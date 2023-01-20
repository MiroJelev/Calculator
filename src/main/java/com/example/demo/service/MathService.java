package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Answer;
import com.example.demo.entity.User;
import com.example.demo.repository.MathRepository;
import com.example.demo.repository.UserRepository;
import com.fathzer.soft.javaluator.DoubleEvaluator;

@Service
public class MathService {

  private final MathRepository mathRepository;
  private final UserRepository userRepository;

  @Autowired
	public MathService(MathRepository mathRepository, UserRepository userRepository){
		this.mathRepository = mathRepository;
    this.userRepository = userRepository;
	}

  public Answer calculate(String calculation, String user_id) {
      Answer answer = null;
      try{
        DoubleEvaluator eval = new DoubleEvaluator();
        Double result = eval.evaluate(calculation);
        Optional<User> user_opt = userRepository.findById(Long.parseLong(user_id));
        User user = new User();
        if(user_opt.isPresent()){
          user = user_opt.get();
        }
        answer = new Answer( user, result, calculation);
      }catch(Exception e){

      }
      return answer;
  }

  public List<Answer> getAll(long id) {
    List<Answer> allAnswers = new ArrayList<Answer>(mathRepository.findAllByUser_Id(id));
    return allAnswers;
  }

  public Answer addNewCalc(Answer expression){
    Answer ans = null;
    Optional<Answer> calc = mathRepository.findAnswerByCalculation(expression.getCalculation());
    if(!calc.isPresent()){
      mathRepository.save(expression);
      ans = expression;
    }
    return ans;
  }

  public Answer UpdateCalc(long id, Answer expression){
    Answer ans = null;
    Optional<Answer> calc = mathRepository.findAnswerByid(id);
    if(calc.isPresent()){
      expression.setId(id);
      mathRepository.save(expression);
      ans = expression;
    }
    return ans;
  }

  public void deleteCalc(long id){
    mathRepository.deleteByid(id);
  }

  

}