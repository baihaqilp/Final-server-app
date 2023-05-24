package id.co.metrodata.serverApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverApp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Boolean existsByName(String name);
}
