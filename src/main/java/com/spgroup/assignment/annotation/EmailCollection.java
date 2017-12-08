package com.spgroup.assignment.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailCollectionValidator.class)
@Documented
public @interface EmailCollection {
    String message() default "Invalid/Duplicate Email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}