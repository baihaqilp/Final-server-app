package id.co.metrodata.serverApp.models.dto.request;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class TaskRequest {
    private String name;
    private String desc;
    // @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private LocalDateTime deadline;
    private Long segmentId;
}
