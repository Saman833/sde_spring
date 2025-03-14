package sde.homework.demo.githubmanager.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "git_events")
public class GitEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String repository;

    @Column(nullable = false)
    private String actor;

    private String eventUrl;

    private LocalDateTime eventTime;
}

