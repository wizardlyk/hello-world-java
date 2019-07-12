package zz.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lyk
 * @Date 2019/7/3 20:54
 * @Version 1.0
 **/
@Constraint(validatedBy = {MyConstraintValidator.class})
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConstraint {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
