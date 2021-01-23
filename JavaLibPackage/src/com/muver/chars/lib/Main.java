package com.muver.chars.lib;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.beans.beancontext.BeanContext;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    // TODO Когда будут готовы все модули, покрыть всё исключениями
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String container = "лолололололололололололололололололололололололололололололололололололололололололaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String message = "He";
        String key = "12331244";
        EncodingType type = EncodingType.OnlyReplace;
        String text = CharEncoder.encoding(container, message, key, type);
        String dec = CharEncoder.decoding(text, key, type);
        System.out.println(text);
        System.out.println(dec);
        System.out.println(ReplaceEncoder.maxCapacity(container));
    }
}
