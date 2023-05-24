package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.*;
import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import id.co.metrodata.serverApp.models.dto.request.GradeRequest;
import id.co.metrodata.serverApp.repositories.GradeRepository;
import id.co.metrodata.serverApp.repositories.SegmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private EmployeeService employeeService;
    private SubmissionService submissionService;
    private EvaluationService evaluationService;
    private UserService userService;
    private EmailService emailService;
    private TaskService taskService;

    @Scheduled(cron = "0 0 6 * * *", zone = "Asia/Jakarta")
    public void testScheduler() {
        Date localDate = Date.valueOf(LocalDate.now().plusDays(1));
        segmentService.getAll().forEach(segment -> {
            if ((segment.getEnd_date().compareTo(localDate)) == 0) {
                taskService.getBySegmentId(segment.getId()).forEach(task -> {
                    employeeService.getByClassId(task.getSegment().getClassroom().getId()).forEach(employee -> {
                        if (!gradeRepository.existsBySegment_Id(segment.getId())) {
                            GradeRequest gradeRequest = new GradeRequest();
                            gradeRequest.setSegmentId(segment.getId());
                            gradeRequest.setTraineeId(employee.getId());
                            create(gradeRequest);
                        }
                    });
                });
            }
        });
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> getBySegment(Long id) {
        return gradeRepository.findAllBySegment_Id(id);
    }

    public Grade getById(Long id) {
        return gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not Found!"));
    }

    public List<Grade> getGradeTraineePassedBySegment(Long id) {
        List<Grade> gradeTrainee = new ArrayList<>();
        if (gradeRepository.existsBySegment_Id(id)) {
            for (Grade gradeCheck : gradeRepository.findAllBySegment_Id(id)) {
                if (Objects.equals(gradeCheck.getStatus(), "Lulus")) {
                    gradeTrainee.add(gradeCheck);
                }
            }
        }
        return gradeTrainee;
    }

    public Grade create(GradeRequest gradeRequest) {
        if (gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())) {
            for (Grade gradeCheck : gradeRepository.findAllBySegment_Id(gradeRequest.getSegmentId())) {
                if (Objects.equals(gradeCheck.getTrainee().getId(), gradeRequest.getTraineeId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "The grade in the segment already exists for that trainee!");
                }
            }
        }
        Grade grade = new Grade();
        grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));

        // set Average
        List<Evaluation> evaluationsBySubmission = new ArrayList<>();
        float result = 0f;
        for (Submission submission : submissionService.getByTraineeId(gradeRequest.getTraineeId())) {
            if (Objects.equals(submission.getTask().getSegment().getId(), gradeRequest.getSegmentId())) {
                for (Evaluation evaluation : evaluationService.getBySubmission(submission.getId())) {
                    evaluationsBySubmission.add(evaluation);
                    result += evaluation.getNilai();
                }
            }
        }
        // if (evaluationsBySubmission.isEmpty()) {
        // throw new ResponseStatusException(
        // HttpStatus.NOT_FOUND,
        // "Trainee haven't submitted any submissions yet!");
        // }
        float average = 0;
        if (result != 0) {
            average = result / evaluationsBySubmission.size();
        }
        grade.setAverage(average);

        // set Name and Status
        if (average >= 70) {
            grade.setStatus("Lulus");
            if (average >= 90)
                grade.setName("A");
            else if (average >= 80)
                grade.setName("B");
            else
                grade.setName("C");
        } else {
            grade.setStatus("Tidak Lulus");
            if (average >= 60)
                grade.setName("D");
            else
                grade.setName("E");

            // set user isEnabled
            User user = userService.getById(gradeRequest.getTraineeId());
            user.setIsEnabled(false);

            // send email to user fail
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo(user.getEmployee().getEmail());
            emailRequest.setSubject("Graduation Announcement");
            emailRequest.setName(user.getEmployee().getName());
            emailService.sendMailGrade(emailRequest);
        }
        return gradeRepository.save(grade);
    }

    public Grade update(Long id, GradeRequest gradeRequest) {
        Grade gradeOld = getById(id);
        if (!Objects.equals(gradeOld.getSegment().getId(), gradeRequest.getSegmentId())
                && !Objects.equals(gradeOld.getTrainee().getId(), gradeRequest.getTraineeId())) {
            if (gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())) {
                for (Grade gradeCheck : gradeRepository.findAllBySegment_Id(gradeRequest.getSegmentId())) {
                    if (Objects.equals(gradeCheck.getTrainee().getId(), gradeRequest.getTraineeId())) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "The grade in the segment already exists for that trainee!");
                    }
                }
            }
        }
        Grade grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));

        // set Average
        List<Evaluation> evaluationsBySubmission = new ArrayList<>();
        float result = 0f;
        for (Submission submission : submissionService.getByTraineeId(gradeRequest.getTraineeId())) {
            if (Objects.equals(submission.getTask().getSegment().getId(), gradeRequest.getSegmentId())) {
                for (Evaluation evaluation : evaluationService.getBySubmission(submission.getId())) {
                    evaluationsBySubmission.add(evaluation);
                    result += evaluation.getNilai();
                }
            }
        }
        if (evaluationsBySubmission.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Trainee haven't submitted any submissions yet!");
        }
        float average = result / evaluationsBySubmission.size();
        grade.setAverage(average);

        // set Name and Status
        if (average >= 70) {
            grade.setStatus("Lulus");
            if (average >= 90)
                grade.setName("A");
            else if (average >= 80)
                grade.setName("B");
            else
                grade.setName("C");
        } else {
            grade.setStatus("Tidak Lulus");
            if (average >= 60)
                grade.setName("D");
            else
                grade.setName("E");
            User user = userService.getById(gradeRequest.getTraineeId());
            user.setIsEnabled(false);
        }
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public Grade delete(Long id) {
        Grade grade = getById(id);
        gradeRepository.delete(grade);
        return grade;
    }
}
