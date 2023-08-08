package io.github.WesleyViricimo.validation;

import io.github.WesleyViricimo.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Indica que deve ser realizada a verificação em tempo de execução
@Target(ElementType.FIELD) //Indica que a classe deve ser utilizada nos campos
@Constraint(validatedBy = NotEmptyListValidator.class) //Indica que a classe que está a Constraint validator
public @interface NotEmptyList { //Anotação customizada

    String message() default "A lista não pode ser vazia!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
