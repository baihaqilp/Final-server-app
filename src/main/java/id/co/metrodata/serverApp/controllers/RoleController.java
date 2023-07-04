package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.Role;
import id.co.metrodata.serverApp.models.dto.request.RoleRequest;
import id.co.metrodata.serverApp.services.RoleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    private RoleService roleService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Role create(@RequestBody RoleRequest roleRequest) {
        return roleService.create(roleRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody RoleRequest roleRequest) {
        return roleService.update(id, roleRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Long id) {
        return roleService.delete(id);
    }
}
