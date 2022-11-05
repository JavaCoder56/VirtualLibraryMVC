package VirtualLibrary.util;

import VirtualLibrary.dao.ЧеловекDAO;
import VirtualLibrary.models.Человек;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PersonValidator implements Validator {
    private final ЧеловекDAO человекDAO;

    @Autowired
    public PersonValidator(ЧеловекDAO человекDAO) {
        this.человекDAO = человекDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Человек.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Человек person = (Человек) o;
        if(человекDAO.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName","", "Человек с таким именем уже существует");
        }
    }
}
