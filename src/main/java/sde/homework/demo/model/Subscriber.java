package sde.homework.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "subscribers")
public class Subscriber {

    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Subscriber() {
    }

    public Subscriber(String email, LocalDateTime createdAt, String ipAddress, String source) {
        this.email = email;
        this.createdAt = createdAt;
        this.ipAddress = ipAddress;
        this.source = source;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", ipAddress='" + ipAddress + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
