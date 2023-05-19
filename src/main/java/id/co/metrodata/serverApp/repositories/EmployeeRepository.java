package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM tb_user_role a join tb_employee b on a.user_id = b.id WHERE a.role_id = ?", nativeQuery = true)
    List<Employee> findByUserRole(Long id);

    List<Employee> findByClassroom_Id(Long id);

    // @Query(value = "SELECT * FROM tb_segment a join tb_employee b on a.trainer_id
    // = b.id join tb_class c on a.classroom_id = c.id join tb_user d on d.id = b.id
    // join tb_user_role e on e.user_id = d.id WHERE a.classroom_id = ?",
    // nativeQuery = true)
    // List<Employee> findByClass(Long id);

}
