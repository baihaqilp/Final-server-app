package id.co.metrodata.serverApp.models.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
}
