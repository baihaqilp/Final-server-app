package id.co.metrodata.serverApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
