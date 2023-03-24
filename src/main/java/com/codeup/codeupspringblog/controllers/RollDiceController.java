package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(path = "/roll-dice")
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollTheDice(){
        return ("roll-dice");
    }

    @GetMapping("/roll-dice/{n}")
    public String rollTheDice(@PathVariable int n, Model model){

        int randomNumber = 0;
        int correctCount = 0;
        String result = "";

//        for (int i = 1; i <= 5; i++){

            randomNumber = (int) (Math.random()*(6)) + 1;

            if (n == randomNumber){
                result = "Correct";
                correctCount += 1;
            } else {
                result = "Wrong";
            }

//            model.addAttribute("counter", i);

            model.addAttribute("randomRoll", "The correct answer is: " + randomNumber);
            model.addAttribute("guessedRoll", "You guessed: " + n);
            model.addAttribute("resultRoll", "Your guess was: " + result);
//        }

//        model.addAttribute("timesCorrect", "Total correct are: " + correctCount);

        return ("roll-dice");
    }

}
