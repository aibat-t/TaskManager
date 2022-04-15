package kz.aibat.TaskManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="t_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description", length = 1200)
    private String description;

    @Column(name="deadline")
    private Date deadLine;

    @Column(name="is_compleate", columnDefinition = "boolean default false")
    private boolean isCompleted;


    @ManyToOne(fetch = FetchType.LAZY)
    private AuthUser user;
}
