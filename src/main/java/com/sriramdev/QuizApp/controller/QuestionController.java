package com.sriramdev.QuizApp.controller;

import com.sriramdev.QuizApp.model.Question;
import com.sriramdev.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allquestion")
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestions();
    }
@GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        System.out.println(category);
        return questionService.getQuestionByCategory(category);
}
@GetMapping("difficulty/{difficultyLeve}")
    public ResponseEntity<List<Question>> getQuestionByDifficulty(@PathVariable String difficultyLeve){
       return questionService.getQuestionByDifficulty(difficultyLeve);
}
@PostMapping("add")
    public ResponseEntity<String>  addQuestion(@RequestBody Question ques){
            return questionService.addQuestion(ques);
}
}
