package ru.itis.teamwork.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode
@ToString(exclude = {"users", "teamLeader"})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "git_link")
    private String gitLink;

    @Column(name = "description", nullable = false, length = 65535)
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<ProjectFile> files;

    @OneToMany(mappedBy = "project")
    private Set<Task> projectTasks;

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "team_leader_id", nullable = false)
    private User teamLeader;
}
