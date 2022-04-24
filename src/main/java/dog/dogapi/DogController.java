/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dog.dogapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/**
 *
 * @author Marko
 */
public class DogController 
{
  @Autowired
  private DogService dogService;

  @GetMapping("/viewDogs")
  public String getAllDogs(Model model)
  {
    dogService.getTime();
    List<Dog> dogArray = dogService.findAllDogs();
    model.addAttribute("Dogs", dogArray);
    return "viewDogs";
  }

  @PostMapping("/viewDogs")
  public String addDog(@RequestParam String dogName, String dogColor, String dogAge, String dogGender, String dogWeight, String dogBreed)
  {
    dogService.getTime();
    dogService.addDog(dogName, dogColor, dogGender, Integer.parseInt(dogAge), Integer.parseInt(dogWeight), dogBreed);
    return "redirect:/viewDogs";
  }

  @GetMapping("/hungry")
  public String getHungryDogs(Model model)
  {
    dogService.getTime();
    ArrayList<Dog> dogs = dogService.getDogs();
    model.addAttribute("Dogs", dogs);
    return "hungry";
  }

  @PostMapping("/feed")
  public String feedDog(@RequestParam String dogName)
  {
    dogService.getTime();
    dogService.addFullness(dogName);
    return "redirect:/hungry";
  }
  
  @GetMapping("viewDogs/{dogName}/walk")
  public String getDogToWalk(@PathVariable String dogName, Model model)
  {
    dogService.getTime();
    Dog dog = dogService.findDogByName(dogName);
    model.addAttribute("dog", dog);
    return "walk";
  }

  @PostMapping("viewDogs/{dogName}/addHappiness")
  public String addHappinessToDog(@RequestParam String dogName)
  {
    dogService.getTime();
    dogService.addHappiness(dogName);
    return "redirect:/viewDogs";
  }
  
  @PostMapping("/delete")
  public String deleteDog(@RequestParam String dogName)
  {
    dogService.getTime();
    dogService.deleteDog(dogName);
    return "redirect:/viewDogs";
  }
}
