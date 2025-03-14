package sde.homework.demo.githubmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "git_repositories")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String repositoryFullName;
    private String githubRepoUrl;

    private boolean alertOnNewRelease;
    private String alertLabel;

    @ManyToOne
    private User user;
}