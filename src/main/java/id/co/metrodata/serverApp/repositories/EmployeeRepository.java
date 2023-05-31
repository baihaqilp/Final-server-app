package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM tb_user_role a join tb_employee b on a.user_id = b.id WHERE a.role_id = ? order by b.id desc", nativeQuery = true)
    List<Employee> findByUserRole(Long id);

    List<Employee> findByClassroom_Id(Long id);

    @Query(value = "SELECT * FROM tb_employee WHERE id = ?", nativeQuery = true)
    Employee findByUsername(Long id);

    Boolean findByEmail(String email);
}
