package dev.aniket.quiz_service.controller;


import dev.aniket.quiz_service.dto.QuizDto;
import dev.aniket.quiz_service.model.QuestionWrapper;
import dev.aniket.quiz_service.model.Quiz;
import dev.aniket.quiz_service.model.Response;
import dev.aniket.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {
    private final QuizService quizService;

//    http://localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Integer>> getQuizQuestionsIds(@PathVariable Integer id) {
        return quizService.getQuizQuestionsIds(id);
    }

    @PostMapping("/get")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestBody List<Integer> questionsIds) {
        return quizService.getQuizQuestions(questionsIds);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses) {
        return quizService.calculateResult(responses);
    }
}
