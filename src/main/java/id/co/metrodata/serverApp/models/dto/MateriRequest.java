package id.co.metrodata.serverApp.models.dto;

import lombok.Data;

@Data
public class MateriRequest {
    private String name;
    private String desc;
    private Long topicId;
}
