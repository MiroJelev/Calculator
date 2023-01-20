package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Answer;

@Repository
public interface  MathRepository extends JpaRepository<Answer, String> {
    Optional<Answer> findAnswerByCalculation(String expression);
    Optional<Answer> findAnswerByid(long id);
    @Transactional
    void deleteByid(long id);
    List<Answer> findAllByUser_Id(long id);
}

