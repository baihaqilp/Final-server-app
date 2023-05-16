package id.co.metrodata.serverApp.models.dto.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class TaskRequest {
    private String name;
    private String desc;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private Date deadline;
    private Long segmentId;
}
