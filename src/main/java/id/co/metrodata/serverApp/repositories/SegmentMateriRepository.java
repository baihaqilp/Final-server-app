package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.SegmentMateri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentMateriRepository extends JpaRepository<SegmentMateri, Long> {
    public Boolean existsBySegment_Id(Long id);
    public Boolean existsByMateri_Id(Long id);
    List<SegmentMateri> findAllBySegment_Id(Long id);

}
