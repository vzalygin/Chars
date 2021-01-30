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
                int length = message.length();
                container = InsertEncoder.encoding(container, num, -1);
                container = ReplaceEncoder.encoding(container, Integer.toString(length));
                break;
            case MaxCapacity:
                String l =  ReplaceEncoder.maxCapacity(container);
                Log.i("REP", num.substring(0, Math.min(l.length()-1, num.length())));
                container = ReplaceEncoder.encoding(container, num.substring(0, Math.min(l.length()-1, num.length())));
                num = num.substring(Math.min(l.length()-1, num.length()));
                Log.i("REP", num);
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
                int length = Integer.parseInt(ReplaceEncoder.decoding(container));
                message = InsertEncoder.decoding(container);
                if (message.length() != length)
                    throw new InvalidChecksumException();
                break;
            case MaxCapacity:
                message += ReplaceEncoder.decoding(container);
                message += InsertEncoder.decoding(container);
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
