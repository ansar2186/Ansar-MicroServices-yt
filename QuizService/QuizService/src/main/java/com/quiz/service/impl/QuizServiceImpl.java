package com.quiz.service.impl;

import com.quiz.entities.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
      /*  List<Quiz> allQuiz = quizRepository.findAll();
        return allQuiz; */
        List<Quiz> allQuiz = quizRepository.findAll();
        List<Quiz> collect = allQuiz.stream().map(quiz -> {
            quiz.setQuestionList(questionClient.getQuestionsOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Quiz getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestionList(questionClient.getQuestionsOfQuiz(quiz.getId()));
        return  quiz;
    }
}
