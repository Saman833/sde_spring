package sde.homework.demo.githubmanager.service;


import org.springframework.stereotype.Service;
import sde.homework.demo.githubmanager.model.GitRepository;
import sde.homework.demo.githubmanager.model.User;
import sde.homework.demo.githubmanager.repository.RepositoryRepository;

import java.util.List;

@Service
public class  GitService {

    private final RepositoryRepository trackedRepositoryRepository;

    public GitService(RepositoryRepository trackedRepositoryRepository) {
        this.trackedRepositoryRepository = trackedRepositoryRepository;
    }

    public GitRepository addRepositoryToUser(User user, String repoFullName, boolean alertOnNewRelease, String alertLabel) {
        GitRepository tr = new  GitRepository();
        tr.setRepositoryFullName(repoFullName);
        tr.setAlertOnNewRelease(alertOnNewRelease);
        tr.setAlertLabel(alertLabel);
        tr.setUser(user);

        return trackedRepositoryRepository.save(tr);
    }

    public List<GitRepository> getAllTrackedByUser(User user) {
        // for demonstration we just return everything and filter:
        return trackedRepositoryRepository.findAll().stream()
                .filter(tr -> tr.getUser().getId().equals(user.getId()))
                .toList();
    }
}

