package com.ylluberes.dofus.collector.api.dto.request.constraints;

import com.ylluberes.dofus.collector.api.dto.request.InAuthSignUp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * source: https://stackoverflow.com/questions/7239897/spring-3-annotation-based-validation-password-and-confirm-password
 */

/**
 * <p>
 *
 * TODO: In <method>isValid</method> we have conditions to validate <param> [candidate] <param> object and password, confirm-password attributes, witch are also validated
 * TODO: in InAuthSignUp class with javax validation annotations (see com.ylluberes.dofus.collector.api.dto.request.InAuthSignUp[password] -> @NotNull tag),
 * TODO: witch is redundant? check if field validations can be executed before custom class level constraint validations
 * </p>
 */
public class PasswordEqualsConstraintValidator implements ConstraintValidator<PasswordConfirmConstraint, InAuthSignUp> {

    @Override
    public void initialize(PasswordConfirmConstraint arg0) {

    }

    @Override
    public boolean isValid(InAuthSignUp candidate, ConstraintValidatorContext arg1) {
        if(candidate != null){
            if(candidate.getPassword() != null && candidate.getPassword() != null){
                return candidate.getPassword().equals(candidate.getConfirmPassword());
            }
        }
        return false;
    }
}