package com.question.service;

import com.question.QuestionServiceApplication;
import com.question.entities.Question;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    List<Question> getAllQuestion();

    Question getQuestionById(Long id);

    List<Question> getQuestionsOfQuiz(Long Id);
}
