package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.SegmentMateri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentMateriRepository extends JpaRepository<SegmentMateri, Long> {
}
