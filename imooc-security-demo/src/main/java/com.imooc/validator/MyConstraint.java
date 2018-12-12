package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lsq on 2018/11/23.
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})//注解可以标在哪里
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)//校验逻辑由哪个类执行
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
