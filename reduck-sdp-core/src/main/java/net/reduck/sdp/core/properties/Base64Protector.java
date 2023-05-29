package net.reduck.sdp.core.properties;

import java.util.Base64;

/**
 * @author Reduck
 * @since 2023/5/29 23:58
 */
public class Base64Protector implements Encryptor {

    @Override
    public String encrypt(String message) {
        return message == null ? null : Base64.getEncoder().encodeToString(message.getBytes());
    }

    @Override
    public String decrypt(String message) {
        return message == null ? null : new String(Base64.getDecoder().decode(message.getBytes()));
    }
}
