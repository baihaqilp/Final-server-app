package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String passwordOld;
    private String passwordNew;
}
