import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String KEY_ALGORTHM = "AES";
    /**
     * AES 加密種類：
     * AES/CBC/NoPadding (128)
     * AES/CBC/PKCS5Padding (128)
     * AES/ECB/NoPadding (128) 
     * AES/ECB/PKCS5Padding (128)
     */
    public static final String CIPHER_ALOGORTHM = "AES/ECB/PKCS5Padding";

    /**
     * 產生金鑰
     * KeyGenerator.getInstance
     *      Returns a KeyGenerator object that generates secret keys for the specified algorithm.
     * SecretKey.getEncoded
     *      Returns the key in its primary encoding format
     */
    public static byte[] initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORTHM);
        //KeyGenerator.getInstance(algorithm);
        kg.init(128);
        //128, 192, 256
        SecretKey secretKey = kg.generateKey();

        return secretKey.getEncoded();
    }

    /**
     * 指定加密金鑰
     * SecretKeySpec()
     *      Constructs a secret key from the given byte array.
     * IllegalArgumentException - 
     *      if algorithm is null or key is null or empty.
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORTHM);
        return secretKey;
    }

    /**
     * 加密
     * 輸入 byte[] data, byte[] key
     * 輸出 byte[] cipher_data
     * NoSuchAlgorithmException - 
     *      if transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * NoSuchPaddingException - 
     *      if transformation contains a padding scheme that is not available.
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        
        Cipher cipher = Cipher.getInstance(CIPHER_ALOGORTHM);
        //cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        //cipher.init(opmode, certificate);
        
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * 輸入 byte[] cipher_data, byte[] key
     * 輸出 byte[] data
     * NoSuchAlgorithmException - 
     *      if transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * NoSuchPaddingException - 
     *      if transformation contains a padding scheme that is not available.
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance(CIPHER_ALOGORTHM);
        //cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, k);
        //cipher.init(opmode, certificate);
        
        return cipher.doFinal(data);
    }

    //https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html

}