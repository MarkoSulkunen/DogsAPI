/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dog.dogapi;


import java.util.List;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

/**
 *
 * @author Marko
 */
public class DogService {
  Long timeOnLastUpdate = null;

  @Autowired
  private DogRepository dogRepository;

  public ArrayList<Dog> getDogs() 
  {
    return this.dogRepository.findAll();
  }

  public void addDog(String name) 
  {
      this.dogRepository.save(new Dog(name, "brown", "male", 1, 1, "golden retriever"));
  }

  public void addDog(String name, String color, String gender, int age, int weight, String breed) 
  {
    try 
      {
        Dog dog = this.dogRepository.findByName(name).get(0);
      } 
    catch (Exception e) 
      {
        this.dogRepository.save(new Dog(name, color, gender, age, weight, breed));
      }
    getTime();
  }

  public List<Dog> findAllDogs() 
  {
    return this.dogRepository.findAll();
  }

  public Dog findDogByName(String dogName) 
  {
    try 
      {
        return this.dogRepository.findByName(dogName).get(0);
      } 
    catch (Exception e) 
      {
        System.out.println("No dog found with that name");
        return null;
      }
  }

  public void addHappiness(String dogName) 
  {
    Dog dog = this.dogRepository.findByName(dogName).get(0);
    dog.addHappiness(1000);
    this.dogRepository.save(dog);
  }

  public void removeHappiness(String dogName) 
  {
    Dog dog = this.dogRepository.findByName(dogName).get(0);
    dog.addHappiness(500);
    this.dogRepository.save(dog);
  }

  public void addFullness(String dogName) 
  {
    Dog dog = this.dogRepository.findByName(dogName).get(0);
    dog.addFullness(500);
    this.dogRepository.save(dog);
  }

  @Transactional
  public void deleteDog(String dogName) 
  {
    this.dogRepository.deleteByName(dogName);
  }

  public void getTime() 
  {
    Long timeNow = new Date().getTime();

    if (timeOnLastUpdate == null) 
      {
        timeOnLastUpdate = timeNow;
      } 
    else 
      {
        int timeDifference = (int) (timeNow - timeOnLastUpdate);
        
        if (timeDifference > 100) 
          {
            timeOnLastUpdate = timeNow;
          }
        
        ArrayList<Dog> dogs = this.dogRepository.findAll();
        
        for (Dog dog : dogs) 
          {
            dog.removeHappiness(timeDifference / 100);
            dog.removeFullness(timeDifference / 100);
            this.dogRepository.save(dog);
          }
      }
  }
}
