package id.co.metrodata.serverApp.controllers;

import java.util.List;

import id.co.metrodata.serverApp.models.dto.request.ChangePasswordRequest;
import id.co.metrodata.serverApp.models.dto.request.ChangeRoleRequest;
import id.co.metrodata.serverApp.models.dto.request.ChangeStatusRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.UserRequest;
import id.co.metrodata.serverApp.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class UserController {
    private UserService userService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_TRAINER', 'UPDATE_TRAINEE')")
    @PostMapping("/change-password")
    public User changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest);
    }
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id) {
        return userService.delete(id);
    }
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PostMapping("/change-status")
    public User changeStatus(@RequestBody ChangeStatusRequest changeStatusRequest) {
        return userService.changeStatus(changeStatusRequest);
    }
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PostMapping("/change-role")
    public User changeRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        return userService.changeRole(changeRoleRequest);
    }
}
