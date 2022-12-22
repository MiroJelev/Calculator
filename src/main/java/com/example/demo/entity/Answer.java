package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private double answer;
    @Column(nullable = false)
    private String calculation;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Answer(){
      
    }
    public Answer(User user, double answer, String calculation) {
      this.user = user;
      this.answer = answer;
      this.calculation = calculation;
    }
    public double getAnswer() {
      return answer;
    }

    public String getCalculation() {
      return calculation;
    }

    public long getId() {
      return id;
    }
  
  
  
    // public double getAnswer() {
    //   return answer;
    // }
  
 
  }
