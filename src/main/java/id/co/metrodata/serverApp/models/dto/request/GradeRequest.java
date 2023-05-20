package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class GradeRequest {
    private String name;
    private String status;
    private float average;
    private Long traineeId;
    private Long segmentId;
}
