package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    public Boolean existsBySegment_Id(Long id);
    public Boolean existsByTrainee_Id(Long id);
    List<Grade> findAllBySegment_Id(Long id);
}
