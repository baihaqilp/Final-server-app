package id.co.metrodata.serverApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_segment_topic")
public class SegmentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private Date start_date;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private Date end_date;
    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
