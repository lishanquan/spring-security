package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by lsq on 2018/11/23.
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

    /**
     * 这个类里可以通过@Autowired注入Spring容器里任何内容，并且不需要在类上边添加@Component等注解，
     * 因为实现了ConstraintValidator接口之后，会自动把该类标记为Spring容器的一个Bean
     */

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        System.out.println(value);

        return false;
    }
}
