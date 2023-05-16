package id.co.metrodata.serverApp.models;

<<<<<<< HEAD
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

=======
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> e93d6d84024d8df05739664a55598fcd58faa34c
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "segment", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks;
    @OneToMany(mappedBy = "segment")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Grade> grades;
}
