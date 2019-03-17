package com.hendzior.web.controller;

import com.hendzior.core.dao.*;
import com.hendzior.core.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("visits")
public class VisitController {

    private VisitDataAccess visitDataAccess;
    private AnimalDataAccess animalDataAccess;

    public VisitController(VisitDataAccess visitDataAccess, AnimalDataAccess animalDataAccess) {
        this.visitDataAccess = visitDataAccess;
        this.animalDataAccess = animalDataAccess;
    }

    @GetMapping
    public String getVisits(ModelMap modelMap) {
        modelMap.addAttribute("visits", visitDataAccess.findAll());
        return "show";
    }

    @GetMapping("/add")
    public String createVisit(ModelMap modelMap) {
        if (!modelMap.containsAttribute("visit")) {
            modelMap.addAttribute("visit", new Visit());
        }
        modelMap.addAttribute("animals", animalDataAccess.findAll());
        return "addvisit";
    }

    @PostMapping
    public String saveVisit(@ModelAttribute @Valid Visit visit, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.visit", bindingResult);
            redirectAttributes.addFlashAttribute("visit", visit);
            return "redirect:/visits/add";
        }
        visitDataAccess.save(visit);
        modelMap.addAttribute("message", "Visit saved!");
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteVisit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Visit> optionalVisit = visitDataAccess.findById(id);
        if (optionalVisit.isPresent()) {
            visitDataAccess.delete(optionalVisit.get());
            redirectAttributes.addFlashAttribute("message", "Visit : " + optionalVisit.get().getId() + " deleted!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Visit with id : " + id + " not found!");
        }
        return "redirect:/visits";
    }

    @GetMapping("/{id}/edit")
    public String editAnimal(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        Optional<Visit> optionalVisit = visitDataAccess.findById(id);
        if (optionalVisit.isPresent()) {
            redirectAttributes.addFlashAttribute("visit", optionalVisit.get());
            return "redirect:/visits/add";
        }
        redirectAttributes.addFlashAttribute("visit with id: " + id + " not found!");
        return "redirect:/visit";
    }

}
