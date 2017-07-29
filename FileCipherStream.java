import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @version 1.2.2
 * @author Y.K
 * Github: https://github.com/48763
 * facebook page: https://www.facebook.com/Y.K.fans/?ref=bookmarks
 * Blog: https://yukifans.com
 */

public class FileCipherStream {

    private static final String KEY_ALGORTHM = "AES";
    private static final String CIPHER_ALOGORTHM = "AES/ECB/PKCS5Padding";
    private static File file;
    private static File temp_file;
    private static SecretKey secretKey;
    private static Cipher cipher;
    private static BufferedInputStream bufferedInputStream;
    private static BufferedOutputStream bufferedOutputStream;
    private static CipherInputStream cipherInputStream;
    private static CipherOutputStream cipherOutputStream;
    
    public static void file_encrypt(String file_name, byte[] key) throws Exception {
        file = new File(file_name);
        temp_file = file.createTempFile("test_file", ".txt", file.getAbsoluteFile().getParentFile());

        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(temp_file));

        secretKey = new SecretKeySpec(key, "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipherInputStream = new CipherInputStream(bufferedInputStream, cipher);

        byte[] cache = new byte[16];

        while(cipherInputStream.read(cache) > 0) {
            bufferedOutputStream.write(cache);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        cipherInputStream.close();
        bufferedInputStream.close();

        bufferedInputStream = new BufferedInputStream(new FileInputStream(temp_file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        cache = new byte[16];

        while(bufferedInputStream.read(cache) > 0) {
            bufferedOutputStream.write(cache);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();

        temp_file.delete();
    }

    public static void file_decrypt(String file_name, byte[] key) throws Exception {
        file = new File(file_name);
        temp_file = file.createTempFile("test", ".txt", file.getAbsoluteFile().getParentFile());

        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(temp_file));

        secretKey = new SecretKeySpec(key, "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        cipherOutputStream = new CipherOutputStream(bufferedOutputStream, cipher);

        byte[] cache = new byte[16];

        while(bufferedInputStream.read(cache) > 0) {
            cipherOutputStream.write(cache);
        }
        cipherOutputStream.flush();
        cipherOutputStream.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();

        bufferedInputStream = new BufferedInputStream(new FileInputStream(temp_file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        cache = new byte[16];

        while(bufferedInputStream.read(cache) > 0) {
            bufferedOutputStream.write(cache);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();

        temp_file.delete();
    }

}