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
@Table(name = "tb_grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;
    @Column(nullable = false, name = "grade_name")
    private String name;
    @Column(nullable = false, name = "grade_average")
    private int average;
    @Column(nullable = false, name = "grade_status")
    private String status;
}
