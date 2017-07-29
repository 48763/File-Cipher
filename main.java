import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class main {

    public static void main(String args[]) throws Exception {
        
        String file_name =  "test_file.jpg";
        byte[] key = AES.initKey();
        FileCipherStream.file_encrypt(file_name, key);
        FileCipherStream.file_decrypt(file_name, key);     

    }

}