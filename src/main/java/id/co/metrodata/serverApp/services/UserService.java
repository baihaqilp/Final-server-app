package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.co.metrodata.serverApp.models.dto.request.ChangePasswordRequest;
import id.co.metrodata.serverApp.models.dto.request.ChangeRoleRequest;
import id.co.metrodata.serverApp.models.dto.request.ChangeStatusRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Employee;
import id.co.metrodata.serverApp.models.Role;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.UserRequest;
import id.co.metrodata.serverApp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found"));
    }

    public User create(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        Employee employee = modelMapper.map(userRequest, Employee.class);
        // set classroom
        employee.setClassroom(null);
        // set user
        employee.setUser(user);
        user.setEmployee(employee);
        // set role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(userRequest.getRoleId()));
        user.setRoles(roles);
        // set password
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, UserRequest userRequest) {
        getById(id);
        User user = modelMapper.map(userRequest, User.class);
        Employee employee = modelMapper.map(userRequest, Employee.class);
        user.setId(id);
        // set user
        employee.setUser(user);
        user.setEmployee(employee);

        // set role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(userRequest.getRoleId()));
        user.setRoles(roles);
        // set password
        if (user.getPassword() != userRequest.getPassword()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        } else {
            user.getPassword();
        }
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    public User getByUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login terlebih dahulu..."));
        return user;

    }

    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = getByUsername();
        if (!passwordEncoder.matches(changePasswordRequest.getPasswordOld(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Old Password is wrong!");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getPasswordNew()));
        return userRepository.save(user);
    }
    public User changeStatus(ChangeStatusRequest changeStatusRequest) {
        User user = getById(changeStatusRequest.getId());
        user.setIsEnabled(changeStatusRequest.getStatus());
        user.setId(changeStatusRequest.getId());
        return userRepository.save(user);
    }
    public User changeRole(ChangeRoleRequest changeRoleRequest) {
        User user = getById(changeRoleRequest.getId());
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(changeRoleRequest.getRoleId()));
        user.setRoles(roles);
        user.setId(changeRoleRequest.getId());
        return userRepository.save(user);
    }
}
