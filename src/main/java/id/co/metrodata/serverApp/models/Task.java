package id.co.metrodata.serverApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(nullable = false, name = "task_deadline")
    private LocalDateTime deadline;
}
