package id.co.metrodata.serverApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(nullable = false, name = "materi_desc", columnDefinition = "MEDIUMTEXT")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Employee employee;
}
