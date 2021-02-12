package com.muver.charsapp;

import android.util.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CharEncoder {

    public static String encoding(String container, String message, String key, EncodingType type) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, TooSmallContainerException {
        String num = DesEncoder.encoding(message, key);
        switch (type) {
            case Standard:
                String length = DesEncoder.encoding(Integer.toString(message.length()), key);
                container = ReplaceEncoder.encoding(container, length);
                container = InsertEncoder.encoding(container, num, -1);
                break;
            case MaxCapacity:
                String l = ReplaceEncoder.maxCapacity(container);
                l = l.substring(0, l.length() > 10 ? l.length()-10 : l.length());
                int replaceLength = Math.min(l.length(), num.length()-1);
                container = ReplaceEncoder.encoding(container, num.substring(0, replaceLength));
                num = num.substring(replaceLength);
                if (!num.isEmpty())
                    container = InsertEncoder.encoding(container, num, -1);
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

    public static String decoding(String container, String key, EncodingType type) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidChecksumException {
        String message = "";
        switch (type){
            case Standard:
                String length = DesEncoder.decoding(ReplaceEncoder.decoding(container), key);
                message = InsertEncoder.decoding(container);
                message = DesEncoder.decoding(message, key);
                if (message.length() != Integer.parseInt(length))
                    throw new InvalidChecksumException();
                break;
            case MaxCapacity:
                message += ReplaceEncoder.decoding(container);
                message += InsertEncoder.decoding(container);
                message = DesEncoder.decoding(message, key);
                break;
            case OnlyInsert:
                message = InsertEncoder.decoding(container);
                message = DesEncoder.decoding(message, key);
                break;
            case OnlyReplace:
                message = ReplaceEncoder.decoding(container);
                message = DesEncoder.decoding(message, key);
                break;
            default:
                break;
        }
        return message;
    }
}
