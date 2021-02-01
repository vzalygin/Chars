package com.muver.chars.lib;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, TooSmallContainerException, IOException, InvalidChecksumException {
        String container = ReadFile("container.txt");
        String message = ReadFile("message.txt");
        //message = message.substring(0, 500);
        //container = container.substring(0, 3000);
        String key = "12341234";
        EncodingType type = EncodingType.Standard;
        String encoded = "", decoded = "";
        encoded = CharEncoder.encoding(container, message, key, type);
        decoded = CharEncoder.decoding(encoded, key, type);
        System.out.println(decoded);
    }

    private static void WorkTime() {
        long m = 0;
        //
        System.out.println((double) (System.currentTimeMillis() - m));
    }


    private static String ReadFile(String FileName) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        try
        {FileInputStream fis =  new FileInputStream(FileName);
            BufferedReader input = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            //BufferedReader input = new BufferedReader(new FileReader( new File(FileName).getAbsoluteFile()));
            try{
                String s;
                while ((s=input.readLine())!=null){sb.append(s);sb.append("\n");}}
            finally {
                input.close();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static void test1() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, TooSmallContainerException, NoSuchPaddingException, InvalidKeyException {
        int k = 100;
        EncodingType type = EncodingType.MaxCapacity;
        float result = 0;
        for (String mess = "б"; mess.length() < k; mess += "a") {
            boolean isTooSmall = true;
            int i = 0;
            String container = "a";
            String key = "12345678";
            String output = "";
            long m = System.currentTimeMillis();
            while (isTooSmall) {
                i += 1;
                try {
                    output = CharEncoder.encoding(container, mess, key, type);
                    isTooSmall = false;
                } catch (Exception e) {
                    container += "a";
                }
            }
            //System.out.println((double) (System.currentTimeMillis() - m));

            m = System.currentTimeMillis();
            CharEncoder.encoding(container, mess, key, type);
            //System.out.println((double) (System.currentTimeMillis() - m));
            System.out.println(/*mess.length() + " " +*/ i /*+ " " + i/(float)mess.length()*/);
            result += i/(float)mess.length();
        }
        result /= k;
        System.out.println(result);
    }
}
