package com.boxi.core.errors;

import com.boxi.core.conf.PropConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 2937969386484720825L;

    private static  PropConf propConfig;

    @Autowired
    public BusinessException(PropConf propConfig) {
        BusinessException.propConfig = propConfig;
    }


    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }


    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }


    public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, Integer id, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id.toString());
        return throwException(exceptionType, messageTemplate, args);
    }


    public static RuntimeException valueException(EntityType entityType, String messageTemplate, String... args) {
        return new CustomException(format(messageTemplate, args));
    }

    public static RuntimeException entityNotFoundException(EntityType entityType, String messageTemplate, String... args) {
        return new EntityNotFoundException(format(messageTemplate, args));
    }




    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        }
        else if (ExceptionType.EXCEL_VALIDATION.equals(exceptionType)) {
            return new ExcelException(format(messageTemplate, args));
        }
        else if (ExceptionType.VALIDATION_EXCEPTION.equals(exceptionType)) {
            return new CustomException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propConfig.getConfigValue(template));
        return templateContent.map(s -> MessageFormat.format(s, (Object[]) args)).orElseGet(() -> String.format(template, (Object[]) args));
    }

    public static class EntityNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 8490379462774046464L;

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {

        private static final long serialVersionUID = 6097697152768645228L;

        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class CustomException extends RuntimeException {

        private static final long serialVersionUID = -1584388453062884064L;

        public CustomException(String message) {
            super(message);
        }
    }

    public static class ExcelException extends RuntimeException {
        public ExcelException(String message) {
            super(message);
        }
    }



    public static class ParentNotFoundException extends RuntimeException {
        public ParentNotFoundException(String message) {
            super(message);
        }
    }

}
