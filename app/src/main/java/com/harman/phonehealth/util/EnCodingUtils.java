package com.harman.phonehealth.util;

import android.content.Context;
import android.util.Log;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.database.entity.PublicKey;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Encoding and Decoding Util
 * */
public class EnCodingUtils {
    public static EnCodingUtils mEnCodingUtils;
    public static Cipher mCipher;
    public final static int AES_MODE = 0;
    public final static int DES_MODE = 1;
    public final static int RAS_MODE = 2;
    private int mEncodeMode = 0;
    public Context mcontext;
    public static Key desKey = null;

    public static EnCodingUtils getInstance(Context context){
        return EnCodingUtilsHolder.instance;
    }

    private static class EnCodingUtilsHolder{
        private static final EnCodingUtils instance=new EnCodingUtils();
     }

    public  void init(int encodeMode){
        if (mCipher==null){
            try {
                switch (encodeMode){
                    case DES_MODE:
                        byte keybytes[] = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            List<PublicKey> publicKeyList = RoomUtils.InitDataBase(PhoneHealthApp.sApplication).keyDao().findKeyWithMode(encodeMode);
                           if (publicKeyList!=null||publicKeyList.size()!=0) {
                               Log.i("PHoneHealthAPP", "get database  strKey= " +publicKeyList.get(0).getKeyValue());
                               keybytes = Base64.getDecoder().decode(publicKeyList.get(0).getKeyValue());
                           }
                        }
                        if(keybytes==null||keybytes.length==0){
                            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
                            //指定生成的密钥长度为128
                            keyGenerator.init(128);
                            desKey = keyGenerator.generateKey();
                            if (desKey!=null){
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    String strKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
                                    PublicKey publicKey = new PublicKey();
                                    publicKey.setKeyValue(strKey);
                                    publicKey.setKeyMode(encodeMode);
                                    RoomUtils.InitDataBase(PhoneHealthApp.sApplication).keyDao().insertKeys(publicKey);
                                    Log.i("PHoneHealthAPP","insert database  strKey= "+strKey);
                                }
                            }
                        }else{
                            DESedeKeySpec deSedeKeySpec= null;
                            try {
                                deSedeKeySpec = new DESedeKeySpec(keybytes);
                                SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");
                                desKey= secretKeyFactory.generateSecret(deSedeKeySpec);
                            } catch (InvalidKeyException | InvalidKeySpecException e) {
                                e.printStackTrace();
                            }
                        }
                        mCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                        break ;
                    case AES_MODE:
                        break;
                    case RAS_MODE:
                        break;
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] DESEncode(byte[] in){
        if (desKey==null||mCipher==null){
            return null;
        }
        byte[] bytes = null;
        try {
            mCipher.init(Cipher.ENCRYPT_MODE, desKey);
            bytes = mCipher.doFinal(in);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] DESDecode(byte[] in){
        if (desKey==null||mCipher==null){
            return null;
        }
        byte[] bytes = null;
        try {
            mCipher.init(Cipher.DECRYPT_MODE, desKey);
            bytes = mCipher.doFinal(in);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
