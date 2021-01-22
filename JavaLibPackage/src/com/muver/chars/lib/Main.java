package com.muver.chars.lib;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static PrintStream output = System.out;
    // TODO Когда будут готовы все модули, покрыть всё исключениями
    public static void main(String[] args) {
        String mess = "хах";
        output.println(mess);
        String container = "Somebody was told me that world is gonna roll me... А теперь на русском...";
        //output.println(container);
        String num = NumEncoder.encoding(mess);
        output.println(num);
	    String text = ReplaceEncoder.encoding(container, num);
	    output.println(text);
//        for (char c: text.toCharArray()) {
//            System.out.print((int)c + " ");
//        }
	    String dec = ReplaceEncoder.decoding(text);
	    output.println(dec);
	    String dec_mess = NumEncoder.decoding(dec);
	    output.println(dec_mess);
    }
}
