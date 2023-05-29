package net.reduck.sdp.core.jackson;

/**
 * @author Gin
 * @since 2023/5/29 17:51
 */
public class EncryptionConverter {

    static final EncryptionConverter INSTANCE = new EncryptionConverter();

    public String encrypt(String data) {
        return data;
    }

    public String decrypt(String data) {
        return data;
    }


}
