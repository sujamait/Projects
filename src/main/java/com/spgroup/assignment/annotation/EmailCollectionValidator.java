package com.spgroup.assignment.annotation;

import java.util.Collection;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import com.spgroup.assignment.constants.CommonConstants;

public class EmailCollectionValidator implements ConstraintValidator<EmailCollection, Collection<String>> {

    @Override
    public void initialize(EmailCollection constraintAnnotation) {

    }

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        EmailValidator validator = new EmailValidator();

        for (String s : value) {            
            if (!validator.isValid(s, context) || !s.contains(CommonConstants.DOT)) {
                return false;
            }
        }
        
        //Check If EmailId's are same
        boolean allEqual = true;
        if(value.size()>1)
        for (String s : value) {
        	allEqual = true;
            if(s.equalsIgnoreCase((String) value.toArray()[0]))
                allEqual = false;
        }
        return allEqual;
    }


}