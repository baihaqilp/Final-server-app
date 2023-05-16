package id.co.metrodata.serverApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

}
