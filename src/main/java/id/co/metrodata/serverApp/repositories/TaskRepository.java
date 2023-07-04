package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
        List<Task> findBySegment_Id(Long id);

        @Query(value = "SELECT * FROM tb_task t join tb_segment s on t.segment_id = s.id join tb_employee e on e.id = s.trainer_id join tb_user u on u.id = e.id WHERE u.username = ? order by t.task_id desc", nativeQuery = true)
        public List<Task> findByTrainerTask(String username);

        @Query(value = "SELECT * FROM tb_task t join tb_segment s on t.segment_id = s.id join tb_class c on c.id = s.classroom_id join tb_employee e on e.classroom_id = c.id join tb_user u on u.id = e.id WHERE u.username = ? order by t.task_id desc", nativeQuery = true)
        public List<Task> findByTraineeTask(String username);
}
