package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class MateriRequest {
    private String name;
    private String desc;
    private Long topicId;
}
