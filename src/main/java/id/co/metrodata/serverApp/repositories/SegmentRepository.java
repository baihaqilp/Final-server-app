package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Segment;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    List<Segment> findAllByClassroom_Id(Long id);
    public Boolean existsByTrainer_Id(Long id);
    public Boolean existsByClassroom_Id(Long id);
    public Boolean existsByClassroom_IdAndTrainer_Id(Long c_id, Long t_id);
}
