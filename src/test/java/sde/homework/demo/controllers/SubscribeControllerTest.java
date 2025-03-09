package sde.homework.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;


class SubscribeControllerTest {

    private SubscriberRepository subscriberRepository;
    private TestSubscribeController subscribeController;
    private HttpServletRequest request;

    static class TestSubscribeController extends SubscribeController {
        public TestSubscribeController(SubscriberRepository subscriberRepository) {
            super(subscriberRepository);
        }
    }

    @BeforeEach
    void setUp() {
        subscriberRepository = mock(SubscriberRepository.class);
        subscribeController = new TestSubscribeController(subscriberRepository);
        request = mock(HttpServletRequest.class);
    }

    @Test
    void testCreateSubscriber_Success() {

        String email = "Saman1test@example.com";
        String source = "TestSource";
        when(request.getRemoteAddr()).thenReturn("192.168.1.1");
        Subscriber subscriber = subscribeController.createSubscriber(email, request, source);
        assertNotNull(subscriber);
        assertEquals(email, subscriber.getEmail());
        assertEquals("192.168.1.1", subscriber.getIpAddress());
        assertEquals(source, subscriber.getSource());
        assertNotNull(subscriber.getCreatedAt());
    }

    @Test
    void testCreateSubscriber_NullEmail() {
        when(request.getRemoteAddr()).thenReturn("192.168.1.1");
        Subscriber subscriber = subscribeController.createSubscriber(null, request, "TestSource");
        assertNull(subscriber.getEmail());
        assertEquals("192.168.1.1", subscriber.getIpAddress());
    }
}
