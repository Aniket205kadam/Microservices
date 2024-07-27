package dev.aniket.QuizApp.service;

import dev.aniket.QuizApp.dao.QuestionDao;
import dev.aniket.QuizApp.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
