package dev.aniket.QuizApp.service;

import dev.aniket.QuizApp.dao.QuestionDao;
import dev.aniket.QuizApp.dao.QuizDao;
import dev.aniket.QuizApp.model.Question;
import dev.aniket.QuizApp.model.QuestionWrapper;
import dev.aniket.QuizApp.model.Quiz;
import dev.aniket.QuizApp.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizDao quizDao;
    private final QuestionDao questionDao;


    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        //create the quiz
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        //save the quiz and return it.
        return new ResponseEntity<>(quizDao.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        //Question convert to QuestionWrapper
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            //the wrapper add to the list
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        Integer score = 0;

        //compare the answers
        for (Response r : responses) {
            Question que = getQuestionById(questions, r.getId());
            if (que.getRightAnswer().equals(r.getResponse())) {
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    //helping method for calculateResult()
    public Question getQuestionById(List<Question> questions, Integer  queId) {
        for (Question q : questions) {
            if (q.getId() == queId) {
                return q;
            }
        }
        return new Question();
    }
}
