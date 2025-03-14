package sde.homework.demo.githubmanager.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sde.homework.demo.githubmanager.model.GitRepository;
import sde.homework.demo.githubmanager.service.GitService;
import sde.homework.demo.githubmanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/repos")
public class GitRepositoryController {

    private final UserService userService;
    private final GitService trackedRepositoryService;

    public GitRepositoryController(UserService userService,
                                GitService trackedRepositoryService) {
        this.userService = userService;
        this.trackedRepositoryService = trackedRepositoryService;
    }

    @PostMapping("/track")
    public ResponseEntity<GitRepository> trackRepository(
            @RequestParam String username,
            @RequestParam String repoFullName,
            @RequestParam(defaultValue = "false") boolean alertOnNewRelease,
            @RequestParam(required = false) String alertLabel
    ) {
        return userService.getUserByUsername(username)
                .map(user -> {
                    GitRepository tr = trackedRepositoryService.addRepositoryToUser(
                            user, repoFullName, alertOnNewRelease, alertLabel
                    );
                    return ResponseEntity.ok(tr);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tracked/{username}")
    public ResponseEntity<List<GitRepository>> getTrackedRepositories(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> ResponseEntity.ok(trackedRepositoryService.getAllTrackedByUser(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}

