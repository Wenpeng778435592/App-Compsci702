package com.compsci702.compsci702app.Activity;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class outputProcess {
    private String input1 = "drzy4cHujrlazKIS";
    private String strDecryptedText;
    private String input2 = "fNgcFOk2ItFTjp/Ffhy";
    private String fence;

    public String decrypt(byte[] byteCipherText) {
        Cipher cipher = null;
//        MainActivity ma = new MainActivity();
        String input1b = GameActivity.input1b;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            if (!input1.equals(input2)) {
                throw new IllegalStateException();
            }
        }
        catch (Exception e) {
            fence =  "" + input1 + input1b + "U" + input2 + "Q" + "=" + "";
        }

        byte[] encodedKey = Base64.decode(fence, Base64.DEFAULT);
        SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        //Decrypt fn
        try {
            if (cipher != null) {
                cipher.init(Cipher.DECRYPT_MODE, originalKey);
            }
            byte[] byteDecryptedText = new byte[0];
            if (cipher != null) {
                byteDecryptedText = cipher.doFinal(byteCipherText);
            }
            strDecryptedText = new String(byteDecryptedText);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }
}








