package id.co.metrodata.serverApp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Privilege;
import id.co.metrodata.serverApp.repositories.PrivilegeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrivilegeService {
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> getAll() {
        return privilegeRepository.findAll();
    }

    public Privilege getById(Long id) {
        return privilegeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilege id not found"));
    }

    public Privilege create(Privilege privilege) {
        if (privilegeRepository.existsByName(privilege.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Privilege name is already exists!");
        }
        return privilegeRepository.save(privilege);
    }

    public Privilege update(Long id, Privilege privilege) {
        getById(id);
        privilege.setId(id);
        return privilegeRepository.save(privilege);
    }

    public Privilege delete(Long id) {
        Privilege privilege = getById(id);
        privilegeRepository.delete(privilege);
        return privilege;
    }
}
