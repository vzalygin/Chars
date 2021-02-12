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
        String container = readFile("container.txt");
        container = container.substring(1000, 2000);
        String message = "";
        for (int i = 0; i < 70; i += 1)
            message += "a";
        String key = "01901901";
        long m;
        m = 0;
        String encoded = CharEncoder.encoding(container, message, key, EncodingType.MaxCapacity);
        System.out.println((double) (System.currentTimeMillis() - m));
        String decoded = CharEncoder.decoding(encoded, key, EncodingType.MaxCapacity);
        write(encoded, "out.txt");
        double quotient = InsertEncoder.distortionOfContainer(encoded);
        System.out.println(encoded);
        System.out.println(decoded);
        System.out.println(quotient);
    }

    private static void timer() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, TooSmallContainerException, IOException, InvalidChecksumException {
        String container = readFile("container.txt");
        String message = readFile("message.txt");
        String tmp_message = "", tmp_container = "", key = "12341234";
        String encoded = "", decoded = "";
        EncodingType type = EncodingType.OnlyInsert;
        int step = 100, i = 1, begin = 100, count = 10;
        long m;
        //tmp_message = message.substring(0, 50);
        tmp_container = container.substring(0, 30000);
        for (int j = 0; j < 300; j += 1) {
            //try {
                tmp_message = message.substring(0, begin+step*i);
                int averE = 0, averD = 0;
                for (int k = 0; k < count; k += 1) {
                    m = System.currentTimeMillis();
                    encoded = CharEncoder.encoding(tmp_container, tmp_message, key, type);
                    averE += (System.currentTimeMillis() - m);
                    m = System.currentTimeMillis();
                    //decoded = CharEncoder.encoding(tmp_container, tmp_message, key, type);
                    averD += (System.currentTimeMillis() - m);
                }
                averE /= count;
                averD /= count;
                System.out.println(averE);
                i += 1;
            /*}
            catch (Exception e) {
                System.out.println(0);
                curr_container += 30000;
                tmp_container = container.substring(0, curr_container);
            }*/
        }
    }

    private static void WorkTime() {
        long m;
        m = 0;
        //
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    private static void write(String text, String path) throws IOException {
        FileWriter writer = new FileWriter(path, false);
        writer.write(text);
        writer.flush();
    }

    private static String readFile(String FileName) throws IOException
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
