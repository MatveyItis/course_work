package ru.itis.teamwork.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
//@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chat")
public class Chat implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Set<Message> messages = new HashSet<>();

    @OneToOne(mappedBy = "chat")
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "user_chats",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();
}
