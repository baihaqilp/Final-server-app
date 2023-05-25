package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.co.metrodata.serverApp.models.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findAllByProgram_Id(Long id);
    List<Classroom> findAllByIsStatus(Boolean status);

    public Boolean existsByName(String name);

    public Boolean existsByProgram_Id(Long id);

    @Query(value = "SELECT * FROM tb_class a join tb_employee b on a.id = b.classroom_id join tb_user c on c.id = b.id WHERE c.username = ?", nativeQuery = true)
    public Classroom findByTraineeClass(String username);
}
