package dev.aniket.QuizApp.dao;

import dev.aniket.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
