package ru.itis.teamwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.teamwork.forms.CreateProjectForm;
import ru.itis.teamwork.models.Project;
import ru.itis.teamwork.models.User;
import ru.itis.teamwork.repositories.ProjectRepository;
import ru.itis.teamwork.repositories.UserRepository;

import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> findProjectsByUser(User user) {
        return new ArrayList<>();
    }

    public boolean create(CreateProjectForm form) {
        User user = userRepository.findByUsername(form.getTeamLeaderLogin());

        Set<User> participants = new HashSet<>();
        participants.add(user);

        form.setName(form.getName().replace(" ", "_"));


        Project project = new Project();
        project.setName(form.getName());
        project.setDescription(form.getDescription());
        project.setTeamLeader(user);
        project.setUsers(participants);

        return !(projectRepository.save(project) == null);

    }

    public Project getProjectById(Long id) {
        Optional<Project> byId = projectRepository.findById(id);
        return byId.orElse(new Project());
    }
}
