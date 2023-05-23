package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String name;
}
