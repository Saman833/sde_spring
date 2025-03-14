package sde.homework.demo.controllers.web;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class WebSubscribeControllerIT {

    @Autowired
    private WebSubscribeController webSubscribeController; // Inject real controller

    @Autowired
    private SubscriberRepository subscriberRepository; // Inject real database repo

    private HttpServletRequest mockRequest;
    private Model model;

    @BeforeEach
    void setUp() {
        mockRequest = mock(HttpServletRequest.class);
        System.out.println("mockRequest isssss :::::    "+mockRequest);
        model = new BindingAwareModelMap();
    }

    @Test
    public void shouldCreateSubscriptionFromWeb() {
        String email = "test@example.com";

        // Call the controller method directly
        String viewName = webSubscribeController.createSubscribe(email, mockRequest, model);
        System.out.println("viewName is:::: "+ viewName);
        // Verify it returns the correct view

    }

    @Test
    public void shouldNotCreateDuplicateSubscription() {
        String email = "duplicate@example.com";

        // First subscription - should succeed
        webSubscribeController.createSubscribe(email, mockRequest, model);
        assertThat(subscriberRepository.existsByEmail(email)).isTrue();

        // Second attempt - should fail with error message
        Model model2 = new BindingAwareModelMap();
        String viewName = webSubscribeController.createSubscribe(email, mockRequest, model2);
        assertThat(viewName).isEqualTo("subscribe/create");

        // Verify the error message
        assertThat(model2.asMap()).containsKey("error");
    }

    @Test
    public void shouldReturnAllWebSubscribers() {
        // Insert test data
        subscriberRepository.save(new Subscriber("web1@example.com", null, "127.0.0.1", "Web"));
        subscriberRepository.save(new Subscriber("web2@example.com", null, "127.0.0.1", "Web"));

        Model model = new BindingAwareModelMap();
        String viewName = webSubscribeController.indexSubscribe(model);

        // Verify view name
        assertThat(viewName).isEqualTo("subscribe/index");

        // Verify the list of subscribers
        List<Subscriber> subscribers = (List<Subscriber>) model.asMap().get("subscribers");
        assertThat(subscribers).hasSize(2);
    }
}
