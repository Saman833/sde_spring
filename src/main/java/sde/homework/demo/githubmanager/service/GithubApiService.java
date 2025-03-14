package sde.homework.demo.githubmanager.service;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sde.homework.demo.githubmanager.config.GithubProperties;

@Service
public class GithubApiService {

    private final WebClient webClient;

    public GithubApiService(GithubProperties githubProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(githubProperties.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubProperties.getToken())
                .build();
    }

    // Example: fetching recent commits
    public Mono<String> getCommits(String owner, String repo) {
        // GET /repos/{owner}/{repo}/commits
        return webClient.get()
                .uri("/repos/{owner}/{repo}/commits", owner, repo)
                .retrieve()
                .bodyToMono(String.class);
    }


    public Mono<String> getIssues(String owner, String repo) {
        // GET /repos/{owner}/{repo}/issues
        return webClient.get()
                .uri("/repos/{owner}/{repo}/issues", owner, repo)
                .retrieve()
                .bodyToMono(String.class);
    }


}

