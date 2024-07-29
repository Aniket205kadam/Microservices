package dev.aniket.question_service.service;

import dev.aniket.question_service.dao.QuestionDao;
import dev.aniket.question_service.model.Question;
import dev.aniket.question_service.model.QuestionWrapper;
import dev.aniket.question_service.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> saveQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question Added Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question Updated Successfully!", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully!", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<QuestionWrapper> getQuestionById(Integer questionId) {
        Optional<Question> question = questionDao.findById(questionId);

        //if the question is not present
        if (question.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Convert the Question to QuestionWrapper
        QuestionWrapper questionWrapper = new QuestionWrapper();
        questionWrapper.setId(question.get().getId());
        questionWrapper.setQuestionTitle(question.get().getQuestionTitle());
        questionWrapper.setOption1(question.get().getOption1());
        questionWrapper.setOption2(question.get().getOption2());
        questionWrapper.setOption3(question.get().getOption3());
        questionWrapper.setOption4(question.get().getOption4());

        return new ResponseEntity<>(questionWrapper, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {
        List<Question> questions = questionDao.findAllById(questionIds);
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            //add to the list
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer score = 0;
        for (Response r : responses) {
            if (questionDao.findById(r.getId()).get().getRightAnswer().equals(r.getResponse())) {
                score++;
            }
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
