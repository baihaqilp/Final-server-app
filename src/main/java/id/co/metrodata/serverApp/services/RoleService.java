package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Privilege;
import id.co.metrodata.serverApp.models.Role;
import id.co.metrodata.serverApp.models.dto.request.RoleRequest;
import id.co.metrodata.serverApp.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;
    private PrivilegeService privilegeService;
    private ModelMapper modelMapper;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Role create(RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Role name is already exists!"
            );
        }
        Role role = modelMapper.map(roleRequest, Role.class);
        // set privilege
        List<Privilege> privileges = new ArrayList<>();
        for (Long privilege : roleRequest.getPrivileges()) {
            privileges.add(privilegeService.getById(privilege));
        }
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    public Role update(Long id, RoleRequest roleRequest) {
        Role roleOld = getById(id);
        for (Role roleCheck : getAll()) {
            if (!Objects.equals(roleCheck.getId(), roleOld.getId())) {
                if (Objects.equals(roleCheck.getName(), roleRequest.getName())) {
                    throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Role name is already exists!"
                    );
                }
            }
        }
        Role role = modelMapper.map(roleRequest, Role.class);
        role.setId(id);
        // set privilege
        List<Privilege> privileges = new ArrayList<>();
        for (Long privilege : roleRequest.getPrivileges()) {
            privileges.add(privilegeService.getById(privilege));
        }
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    public Role delete(Long id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
