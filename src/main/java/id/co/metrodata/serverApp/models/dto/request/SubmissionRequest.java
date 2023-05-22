package id.co.metrodata.serverApp.models.dto.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SubmissionRequest {
    private String submission_file;
    private String submission_url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime submission_date;
    private Float nilai;
    private Long taskId;
    private Long employeeId;

}
