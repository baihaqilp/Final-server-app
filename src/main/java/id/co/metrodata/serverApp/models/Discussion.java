package id.co.metrodata.serverApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_discussion")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discussion_id")
    private Long id;
    @Column(nullable = false, name = "discussion_title")
    private String title;
    @Column(nullable = false, name = "discussion_desc")
    private String desc;
    @Column(nullable = false, name = "discussion_create_at")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "materi_id", nullable = false)
    private Materi materi;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
