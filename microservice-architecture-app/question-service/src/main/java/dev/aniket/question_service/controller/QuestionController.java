package dev.aniket.question_service.controller;


import dev.aniket.question_service.model.Question;
import dev.aniket.question_service.model.QuestionWrapper;
import dev.aniket.question_service.model.Response;
import dev.aniket.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    private final Environment environment;

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

//    http://localhost:8080/question/generate?category=Java&numQ=5
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam("category") String categoryName,
                                                             @RequestParam("numQ") Integer numQuestion)
    {
        return questionService.getQuestionForQuiz(categoryName, numQuestion);
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<QuestionWrapper> getQuestionById(@PathVariable("id") Integer questionId) {
        return questionService.getQuestionById(questionId);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionIds(@RequestBody List<Integer> questionIds) {
        System.out.println("Port of the application: " + environment.getProperty("local.server.port"));
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

    //generate
    //getQuestion (questionId)
    //getScore
}
