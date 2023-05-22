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
@Table(name = "tb_materi")
public class Materi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materi_id")
    private Long id;
    @Column(nullable = false, name = "materi_name")
    private String name;
    @Column(nullable = false, name = "materi_desc")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "materi")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Discussion> discussions;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Employee employee;
}
