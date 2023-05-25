package id.co.metrodata.serverApp.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_segment")
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    Employee trainer;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    @Column(nullable = false)
    private Date start_date;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    @Column(nullable = false)
    private Date end_date;

    @OneToMany(mappedBy = "segment", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks;
    @OneToMany(mappedBy = "segment")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Grade> grades;

    @OneToMany(mappedBy = "segment")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<SegmentTopic> segmentMateris;
}
