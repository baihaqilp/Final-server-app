package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // @Query(value = "SELECT * FROM tb_user a join tb_user_role b on b.user_id =
    // a.id join tb_role c on c.id = b.role_id WHERE c.id = 1", nativeQuery = true)
    // List<User> findByRole_Id(Long id);
}
