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

import id.co.metrodata.serverApp.models.Privilege;
import id.co.metrodata.serverApp.services.PrivilegeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/privilege")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PrivilegeController {
    private PrivilegeService privilegeService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Privilege> getAll() {
        return privilegeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Privilege getById(@PathVariable Long id) {
        return privilegeService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Privilege create(@RequestBody Privilege privilege) {
        return privilegeService.create(privilege);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Privilege update(@PathVariable Long id, @RequestBody Privilege privilege) {
        return privilegeService.update(id, privilege);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Privilege delete(@PathVariable Long id) {
        return privilegeService.delete(id);
    }
}
