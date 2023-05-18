package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class RoleRequest {
    private String name;
    private Long[] privileges;
}
