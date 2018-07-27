# 修改與新增
### version 1.2.5  
1. 修改 cache 的使用。  
2. 新增 length 在串流與字串的使用。  
### version 1.2.4  
1. 修正 byte[] 讀出後寫入問題。  
2. 新增 enfile_read()。


# File-Cipher

### Class FileCipherStream

---

public class **FileCipherStream**

`FileCipherStream` 提供檔案的加密與解密。因為使用 `filestream`，所以可輕鬆應用在多數的檔案上。  

加密的類型預設為 `AES`，使用者務必要將金鑰保存好。  

### Constructor Summary
| **Constructor and Description** |
| :------ |
| FileCipherStream() |
| ```Description``` |
### Method Summary
| **Modifier and Type** | **Method and Description** |
| ------ | :------ |
| static void | file_encrypt(String file_name, byte[] key) |
|  | ```Description``` |
| static void | file_decrypt(String file_name, byte[] key) | 
|  | ```Description``` |
| static String | enfile_read(String file_name, byte[] key) |
|  | ```Description``` |


### Method Detail 
**file_encrypt**
``` java

public static void file_encrypt(String file_name, byte[] key) 
    throws Exception
/**
* Parameters:
*   file_name - 
*   key - 
* Throws:
*/
```

**file_decrypt**
``` java

public static void file_decrypt(String file_name, byte[] key) 
    throws Exception 
/**
* Parameters:
*    file_name - 
*    key - 
* Throws:
*/
```

**enfile_read**
``` java

public static String enfile_read(String file_name, byte[] key)
    throws Exception 
/**
* Parameters:
*    file_name - 
*    key - 
* Throws:
*/
```
