package com.sriramdev.QuizApp.dao;

import com.sriramdev.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDoa extends JpaRepository<Quiz,Integer> {
}
