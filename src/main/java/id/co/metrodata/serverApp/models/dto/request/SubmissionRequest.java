package id.co.metrodata.serverApp.models.dto.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class SubmissionRequest {
    private String submission_file;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private Date submission_date;
    private Float nilai;
    private Long taskId;
    private Long employeeId;

}
