import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @version 1.2.5
 * @author Y.K
 * Github: https://github.com/48763
 * facebook page: https://www.facebook.com/Y.K.fans/?ref=bookmarks
 * Blog: https://yukifans.com
 */

public class FileCipherStream {

    private static final String KEY_ALGORTHM = "AES";
    private static final String CIPHER_ALOGORTHM = "AES/ECB/PKCS5Padding";
    private static byte[] cache = new byte[16];
    private static int length;
    private static File file;
    private static File temp_file;
    private static SecretKey secretKey;
    private static Cipher cipher;
    private static BufferedInputStream bufferedInputStream;
    private static BufferedOutputStream bufferedOutputStream;
    private static CipherInputStream cipherInputStream;
    private static CipherOutputStream cipherOutputStream;
    private static Base64.Encoder encoder = Base64.getEncoder();
    
    /**
     * 檔案加密
     */
    public static void file_encrypt(String file_name, byte[] key) throws Exception {
        /**
         * 創建暫存檔
         * 將未加密的檔案做串流加密，寫入至暫存檔
         */
        file = new File(file_name);
        temp_file = file.createTempFile("test_file", ".txt", file.getAbsoluteFile().getParentFile());

        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(temp_file));

        secretKey = new SecretKeySpec(key, KEY_ALGORTHM);
        cipher = Cipher.getInstance(CIPHER_ALOGORTHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipherInputStream = new CipherInputStream(bufferedInputStream, cipher);

        while((length = cipherInputStream.read(cache)) > 0) {
            bufferedOutputStream.write(cache, 0, length);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        cipherInputStream.close();
        bufferedInputStream.close();

        /**
         * 將已加密內容的暫存檔，寫回並覆蓋原本的檔案
         * 再將暫存檔刪除
         */
        bufferedInputStream = new BufferedInputStream(new FileInputStream(temp_file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        while((length = bufferedInputStream.read(cache)) > 0) {
            bufferedOutputStream.write(cache, 0, length);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();

        temp_file.delete();
    }

    /**
     * 檔案解密
     */
    public static void file_decrypt(String file_name, byte[] key) throws Exception {
        /**
         * 創建一暫存檔
         * 將已加密的檔案做串流解密，寫入至暫存檔
         */
        file = new File(file_name);
        temp_file = file.createTempFile("test", ".txt", file.getAbsoluteFile().getParentFile());

        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(temp_file));

        secretKey = new SecretKeySpec(key, KEY_ALGORTHM);
        cipher = Cipher.getInstance(CIPHER_ALOGORTHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        cipherOutputStream = new CipherOutputStream(bufferedOutputStream, cipher);

        while((length = bufferedInputStream.read(cache)) > 0) {
            cipherOutputStream.write(cache, 0, length);
        }
        cipherOutputStream.flush();
        cipherOutputStream.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();

        /**
         * 將已解密的暫存檔，寫回並覆蓋原本的檔案
         * 再將暫存檔刪除
         */
        bufferedInputStream = new BufferedInputStream(new FileInputStream(temp_file));
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        while((length = bufferedInputStream.read(cache)) > 0) {
            bufferedOutputStream.write(cache, 0, length);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();

        temp_file.delete();
    }

    public static String enfile_read(String file_name, byte[] key) throws Exception {
        file = new File(file_name);
        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        
        secretKey = new SecretKeySpec(key, "AES");
        cipher = Cipher.getInstance(CIPHER_ALOGORTHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        cipherInputStream = new CipherInputStream(bufferedInputStream, cipher);

        String str = "";

        while((length = cipherInputStream.read(cache)) > 0) {
            str += new String(cache, 0, length);
        }
        return str;
    }
}
