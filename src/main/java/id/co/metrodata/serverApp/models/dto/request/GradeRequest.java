package id.co.metrodata.serverApp.models.dto.request;

import id.co.metrodata.serverApp.models.Employee;
import lombok.Data;

@Data
public class GradeRequest {
    private String name;
    private String status;
    private float average;
    private Long employeeId;
    private Long segmentId;
}
