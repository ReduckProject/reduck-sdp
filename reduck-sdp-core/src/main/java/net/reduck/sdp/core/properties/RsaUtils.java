package net.reduck.sdp.core.properties;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Gin
 * @since 2021/6/5 17:23
 */
public class RsaUtils {

    @SneakyThrows
    public static byte[] encrypt(byte[] data, PublicKey key) {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, getPublicKey(key));
    }


    @SneakyThrows
    public static byte[] decrypt(byte[] data, PrivateKey key) {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);

    }

    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, getPrivateKey(key));
    }

    @SneakyThrows
    public static KeyPair getKeyPair(int keyLength) {
        //BC即BouncyCastle加密包，EC为ECC算法
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLength, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    @SneakyThrows
    private static PublicKey getPublicKey(byte[] publicKeyBytes) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    @SneakyThrows
    private static PrivateKey getPrivateKey(byte[] privateKeyBytes) {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = getKeyPair(2048);
        System.out.println(keyPair.getPrivate());
    }
}
