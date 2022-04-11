package kz.aibat.TaskManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private AuthUser author;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<AuthUser> authUserList;
}
