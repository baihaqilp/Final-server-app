package id.co.metrodata.serverApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    public Boolean existsByName(String name);

    @Query(value = "SELECT * FROM tb_program order by id desc", nativeQuery = true)
    public List<Program> findAll();
}
