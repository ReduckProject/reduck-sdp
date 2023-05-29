package net.reduck.sdp.core.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Gin
 * @since 2023/5/29 17:22
 */
@Converter
public class SensitiveConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return null;
    }
}
