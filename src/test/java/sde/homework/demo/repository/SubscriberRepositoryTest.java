package sde.homework.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sde.homework.demo.model.Subscriber;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class SubscriberRepositoryTest {

    @Autowired
    private SubscriberRepository subscriberRepository;

    private Subscriber testSubscriber;

    @BeforeEach
    void setUp() {
        subscriberRepository.deleteAll();
        testSubscriber = new Subscriber("test@example.com", LocalDateTime.now(), "192.168.1.1", "Test Source");
        subscriberRepository.save(testSubscriber);
    }

    @Test
    void testExistsByEmail_ShouldReturnTrue() {
        assertTrue(subscriberRepository.existsByEmail("test@example.com"));
    }

    @Test
    void testExistsByEmail_ShouldReturnFalse() {
        assertFalse(subscriberRepository.existsByEmail("notfound@example.com"));
    }

    @Test
    void testFindByEmail_ShouldReturnSubscriber() {
        Optional<Subscriber> foundSubscriber = subscriberRepository.findByEmail("test@example.com");

        assertTrue(foundSubscriber.isPresent());
        assertEquals("test@example.com", foundSubscriber.get().getEmail());
    }

    @Test
    void testFindByEmail_ShouldReturnEmpty() {
        Optional<Subscriber> foundSubscriber = subscriberRepository.findByEmail("notfound@example.com");

        assertFalse(foundSubscriber.isPresent());
    }

    @Test
    void testSaveSubscriber() {
        Subscriber newSubscriber = new Subscriber("new@example.com", LocalDateTime.now(), "10.0.0.1", "Web");
        Subscriber savedSubscriber = subscriberRepository.save(newSubscriber);

        assertNotNull(savedSubscriber.getId());
        assertEquals("new@example.com", savedSubscriber.getEmail());
    }

    @Test
    void testDeleteSubscriber() {
        subscriberRepository.delete(testSubscriber);
        assertFalse(subscriberRepository.existsByEmail("test@example.com"));
    }
}
