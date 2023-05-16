package id.co.metrodata.serverApp.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscussionRequest {
    private String title;
    private String desc;
    private LocalDateTime createAt = LocalDateTime.now();
    private Long materiId;
    private Long employeeId;
}
