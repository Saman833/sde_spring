package sde.homework.demo.model;

import java.time.LocalDateTime;

public class Subscriber {
    private String email;
    private LocalDateTime createdAt;
    private String ipAddress;
    private String source;

    public Subscriber(String email, LocalDateTime createdAt, String ipAddress , String source) {
        this.email = email;
        this.createdAt = createdAt;
        this.ipAddress = ipAddress;
        this.source=source;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public String getSource(){
        return source;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", source=" + source +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
