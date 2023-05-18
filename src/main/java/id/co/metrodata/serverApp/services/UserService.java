package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found"));
    }

    // public List<User> getByRole(Long id){
    // return userRepository.findByRole_Id(id)
    // }

    public User create(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        Employee employee = modelMapper.map(userRequest, Employee.class);
        employee.setUser(user);
        user.setEmployee(employee);

        // set role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(userRequest.getRoleId()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
