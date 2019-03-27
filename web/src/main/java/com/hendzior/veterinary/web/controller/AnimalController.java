package com.hendzior.veterinary.web.controller;

import com.hendzior.veterinary.core.DataInputValidator;
import com.hendzior.veterinary.core.dao.AnimalDataAccess;
import com.hendzior.veterinary.core.dao.CustomerDataAccess;
import com.hendzior.veterinary.core.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequestMapping("/animals")
@Controller
public class AnimalController {

    private AnimalDataAccess animalDataAccess;
    private CustomerDataAccess customerDataAccess;
    private DataInputValidator dataInputValidator;

    public AnimalController(AnimalDataAccess animalDataAccess, CustomerDataAccess customerDataAccess, DataInputValidator dataInputValidator) {
        this.animalDataAccess = animalDataAccess;
        this.customerDataAccess = customerDataAccess;
        this.dataInputValidator = dataInputValidator;
    }

    @GetMapping
    public String getAnimals(ModelMap modelMap) {
        modelMap.addAttribute("animals", animalDataAccess.findAll());

        return "show";
    }

    @GetMapping("/add")
    public String createAnimal(ModelMap modelMap) {
        if (!modelMap.containsAttribute("animal")) {
            modelMap.addAttribute("animal", new Animal());
        }
        modelMap.addAttribute("customers", customerDataAccess.findAll());
        return "addanimal";
    }

    @PostMapping
    public String saveAnimal(@ModelAttribute @Valid Animal animal, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
      if(bindingResult.hasErrors()){
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.animal", bindingResult);
          redirectAttributes.addFlashAttribute("animal", animal);
          return "redirect:/animals/add";
      }
        animalDataAccess.save(animal);
        modelMap.addAttribute("message", "animal : " + animal.getName() + " added!");
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteAnimal(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Animal> optionalAnimal = animalDataAccess.findById(id);
        if (optionalAnimal.isPresent()) {
            animalDataAccess.delete(optionalAnimal.get());
            redirectAttributes.addFlashAttribute("message", "Animal : " + optionalAnimal.get().getName() + " deleted!");

        } else {
            redirectAttributes.addFlashAttribute("message", "Animal with id : " + id + " not found!");
        }
        return "redirect:/animals";
    }

    @GetMapping("/{id}/edit")
    public String editAnimal(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        Optional<Animal> optionalAnimal = animalDataAccess.findById(id);
        if (optionalAnimal.isPresent()) {
            redirectAttributes.addFlashAttribute("animal", optionalAnimal.get());
            return "redirect:/animals/add";
        }
        redirectAttributes.addFlashAttribute("animal with id: " + id + " not found!");
        return "redirect:/animals";
    }
}
