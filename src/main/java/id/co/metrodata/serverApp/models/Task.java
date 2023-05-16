package id.co.metrodata.serverApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.util.Date;
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
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    @Column(nullable = false, name = "task_deadline")
    private Date deadline;

    @OneToMany(mappedBy = "task_id", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Submission> submissions;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;
}
