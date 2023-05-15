package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Materi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriRepository extends JpaRepository<Materi, Long> {
}
