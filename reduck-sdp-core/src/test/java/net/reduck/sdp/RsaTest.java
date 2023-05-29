package net.reduck.sdp;

import net.reduck.sdp.core.properties.PrivateKeyFinder;
import net.reduck.sdp.core.properties.PropertiesProtector;
import net.reduck.sdp.core.properties.RsaUtils;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author Reduck
 * @since 2023/5/29 22:37
 */
public class RsaTest {

    @Test
    public void testEncAndDec(){
        KeyPair keyPair = RsaUtils.getKeyPair(1024);


        String data = "ReduckProject";

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        byte[] enc = RsaUtils.encrypt(data.getBytes(), publicKey);
        byte[] dec = RsaUtils.decrypt(enc, privateKey);
        System.out.println(new String(dec));

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateBytes = privateKey.getEncoded();

        enc = RsaUtils.encrypt(data.getBytes(), publicKeyBytes);
        dec = RsaUtils.decrypt(enc, privateBytes);
        System.out.println(new String(dec));
    }

    @Test
    public void testGenerate() {
        KeyPair keyPair = RsaUtils.getKeyPair(2048);

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();


        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateBytes = privateKey.getEncoded();

        System.out.println(Base64.getEncoder().encodeToString(publicKeyBytes));
        System.out.println(Base64.getEncoder().encodeToString(privateBytes));
        System.out.println(Base64.getEncoder().encodeToString(new PrivateKeyFinder().encrypt(privateBytes)));
    }

    @Test
    public void testKey() {
        String encKey = PropertiesProtector.generateSecretKeyWith256();

        PropertiesProtector protector = new PropertiesProtector(encKey);

        String properties = "ReduckProject";
        String enc = protector.getSecurityProperty(properties);

        String dec = protector.getOriginalProperties(enc);

        System.out.println(enc);
        System.out.println(dec);
    }
}
