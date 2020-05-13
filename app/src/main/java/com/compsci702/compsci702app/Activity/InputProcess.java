package com.compsci702.compsci702app.Activity;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class InputProcess {
    private static final String Salt = "03xy9z52twq8r4s1uv67";
    private static final String SECRET_KEY_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";

    @SuppressLint("GetInstance")
    public byte[] encrypt(String plainText) {

        byte[] bytePlaintext = plainText.getBytes();

        byte[] byteCipherText = new byte[0];
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            //Encrypt fn
            String stringKey = Base64.encodeToString(xyz().getEncoded(), Base64.DEFAULT);
            byte[] encodedKey = Base64.decode(stringKey, Base64.DEFAULT);
            SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            assert cipher != null;
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byteCipherText = cipher.doFinal(bytePlaintext);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return byteCipherText;
    }

    private SecretKey xyz() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            throw new IllegalStateException();
        } catch (Exception e) {
            String pw = "9081726354fabced";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(pw.toCharArray(), Salt.getBytes("UTF-8"), 100, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
            SecretKey tmp = factory.generateSecret(pbeKeySpec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            return secretKey;
        }
    }
}