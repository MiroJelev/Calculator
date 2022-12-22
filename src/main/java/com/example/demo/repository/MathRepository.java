package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Answer;

@Repository
public interface  MathRepository extends JpaRepository<Answer, String> {
    Optional<Answer> findAnswerByCalculation(String expression);
}

