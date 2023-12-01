package com.question.controller;

import com.question.entities.Question;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/saveQuestion")
    public Question createQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }
    //Question service
    @GetMapping("/getAllQuestion")
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/{questionId}")
    public  Question getQuestionById(@PathVariable Long questionId){
        return questionService.getQuestionById(questionId);
    }
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId){
        return  questionService.getQuestionsOfQuiz(quizId);
    }

}
