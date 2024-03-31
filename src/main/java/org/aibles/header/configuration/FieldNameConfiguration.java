package org.aibles.header.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FieldNameConfiguration {

    private static String fieldName;

    @Value("${org.aibles.field-name:user_id}")
    public void setFieldName(String fieldName) {
        FieldNameConfiguration.fieldName = fieldName;
    }

    public static String getFieldName() {
        return fieldName;
    }
}
