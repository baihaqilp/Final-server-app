package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.SegmentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentTopicRepository extends JpaRepository<SegmentTopic, Long> {
    public Boolean existsBySegment_Id(Long id);

    public Boolean existsByTopic_Id(Long id);

    List<SegmentTopic> findAllBySegment_Id(Long id);

}
