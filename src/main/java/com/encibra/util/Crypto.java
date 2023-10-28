package com.encibra.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Crypto {
    private static final String CHAVE = "123";
    private static final String KEY_ALGORITMO = "PBKDF2WithHmacSHA256";
    private static final String ENC_ALGORITMO = "AES";

    private Crypto() {
        throw new IllegalStateException("Classes staticas não devem ser instanciadas.");
    }

    public static String criptografar(String senha) {
        try {
            byte[] salt = { 1, 2, 3, 4, 5, 6, 7, 8 };
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITMO);
            KeySpec spec = new PBEKeySpec(CHAVE.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey chave = new SecretKeySpec(tmp.getEncoded(), ENC_ALGORITMO);
            Cipher cipher = Cipher.getInstance(ENC_ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] senhaCriptografada = cipher.doFinal(senha.getBytes());
            return Base64.getEncoder().encodeToString(senhaCriptografada);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String descriptografar(String senhaCriptografada) {
        try {
            byte[] salt = { 1, 2, 3, 4, 5, 6, 7, 8 };
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITMO);
            KeySpec spec = new PBEKeySpec(CHAVE.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey chave = new SecretKeySpec(tmp.getEncoded(), ENC_ALGORITMO);
            Cipher cipher = Cipher.getInstance(ENC_ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] senhaDescriptografada = cipher.doFinal(Base64.getDecoder().decode(senhaCriptografada));
            return new String(senhaDescriptografada);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
