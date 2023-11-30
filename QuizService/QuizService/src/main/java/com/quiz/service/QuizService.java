package com.quiz.service;

import com.quiz.entities.Quiz;

import java.util.List;

public interface QuizService {

   Quiz addQuiz(Quiz quiz);

   List<Quiz> getAllQuiz();

   Quiz getQuiz(Long id);

}
