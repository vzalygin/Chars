package com.muver.chars.lib;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CharEncoder {

    public static String encoding(String container, String message, String key, EncodingType type) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String num = DesEncoder.encoding(message, key);
        switch (type) {
            case Optimal:
                int length = message.length();
                container = InsertEncoder.encoding(container, num, -1);
                container = ReplaceEncoder.encoding(container, Integer.toString(length));
                break;
            case MaxCapacity:
                break;
            case OnlyInsert:
                container = InsertEncoder.encoding(container, num, -1);
                break;
            case OnlyReplace:
                container = ReplaceEncoder.encoding(container, num);
                break;
            default:
                break;
        }
        return container;
    }

    public static String decoding(String container, String key, EncodingType type) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String message = "";
        switch (type){
            case Optimal:
                int length = Integer.parseInt(ReplaceEncoder.decoding(container));
                message = InsertEncoder.decoding(container);
                break;
            case MaxCapacity:
                break;
            case OnlyInsert:
                message = InsertEncoder.decoding(container);
                break;
            case OnlyReplace:
                message = ReplaceEncoder.decoding(container);
                break;
            default:
                break;
        }
        message = DesEncoder.decoding(message, key);
        return message;
    }
}
