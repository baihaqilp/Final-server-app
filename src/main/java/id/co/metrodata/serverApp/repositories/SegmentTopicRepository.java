package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.SegmentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentTopicRepository extends JpaRepository<SegmentTopic, Long> {
    public Boolean existsBySegment_Id(Long id);

    public Boolean existsByTopic_Id(Long id);

    @Query(value = "SELECT * FROM tb_segment_topic a join tb_segment b on a.segment_id = b.id WHERE a.segment_id = ? and b.classroom_id = ? order by b.category_id asc", nativeQuery = true)
    List<SegmentTopic> findAllBySegmentOrderCategory(Long id, Long classroom_id);

    List<SegmentTopic> findAllBySegment_Id(Long id);

    Boolean existsByTopic_IdAndSegment_Id(Long topic_id, Long segment_id);

}
