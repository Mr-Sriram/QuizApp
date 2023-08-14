package com.sriramdev.QuizApp.service;

import com.sriramdev.QuizApp.dao.QuestionDao;
import com.sriramdev.QuizApp.dao.QuizDoa;
import com.sriramdev.QuizApp.model.Question;
import com.sriramdev.QuizApp.model.QuestionWrapper;
import com.sriramdev.QuizApp.model.Quiz;
import com.sriramdev.QuizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDoa quizDoa;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionbyCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDoa.save(quiz);
        return  new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizDoa.findById(id);
        List<Question> questionFromDb= quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question q: questionFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        System.out.println(questionFromDb);
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }
    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses){
        Quiz quiz = quizDoa.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right =0;
        int i=0;
        for (Response response:responses){
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer()))
                right++;
            i++;

        }
        return  new ResponseEntity<>(right,HttpStatus.OK);

    }
}
