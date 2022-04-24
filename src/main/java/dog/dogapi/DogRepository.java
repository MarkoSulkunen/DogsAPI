/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dog.dogapi;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marko
 */
public interface DogRepository extends JpaRepository<Dog, Long>
{
  @Override
  ArrayList<Dog> findAll();

  ArrayList<Dog> findByName(String name);

  Long deleteByName(String name);
}