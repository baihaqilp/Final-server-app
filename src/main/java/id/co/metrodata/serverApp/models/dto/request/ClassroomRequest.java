package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class ClassroomRequest {
    private String name;
    private Boolean isStatus;
    private Long programId;
}
