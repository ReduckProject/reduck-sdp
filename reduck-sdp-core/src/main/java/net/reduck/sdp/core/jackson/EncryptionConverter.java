package net.reduck.sdp.core.jackson;

import java.util.Base64;

/**
 * @author Gin
 * @since 2023/5/29 17:51
 */
public class EncryptionConverter {
    private String key = "1234567812345678";
    static final EncryptionConverter INSTANCE = new EncryptionConverter();

    public String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public String decrypt(String data) {
        return new String(Base64.getDecoder().decode(data.getBytes()));
    }

}
