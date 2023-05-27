package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class ChangeStatusRequest {
    private Long id;
    private Boolean status;
}
