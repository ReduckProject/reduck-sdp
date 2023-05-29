package net.reduck.sdp.core.properties;

/**
 * @author Gin
 * @since 2023/5/6 14:54
 */
public interface Encryptor {

    String encrypt(String message);

    String decrypt(String message);
}
