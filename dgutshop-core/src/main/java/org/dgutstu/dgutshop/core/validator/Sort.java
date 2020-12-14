package org.dgutstu.dgutshop.core.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * @Author: leesk
 * @Description:校验用户请求参数值只能是add_time或者id
 * @Date: Create in 19:29 2020/12/12
 */
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SortValidator.class)
public @interface Sort {
    String message() default "排序字段不支持";

    String[] accepts() default {"create_time", "id"};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
