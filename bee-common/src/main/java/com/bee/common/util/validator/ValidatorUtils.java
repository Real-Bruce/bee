package com.bee.common.util.validator;

import com.bee.common.exception.BeeException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description hibernate-validator校验工具类
 * <a href="http://docs.jboss.org/hibernate/validator/6.0/reference/en-US/html_single/">hibernate-validator校验工具类</a>
 */
public class ValidatorUtils {

    /**
     * 校验entity
     * @param object
     * @param groups
     * @throws BeeException
     */
    public static void validateEntity(Object object, Class<?>... groups) throws BeeException {
        Locale.setDefault(LocaleContextHolder.getLocale());

        Validator validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(getMessageResource())))
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constrain = constraintViolations.iterator().next();
            throw new BeeException(constrain.getMessage());
        }

    }

    private static ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("i18n/validation");
        return messageSource;
    }

}
