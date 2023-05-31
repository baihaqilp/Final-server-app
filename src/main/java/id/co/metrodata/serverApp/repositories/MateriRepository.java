package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Materi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriRepository extends JpaRepository<Materi, Long> {
    List<Materi> findAllByTopic_Id(Long id);

    List<Materi> findAllByEmployee_Id(Long id);

    @Query(value = "SELECT * FROM tb_materi order by materi_id desc", nativeQuery = true)
    List<Materi> findAllByOrder();
}
