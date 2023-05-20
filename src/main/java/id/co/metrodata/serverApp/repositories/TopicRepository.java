package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    public Boolean existsByName(String name);
}
