package com.seuprojeto.service;

import com.seuprojeto.dto.UserScoreDTO;
import com.seuprojeto.enums.ActivityType;
import com.seuprojeto.model.Activity;
import com.seuprojeto.model.UserScore;
import com.seuprojeto.repository.ActivityRepository;
import com.seuprojeto.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GamificationService {

    @Autowired
    private UserScoreRepository userScoreRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public UserScoreDTO updateScore(Long userId, ActivityType activityType, int points) {
        Optional<UserScore> userScoreOptional = userScoreRepository.findById(userId);
        UserScore userScore;

        if (userScoreOptional.isPresent()) {
            userScore = userScoreOptional.get();
        } else {
            userScore = new UserScore();
            userScore.setUserId(userId);
            userScore.setScore(0);
        }

        userScore.setScore(userScore.getScore() + points);
        userScoreRepository.save(userScore);

        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setType(activityType);
        activity.setTimestamp(LocalDateTime.now());
        activityRepository.save(activity);

        return new UserScoreDTO(String.valueOf(userScore.getUserId()), userScore.getScore());
    }

    public List<UserScoreDTO> getAllUserScores() {
        return userScoreRepository.findAll().stream()
                .map(userScore -> new UserScoreDTO(String.valueOf(userScore.getUserId()), userScore.getScore()))
                .collect(Collectors.toList());
    }
}