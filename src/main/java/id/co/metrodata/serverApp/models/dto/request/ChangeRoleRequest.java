package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class ChangeRoleRequest {
    private Long id;
    private Long roleId;
}
