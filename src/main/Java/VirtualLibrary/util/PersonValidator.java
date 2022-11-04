package VirtualLibrary.util;

import VirtualLibrary.dao.ЧеловекDAO;
import VirtualLibrary.models.Человек;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        if(person.getFullName().isEmpty()) {
            errors.rejectValue("fullName","", "Имя не может быть пустым");
        }
        if (person.getFullName().length()<8||person.getFullName().length()>60) {
            errors.rejectValue("fullName","", "FullName length should be between 8 and 60 characters");
        }
    }
}
