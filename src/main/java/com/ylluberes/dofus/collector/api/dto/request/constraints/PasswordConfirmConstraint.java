package com.ylluberes.dofus.collector.api.dto.request.constraints;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = PasswordEqualsConstraintValidator.class)
    public @interface PasswordConfirmConstraint {
        String message() default "asdasd";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
