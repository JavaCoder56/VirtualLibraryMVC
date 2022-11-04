package VirtualLibrary.cotrollers;

import VirtualLibrary.dao.КнигаDAO;
import VirtualLibrary.dao.ЧеловекDAO;
import VirtualLibrary.models.Книга;
import VirtualLibrary.models.Человек;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class КнигаController {
    private final КнигаDAO книгаDAO;
    private final ЧеловекDAO человекDAO;

    public КнигаController(КнигаDAO книгаDAO, ЧеловекDAO человекDAO) {
        this.книгаDAO = книгаDAO;
        this.человекDAO = человекDAO;
    }

    @GetMapping()
    public String showBooks(Model model) {
        model.addAttribute("books", книгаDAO.showBooks());
        return "books/showBooks";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable ("id") int id, Model model, @ModelAttribute("person") Человек person) {
        model.addAttribute("people", человекDAO.showPeople());
        model.addAttribute("book", книгаDAO.showBook(id));
        model.addAttribute("owner", книгаDAO.currentOwner(id));
        return "books/showBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute ("book") Книга book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute ("book") @Valid Книга book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/new";
        }
        книгаDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", книгаDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("book") @Valid Книга book, BindingResult bindingResult,
                         @PathVariable ("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        книгаDAO.edit(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id) {
        книгаDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/receive")
    public String receiveBook(@PathVariable ("id") int id) {
        книгаDAO.receiveBook(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(@PathVariable ("id") int id, @ModelAttribute("person") Человек person){
        книгаDAO.giveBook(id, person.getId());
        return "redirect: /books/{id}";
    }
}
