package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.*;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import id.co.metrodata.serverApp.models.dto.request.GradeRequest;
import id.co.metrodata.serverApp.repositories.GradeRepository;
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
    private ClassroomService classroomService;

    // setiap jam 6 pagi akan di jalankan
    @Scheduled(cron = "0 0 6 * * *", zone = "Asia/Jakarta")
    public void testScheduler() {
        Date local = Date.valueOf(LocalDate.now().minusMonths(2));
        Date localDate = Date.valueOf(LocalDate.now().plusDays(2));
        Date localNow = Date.valueOf(LocalDate.now());
        segmentService.getSegmentByEnddate(local).forEach(segment -> {
            if ((segment.getEnd_date().compareTo(localDate)) == 0) {
                taskService.getBySegmentId(segment.getId()).forEach(task -> {
                    employeeService.getByClassId(task.getSegment().getClassroom().getId()).forEach(employee -> {
                        GradeRequest gradeRequest = new GradeRequest();
                        gradeRequest.setSegmentId(segment.getId());
                        gradeRequest.setTraineeId(employee.getId());
                        create(gradeRequest);
                    });
                });
            }
            Segment segmentEnd = segmentService.findByClass(segment.getClassroom().getId());
            if ((segmentEnd.getEnd_date().compareTo(localNow)) == 0) {
                ClassroomRequest classroomRequest = new ClassroomRequest();
                classroomRequest.setName(segment.getClassroom().getName());
                classroomRequest.setProgramId(segment.getClassroom().getProgram().getId());
                classroomRequest.setIsStatus(false);
                classroomService.update(segment.getClassroom().getId(), classroomRequest);
            }
        });
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> getBySegment(Long id) {
        if (!gradeRepository.existsBySegment_Id(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Segment not found!");
        }
        return gradeRepository.findAllBySegment_Id(id);
    }

    public List<Grade> getByClassroomTrainer(Long id) {
        return gradeRepository.findByClassroomTraineer(id);
    }

    public List<Grade> getByClassroom(Long id) {
        return gradeRepository.findByClassroom(id);
    }

    public Grade getById(Long id) {
        return gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not Found!"));
    }

    public List<Grade> getByTraineeId(Long id) {
        if (!gradeRepository.existsByTrainee_Id(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Trainee not found!");
        }
        return gradeRepository.findByTrainee_Id(id);
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
        if (gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())
                && gradeRepository.existsByTrainee_Id(gradeRequest.getTraineeId())) {
            return null;
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
            emailService.sendMailGrade(emailRequest, user.getUsername(), grade.getSegment().getCategory().getName(), user.getEmployee().getClassroom().getName(), user.getEmployee().getClassroom().getProgram().getName());
        }
        return gradeRepository.save(grade);
    }

    public Grade delete(Long id) {
        Grade grade = getById(id);
        gradeRepository.delete(grade);
        return grade;
    }
}
