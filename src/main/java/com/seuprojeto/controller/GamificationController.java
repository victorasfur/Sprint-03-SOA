package com.seuprojeto.controller;

import com.seuprojeto.dto.UserScoreDTO;
import com.seuprojeto.enums.ActivityType;
import com.seuprojeto.service.GamificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamification")
public class GamificationController {

    @Autowired
    private GamificationService gamificationService;

    @PostMapping("/activities/{userId}")
    public ResponseEntity<UserScoreDTO> addActivity(@PathVariable String userId, @RequestParam ActivityType activityType) {
        UserScoreDTO updatedScore = gamificationService.updateScore(Long.valueOf(userId), activityType, 10);
        return new ResponseEntity<>(updatedScore, HttpStatus.OK);
    }

    @GetMapping("/scores")
    public ResponseEntity<List<UserScoreDTO>> getAllUserScores() {
        List<UserScoreDTO> allScores = gamificationService.getAllUserScores();
        return new ResponseEntity<>(allScores, HttpStatus.OK);
    }
}