/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dog.dogapi;

/**
 *
 * @author Marko
 */
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends AbstractPersistable<Long>
{
  private String name;
  private String color;
  private String status;
  private String gender;
  private int fullness;
  private int age;
  private int weight;  
  private String breed;
  private int happiness;
  private boolean updateStatus = true;

  public Dog(String name, String color, String gender, int age, int weight, String breed)
  {
    this.name = name;
    this.color = color;
    this.age = age;
    this.gender = gender;
    this.fullness = 1000;
    this.weight = weight;
    this.breed = breed;
    this.happiness = 1000;
    this.status = "Dog is ok";
  }

/*********************************************************************
  M E T H O D    D E S C R I P T I O N
---------------------------------------------------------------------
 NAME: addHappiness
 DESCRIPTION: 
      Increases dogs happiness value

 Input: int amount
 Output: void
*********************************************************************/
  
  public void addHappiness(int amount)
  {
    this.happiness += amount;
      
    if (this.happiness > 1000)
      {
        this.happiness = 1000;
      }
  }

/*********************************************************************
  M E T H O D    D E S C R I P T I O N
---------------------------------------------------------------------
 NAME: removeHappiness
 DESCRIPTION: 
      Decreases dogs happiness value over time and tells when happiness
      value is 300 or below and dog needs to go out

 Input: int amount
 Output: void
*********************************************************************/  
  
  public void removeHappiness(int amount)
  {
    this.happiness -= amount;
      
    if (this.happiness <= 0) 
      {
        this.updateStatus("You need to clean.");
        this.updateStatus = false;
        this.happiness = 0;
        this.fullness = 0;
      } 
    else if (this.happiness <= 300)
      {
        this.updateStatus("Dog needs to go out.");
      }
  }
  
/*********************************************************************
  M E T H O D    D E S C R I P T I O N
---------------------------------------------------------------------
 NAME: addFullness
 DESCRIPTION: 
      Increases dogs fullness value depending on the dogs weight

 Input: int amount
 Output: void
*********************************************************************/    

  public void addFullness(int amount)
  {
    if (this.weight >= 20)
      {
        this.fullness += amount / 2;
      }
    else if (this.weight >= 40)
      {
        this.fullness += amount / 3;
      }
    else
      {
        this.fullness += amount;
      }
      
    if (this.fullness > 1000)
          this.fullness = 1000;
  }
  
/*********************************************************************
  M E T H O D    D E S C R I P T I O N
---------------------------------------------------------------------
 NAME: removeFullness
 DESCRIPTION: 
      Decreases dogs fullness value over time and tells when the value 
      is 300 or below and dog needs to eat. If the value drops down to 0
      dog will get sick.

 Input: int amount
 Output: void
*********************************************************************/

  public void removeFullness(int amount)
  {
    this.fullness -= amount;
      
    if (this.fullness <= 0) 
      {
        this.updateStatus("Dog is sick.");
        this.fullness = 0;
        this.happiness = 0;
        this.updateStatus = false;
      } 
    else if (this.fullness <= 300) 
      {
        this.updateStatus("Dog is hungry");
      } 
    else if (this.happiness > 300)
      {
        this.updateStatus("Dog is ok");
      }  
  }
  
/*********************************************************************
  M E T H O D    D E S C R I P T I O N
---------------------------------------------------------------------
 NAME: updateStatus
 DESCRIPTION: 
      Updates dogs status depending on fullness and happiness values

 Input: String statusUpdate
 Output: void
*********************************************************************/

  public void updateStatus(String statusUpdate)
  {
    if (updateStatus)
      {
        this.status = statusUpdate;
      }
  }
}
