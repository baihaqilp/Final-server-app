package id.co.metrodata.serverApp.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class SegmentMateriRequest {
    private Long segmentId;
    private Long materiId;
//    private Long[] materiId;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date start_date;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date end_date;
}
