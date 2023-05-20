package id.co.metrodata.serverApp.models.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskRequest {
    private String name;
    private String desc;
    private LocalDateTime deadline;
    private Long segmentId;
}
