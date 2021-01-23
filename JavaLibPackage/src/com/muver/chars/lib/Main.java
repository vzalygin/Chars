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
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    // TODO Когда будут готовы все модули, покрыть всё исключениями
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String mess = "Hello!";
        String key = "12341234";
        String encoded = DesEncoder.encoding(mess, key);
        String decoded = DesEncoder.decoding(encoded, key);
        System.out.println(mess);
        System.out.println(decoded);
    }
}
