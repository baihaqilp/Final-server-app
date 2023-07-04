package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import id.co.metrodata.serverApp.models.dto.request.EmailRequest;
import id.co.metrodata.serverApp.models.dto.request.LoginRequest;
import id.co.metrodata.serverApp.models.dto.response.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        private PasswordEncoder passwordEncoder;
        private UserRepository userRepository;
        private ClassroomService classroomService;
        private RoleService roleService;
        private AuthenticationManager authenticationManager;
        private AppUserDetailService appUserDetailService;
        private EmailService emailService;

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

                // send email
                EmailRequest emailRequest = new EmailRequest();
                emailRequest.setTo(userRequest.getEmail());
                emailRequest.setSubject("Register Successfully");
                emailRequest.setName(userRequest.getName());
                emailService.sendMailRegister(emailRequest, user.getUsername(), userRequest.getPassword(), user.getEmployee().getClassroom().getProgram().getName(), user.getEmployee().getClassroom().getName());
                return userRepository.save(user);

        }

        public LoginResponse login(LoginRequest loginRequest) {
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword());

                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);

                User user = userRepository
                                .findByUsername(
                                                loginRequest.getUsername())
                                .get();

                UserDetails userDetails = appUserDetailService.loadUserByUsername(
                                loginRequest.getUsername());

                List<String> authorities = userDetails
                                .getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList());

                return new LoginResponse(
                                user.getUsername(),
                                authorities);
        }
}
