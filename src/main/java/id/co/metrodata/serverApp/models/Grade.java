package id.co.metrodata.serverApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;
    @Column(nullable = false, name = "grade_name")
    private String name;
    @Column(nullable = false, name = "grade_average")
    private float average;
    @Column(nullable = false, name = "grade_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "trainee_id", nullable = false)
    private Employee trainee;

    @ManyToOne
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;
}
