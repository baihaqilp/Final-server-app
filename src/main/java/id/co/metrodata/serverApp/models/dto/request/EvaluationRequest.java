package id.co.metrodata.serverApp.models.dto.request;

import lombok.Data;

@Data
public class EvaluationRequest {
    private float nilai;
    private Long trainer_id;
    private Long submission_id;
}
