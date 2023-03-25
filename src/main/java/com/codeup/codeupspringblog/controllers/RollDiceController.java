package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Rolls;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        String result2 = "";

        randomNumber = (int) (Math.random()*(6)) + 1;

        if (n == randomNumber){
            result = "Correct";
        } else {
            result = "Wrong";
        }

        model.addAttribute("randomRoll", "The correct answer is: " + randomNumber);
        model.addAttribute("guessedRoll", "You guessed: " + n);
        model.addAttribute("resultRoll", "Your guess was: " + result);

        List<Rolls> rolls = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            randomNumber = (int) (Math.random()*(6)) + 1;

            if (n == randomNumber){
                result2 = "Correct";
                correctCount += 1;
            } else {
                result2 = "Wrong";
            }
            rolls.add(new Rolls(randomNumber, n, result2));
        }

        model.addAttribute("rolls", rolls);

        model.addAttribute("timesCorrect", "Total correct are: " + correctCount);

        return ("roll-dice");
    }

}
