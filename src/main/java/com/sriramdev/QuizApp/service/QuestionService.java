package com.sriramdev.QuizApp.service;

import com.sriramdev.QuizApp.model.Question;
import com.sriramdev.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

@Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
return  questionDao.findAll();
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);}
        catch (Exception e){
            e.printStackTrace();
        }
 return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByDifficulty(String difficultyLevel) {
     try{return new ResponseEntity<>(questionDao.findBydifficultyLevel(difficultyLevel), HttpStatus.OK);}
        catch (Exception e){
        e.printStackTrace();
    }
 return new ResponseEntity<>(questionDao.findBydifficultyLevel(difficultyLevel), HttpStatus.BAD_REQUEST);
}

public ResponseEntity<String>  addQuestion(Question question){
        questionDao.save(question);
        return new ResponseEntity<>( "success", HttpStatus.CREATED);
}

}
