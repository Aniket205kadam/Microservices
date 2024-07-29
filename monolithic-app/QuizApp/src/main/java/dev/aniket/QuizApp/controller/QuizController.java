package dev.aniket.QuizApp.controller;

import dev.aniket.QuizApp.model.Question;
import dev.aniket.QuizApp.model.QuestionWrapper;
import dev.aniket.QuizApp.model.Quiz;
import dev.aniket.QuizApp.model.Response;
import dev.aniket.QuizApp.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category,
                                           @RequestParam int numQ,
                                           @RequestParam String title)
    {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer quizId,
                                              @RequestBody List<Response> responses)
    {
        return quizService.calculateResult(quizId, responses);
    }
}
