package com.quiz.controller;

import com.quiz.entities.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/addQuiz")
    public Quiz createQuiz(@RequestBody Quiz quiz) {

        return quizService.addQuiz(quiz);
    }


    @GetMapping("/getAllQuiz")
    public List<Quiz> getQuiz() {

        return quizService.getAllQuiz();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuiz(id);

    }
}
