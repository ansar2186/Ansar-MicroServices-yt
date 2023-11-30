package com.question.service.imp;

import com.question.entities.Question;
import com.question.repository.QuestionRepository;
import com.question.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question not found by Id" +id));
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long Id) {
        return questionRepository.findByQuizId(Id);
    }
}
