package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
        List<Classroom> findAllByProgram_Id(Long id);

        List<Classroom> findAllByIsStatus(Boolean status);

        public Boolean existsByName(String name);

        public Boolean existsByProgram_Id(Long id);

        @Query(value = "SELECT * FROM tb_class a join tb_employee b on a.id = b.classroom_id join tb_user c on c.id = b.id WHERE c.username = ? order by a.id desc", nativeQuery = true)
        public Classroom findByTraineeClass(String username);

        @Query(value = "SELECT DISTINCT c.*, u.* FROM tb_class c " +
                        "join tb_segment s " +
                        "on c.id = s.classroom_id " +
                        "join tb_user u " +
                        "on u.id = s.trainer_id " +
                        "WHERE c.is_status = 1 " +
                        "AND u.username = ? " +
                        "order by c.id desc", nativeQuery = true)
        public List<Classroom> findByTrainerClassActive(String username);

        @Query(value = "SELECT * FROM tb_class WHERE is_status = 1 order by id desc", nativeQuery = true)
        public List<Classroom> findByActive();

        @Query(value = "SELECT * FROM tb_class WHERE is_status = 0  order by id desc", nativeQuery = true)
        public List<Classroom> findByNonActive();
}
