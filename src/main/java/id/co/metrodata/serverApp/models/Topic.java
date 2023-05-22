package id.co.metrodata.serverApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;
    @Column(nullable = false, name = "topic_name")
    private String name;

    @OneToMany(mappedBy = "topic")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Materi> materis;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @OneToMany(mappedBy = "topic")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<SegmentTopic> segmentMateris;
}
