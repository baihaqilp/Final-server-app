package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByTask_Id(Long id);
    List<Submission> findAllByTask_Id(Long id);
    List<Submission> findAllByEmployee_Id(Long id);
    public Boolean existsByTask_Id(Long id);

}
