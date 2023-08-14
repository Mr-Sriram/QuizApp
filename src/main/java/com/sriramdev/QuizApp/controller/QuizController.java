package com.sriramdev.QuizApp.controller;

import com.sriramdev.QuizApp.model.Question;
import com.sriramdev.QuizApp.model.QuestionWrapper;
import com.sriramdev.QuizApp.model.Response;
import com.sriramdev.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){

return quizService.createQuiz(category,numQ,title);
    }

@GetMapping("getQ/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
return quizService.getQuizQuestions(id);
}

    @GetMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
       return quizService.submitQuiz(id,responses);
    }

}
