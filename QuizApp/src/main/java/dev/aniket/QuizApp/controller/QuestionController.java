package dev.aniket.QuizApp.controller;

import dev.aniket.QuizApp.model.Question;
import dev.aniket.QuizApp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question,
                                                 @PathVariable("questionId") Integer id)
    {
        question.setId(id);
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("questionId") Integer id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Question>> findByCategory(@PathVariable("categoryName") String category) {
        return questionService.getQuestionByCategory(category);
    }
}
