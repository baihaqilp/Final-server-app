package id.co.metrodata.serverApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    public Boolean existsByName(String name);
}
