package dev.aniket.quiz_service.service;


import dev.aniket.quiz_service.dao.QuizDao;
import dev.aniket.quiz_service.feign.QuizInterface;
import dev.aniket.quiz_service.model.QuestionWrapper;
import dev.aniket.quiz_service.model.Quiz;
import dev.aniket.quiz_service.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizDao quizDao;
    private final QuizInterface quizInterface;


    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        //create the quiz
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);

        //save the quiz and return it.
        return new ResponseEntity<>(quizDao.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(List<Integer> questionsIds) {
        List<QuestionWrapper> quizQuestions = quizInterface.getQuestionIds(questionsIds).getBody();

        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        int score = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuizQuestionsIds(Integer id) {
        return new ResponseEntity<>(quizDao.findById(id).get().getQuestionsIds(), HttpStatus.OK);
    }

//    //helping method for calculateResult()
//    public Question getQuestionById(List<Question> questions, Integer  queId) {
//        for (Question q : questions) {
//            if (q.getId() == queId) {
//                return q;
//            }
//        }
//        return new Question();
//    }
}
