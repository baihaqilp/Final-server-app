package id.co.metrodata.serverApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @Column(nullable = false, name = "task_name")
    private String name;
    @Column(nullable = false, name = "task_desc")
    private String desc;
    @Column(nullable = false)
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Submission> submissions;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;
}
