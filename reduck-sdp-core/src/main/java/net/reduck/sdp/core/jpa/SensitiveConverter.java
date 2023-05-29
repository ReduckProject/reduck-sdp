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
        // TODO: 2023/5/30 加密逻辑 
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // TODO: 2023/5/30 解密逻辑 
        return null;
    }
}
