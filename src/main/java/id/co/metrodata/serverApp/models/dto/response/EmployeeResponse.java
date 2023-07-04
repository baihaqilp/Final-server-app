package id.co.metrodata.serverApp.models.dto.response;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.Role;
import id.co.metrodata.serverApp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private String name;
    private String email;
    private String phone;
    private String address;
    private Role role;
    private User user;
    private Classroom classroom;
}
