package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.metrodata.serverApp.models.Employee;
import id.co.metrodata.serverApp.models.Role;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.UserRequest;
import id.co.metrodata.serverApp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private ModelMapper modelMapper;
    private AppUserDetailService appUserDetailService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private ClassroomService classroomService;
    private RoleService roleService;

    public User register(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        Employee employee = modelMapper.map(userRequest, Employee.class);
        // set classroom
        employee.setClassroom(classroomService.getById(userRequest.getClassroomId()));
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
}
