package logic;

import java.util.Base64;

/**
 * Encryption
 */
public class Encryption {

    static Base64.Encoder encryptor = Base64.getEncoder();
    static  Base64.Decoder decryptor = Base64.getDecoder();

    static String encrypt (String text){
        String encoded = encryptor.encodeToString(text.getBytes());
        return encoded;
    }

    static String decrypt (String encoded){
        String decoded = new String(decryptor.decode(encoded));
        return decoded;
    }

}