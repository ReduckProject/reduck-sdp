package net.reduck.sdp.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import net.reduck.sdp.annotation.Encrypted;
import net.reduck.sdp.core.jackson.EncryptionModule;
import org.junit.jupiter.api.Test;

/**
 * @author Reduck
 * @since 2023/5/29 23:44
 */
public class ObjectMapperTest {

    @Test
    public void testMapper() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new EncryptionModule());
        Foobar foobar = new Foobar();
        foobar.setBar("test");
        foobar.setFoo("test");
        String ser = mapper.writeValueAsString(foobar);
        System.out.println(ser);

        Foobar foobarDes = mapper.readValue(ser, Foobar.class);
        System.out.println(foobarDes.toString());
    }

    @Data
    public static class Foobar {

        @Encrypted
        private String foo;

        private String bar;
    }
}
