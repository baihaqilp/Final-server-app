package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    public Boolean existsByName(String name);

    List<Topic> findAllByProgram_Id(Long id);

    public Boolean existsByProgram_Id(Long id);

    @Query(value = "SELECT * FROM tb_topic order by topic_id desc", nativeQuery = true)
    List<Topic> findAllByOrderByIdDesc();
}
