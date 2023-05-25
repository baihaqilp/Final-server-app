package id.co.metrodata.serverApp.models.dto.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class SegmentRequest {
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private Date start_date;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private Date end_date;
    private Long trainerId;
    private Long classroomId;
    private Long categoryId;

}
