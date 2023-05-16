package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
