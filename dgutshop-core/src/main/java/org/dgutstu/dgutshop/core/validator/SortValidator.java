package org.dgutstu.dgutshop.core.validator;

import com.google.common.collect.Lists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @Author: leesk
 * @Description:自定义Validators
 * @Date: Create in 19:36 2020/12/12
 */
public class SortValidator implements ConstraintValidator<Sort, String> {
    private List<String> valueList;

    @Override
    public void initialize(Sort sort) {
        valueList = Lists.newArrayList();
        for(String val : sort.accepts()){
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!valueList.contains(s.toUpperCase())){
            return false;
        }
        return true;
    }
}
