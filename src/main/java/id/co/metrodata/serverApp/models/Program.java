package id.co.metrodata.serverApp.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "program_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "program")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "program")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Topic> topics;
}
