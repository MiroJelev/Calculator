package com.example.demo.controller;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Answer;
import com.example.demo.service.MathService;

@RestController
@RequestMapping("/api/v1/math")//path = 
public class MathController {
    
    MathService mathService;

    @Autowired
    public MathController(MathService mathService){
        this.mathService = mathService;
    }

    @GetMapping(value = "/freecalc")
    @ResponseBody
	public ResponseEntity<Answer> getFreeCalc(@RequestParam(value="calculation", defaultValue="") String calculation){
        return ResponseEntity.ok(mathService.calculate(calculation, "0"));
	}

    @GetMapping(value = "/calc")
    @ResponseBody
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<Answer> getCalc(@RequestParam(value="calculation", defaultValue="") String calculation, @RequestParam(value="id", defaultValue="") String user_id){
		return ResponseEntity.ok(mathService.calculate(calculation, user_id));
	}


    @PostMapping(value = "/postcalc")
    @ResponseBody
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public void PostCalc(@RequestParam(value="calculation") String calculation, @RequestParam(value="id") String user_id){
            Answer ans = mathService.calculate(calculation, user_id);
            mathService.addNewCalc(ans);
	}

    @PutMapping(value = "/putcalc")
    @ResponseBody
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public void PutCalc(@RequestParam(value="calculation") String calculation, @RequestParam(value="id") String user_id, @RequestParam(value="expid") long expr_id){
            Answer ans = mathService.calculate(calculation, user_id);
            mathService.UpdateCalc(expr_id, ans);
	}

    @DeleteMapping(value = "/delcalc")
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    public void DeleteCalcRequest(@RequestParam(value="id") long id){
        mathService.deleteCalc(id);
    }

    

    //add to user variables list 
    //when user adds variable to the list it can be used in expressions
    //??arbitrry number of arguments of Get request

    /*
     add expression evaluation
     maybe add set variable
     PUT
     UI


     deleteCalc
     */


}

/*
    @PostMapping(value = "/")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
*/
//GET
//http://localhost:8080/api/v1/student/
//curl http://localhost:8080/api/v1/student/
//POST
//curl -X POST -H "Content-Type: application/json" -d '{"name": "bosch", "email": "haha@tu-sofiq.bg", "dob":"1923-12-13"}' http://localhost:8080/api/v1/student/