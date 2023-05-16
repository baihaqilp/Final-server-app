package id.co.metrodata.serverApp.models.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long discussionId;
    private Long employeeId;
}
