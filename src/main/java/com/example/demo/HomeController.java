package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String index(Model model) {
        //Person person = new Person();
        //Pet pet = new Pet();
        model.addAttribute("Persons", PersonRepository.findAll());
        model.addAttribute("Pets", PetRepository.findAll());

        return "homepage";

    }

    @GetMapping("/addperson")
    public String personForm(Model model) {
        model.addAttribute("person", new person());
        return "personform";
    }

    @PostMapping("/process_department")
    public String processDepartmentForm(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) return "personform";
        personRepository.save(person);

        return "redirect:/personlist";
    }

    @RequestMapping("/personlist")
    public String departmentList(Model model) {
        model.addAttribute("persons", personRepository.findAll());

        return "personlist";
    }

    @GetMapping("/addemployee")
    public String personForm(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("employee", new Person());
        return "personform";
    }

    @PostMapping("/process_person")
    public String processPersonForm(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "personform";
        }
        personRepository.save(person);

        return "redirect:/employeelist";
    }

    @RequestMapping("/personlist")
    public String employeeList(Model model) {
        model.addAttribute("persons", personRepository.findAll());

        return "employeelist";


//bottom half ========================================================

        @RequestMapping("/detail/{id}")
        public String showPerson ( @PathVariable("id") long id, Model model){
            model.addAttribute("person", personRepository.findById(id).get());
            return "showperson";
        }

        @RequestMapping("/update/{id}")
        public String updatePerson ( @PathVariable("id") long id, Model model){
            model.addAttribute("person", personRepository.findById(id).get());
            return "personform";
        }

        @RequestMapping("/delete/{id}")
        public String delPerson ( @PathVariable("id") long id){
            personRepository.deleteById(id);
            return "index";
        }

        @RequestMapping("/detail_pet/{id}")
        public String showPet ( @PathVariable("id") long id, Model model){
            model.addAttribute("pet", petRepository.findById(id).get());
            return "showpet";
        }

        @RequestMapping("/update_pet/{id}")
        public String updatePet ( @PathVariable("id") long id, Model model){
            model.addAttribute("person", personRepository.findById(id).get());
            model.addAttribute("pets", petRepository.findAll());
            return "petform";
        }

        @RequestMapping("/delete_pet/{id}")
        public String delPet ( @PathVariable("id") long id){
            petRepository.deleteById(id);
            return "index";
        }

    }
}







