package sde.homework.demo.githubmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sde.homework.demo.githubmanager.service.GitService;
import sde.homework.demo.githubmanager.service.GithubApiService;
import sde.homework.demo.githubmanager.service.UserService;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final UserService userService;
    private final GitService gitService;
    private final GithubApiService githubApiService;

    public ActivityController(UserService userService,
                              GitService gitService,
                              GithubApiService githubApiService) {
        this.userService = userService;
        this.gitService = gitService;
        this.githubApiService = githubApiService;
    }

    @GetMapping("/commits/{username}")
    public ResponseEntity<Mono<String>> getCommits(@PathVariable String username,
                                                   @RequestParam String repoFullName) {
        return userService.getUserByUsername(username).map(user -> {
            boolean isTracked = gitService
                    .getAllTrackedByUser(user)
                    .stream()
                    .anyMatch(tr -> tr.getRepositoryFullName().equalsIgnoreCase(repoFullName));
            if (!isTracked) {
                return ResponseEntity.badRequest()
                        .body(Mono.just("Repo not tracked by this user."));
            }
            String[] parts = repoFullName.split("/");
            if (parts.length != 2) {
                return ResponseEntity.badRequest()
                        .body(Mono.just("Invalid repository format. Expected owner/repo."));
            }
            Mono<String> commits = githubApiService.getCommits(parts[0], parts[1]);
            return ResponseEntity.ok(commits);
        }).orElse(ResponseEntity.notFound().build());
    }
}

