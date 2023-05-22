package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Segment;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    List<Segment> findAllByClassroom_Id(Long id);

    public Boolean existsByTrainer_Id(Long id);

    public Boolean existsByClassroom_Id(Long id);

    public Boolean existsByClassroom_IdAndTrainer_Id(Long c_id, Long t_id);

    @Query(value = "SELECT * FROM tb_segment a join tb_segment_topic b on a.id = b.segment_id join tb_topic c on c.id = b.topic_id join tb_materi d on c.id = d.topic_id WHERE b.segment_id = ?", nativeQuery = true)
    public List<Segment> findAllBySegmentTopic_Id(Long id);

    @Query(value = "SELECT * FROM tb_segment a join tb_employee b on a.trainer_id = b.id WHERE a.trainer_id = ? group by a.classroom_id", nativeQuery = true)
    List<Segment> findAllBySegmentTrainerGroup(Long id);

    @Query(value = "SELECT * FROM tb_segment a join tb_employee b on a.trainer_id = b.id join tb_class c on c.id = a.classroom_id WHERE a.classroom_id = ? and  a.trainer_id = ?", nativeQuery = true)
    List<Segment> findAllBySegmentClassTrainer(Long classroom_id, Long trainer_id);
}
