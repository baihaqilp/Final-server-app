package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query(value = "SELECT * FROM tb_submission  WHERE task_id = ? order by id desc", nativeQuery = true)
    List<Submission> findByTask_Id(Long id);

    List<Submission> findAllByTask_Id(Long id);

    List<Submission> findAllByEmployee_Id(Long id);

    public Boolean existsByTask_Id(Long id);

    @Query(value = "SELECT * FROM tb_submission a join tb_task b on a.task_id = b.task_id  WHERE a.task_id = ? and a.employee_id = ? order by a.id desc", nativeQuery = true)
    Submission findAllByTaskTrainee(Long id, Long user_id);
}
