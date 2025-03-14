package sde.homework.demo.githubmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sde.homework.demo.githubmanager.repository.RepositoryRepository;
import sde.homework.demo.githubmanager.service.UserService;
import sde.homework.demo.githubmanager.model.User;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final UserService userService;
    private final RepositoryRepository repositoryRepository;

    public StatsController(UserService userService,
                            RepositoryRepository trackedRepositoryRepository) {
        this.userService = userService;
        this.repositoryRepository = trackedRepositoryRepository;
    }

    @GetMapping("/global")
    public ResponseEntity<Map<String, Object>> getGlobalStats() {
        long totalTrackedRepos = repositoryRepository.count();
        Map<String, Object> response = Map.of(
                "totalTrackedRepositories", totalTrackedRepos
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Map<String, Object>> getUserStats(@PathVariable String username) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        long userRepoCount = user.getTrackedRepositories().size();
        Map<String, Object> response = Map.of(
                "username", username,
                "trackedRepoCount", userRepoCount
        );
        return ResponseEntity.ok(response);
    }
}

