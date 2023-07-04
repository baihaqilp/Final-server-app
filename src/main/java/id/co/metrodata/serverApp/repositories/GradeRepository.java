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

        @Query(value = "SELECT * FROM tb_grade g join tb_segment s on g.segment_id = s.id join tb_class c on c.id = s.classroom_id WHERE c.id = ?", nativeQuery = true)
        public List<Grade> findByClassroom(Long id);
        List<Grade> findByTrainee_Id(Long id);
        @Query(value =
                "SELECT * FROM tb_grade g " +
                        "join tb_segment s " +
                        "on g.segment_id = s.id " +
                        "join tb_class c " +
                        "on c.id = s.classroom_id " +
                        "join tb_employee e " +
                        "on e.id = s.trainer_id " +
                        "WHERE e.id = ?",
                nativeQuery = true)
        public List<Grade> findByClassroomTraineer(Long id);
}
