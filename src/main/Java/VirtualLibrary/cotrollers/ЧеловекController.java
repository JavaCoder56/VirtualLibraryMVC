package VirtualLibrary.cotrollers;

import VirtualLibrary.dao.ЧеловекDAO;
import VirtualLibrary.models.Человек;
import VirtualLibrary.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/people")
public class ЧеловекController {
    private final ЧеловекDAO человекDAO;
    private final PersonValidator personValidator;

    @Autowired
    public ЧеловекController(ЧеловекDAO человекDAO, PersonValidator personValidator) {
        this.человекDAO = человекDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", человекDAO.showPeople());
        return "people/showPeople";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", человекDAO.showPerson(id));
        model.addAttribute("ownedBooks", человекDAO.ownedBooks(id));
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Человек person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Человек person, BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        человекDAO.create(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        человекDAO.delete(id);
        return "redirect: /people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", человекDAO.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("person") @Valid Человек person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        человекDAO.edit(id, person);
        return "redirect:/people";
    }
}
