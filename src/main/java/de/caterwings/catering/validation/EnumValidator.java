package de.caterwings.catering.validation;


import de.caterwings.catering.validation.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class EnumValidator implements ConstraintValidator<Enum, String> {

    private Enum annotation;

    @Override
    public void initialize(final Enum annotation) {
        this.annotation = annotation;
    }


    @Override
    public boolean isValid(String object, ConstraintValidatorContext context) {
        if (isNull(object)) {
            return true;
        }
        boolean result = false;
        final Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (nonNull(enumValues)) {
            for (Object enumValue : enumValues) {
                final String enumValueAsString = enumValue.toString();
                if (object.equals(enumValueAsString)
                        || (this.annotation.ignoreCase() && object.equalsIgnoreCase(enumValueAsString))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
