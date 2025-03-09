package sde.homework.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubscriberTest {

    private Subscriber subscriber;
    private final String email = "test@example.com";
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final String ipAddress = "192.168.1.1";
    private final String source = "Web";

    @BeforeEach
    void setUp() {
        subscriber = new Subscriber(email, createdAt, ipAddress, source);
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(subscriber);
        assertEquals(email, subscriber.getEmail());
        assertEquals(createdAt, subscriber.getCreatedAt());
        assertEquals(ipAddress, subscriber.getIpAddress());
        assertEquals(source, subscriber.getSource());
    }

    @Test
    void testDefaultConstructor() {
        Subscriber defaultSubscriber = new Subscriber();
        assertNotNull(defaultSubscriber);
    }

    @Test
    void testToStringMethod() {
        String expectedToString = "Subscriber{" +
                "id=" + null +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", ipAddress='" + ipAddress + '\'' +
                ", source='" + source + '\'' +
                '}';
        assertEquals(expectedToString, subscriber.toString());
    }
}
