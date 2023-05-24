package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByTrainer_Id(Long id);

    List<Evaluation> findAllBySubmission_Id(Long id);

    List<Evaluation> findAllBySubmission_Task_Id(Long id);

    Boolean existsByTrainer_Id(Long id);
}
