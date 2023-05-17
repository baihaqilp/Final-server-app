package id.co.metrodata.serverApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float nilai;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

}
