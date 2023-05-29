package net.reduck.sdp.core.properties;

/**
 * @author Gin
 * @since 2023/5/23 19:46
 */
public class PropertiesProtector {
    private final EncryptionWrapperDetector wrapperDetector = new EncryptionWrapperDetector("$ENC{", "}");
    private final AesEncryptor encryptor;

    public PropertiesProtector(String secretKey) {
        this.encryptor = new AesEncryptor(PrivateKeyFinder.getSecretKey(secretKey));
    }

    public String getSecurityProperty(String property) {
        return wrapperDetector.wrapper(encryptor.encrypt(property));
    }

    public String getOriginalProperties(String property) {
        return encryptor.decrypt(wrapperDetector.unWrapper(property));
    }

    public static String generateSecretKey() {
        return PrivateKeyFinder.generateSecretKey();
    }

    public static String generateSecretKeyWith256() {
        return PrivateKeyFinder.generateSecretKeyWith256();
    }
}
