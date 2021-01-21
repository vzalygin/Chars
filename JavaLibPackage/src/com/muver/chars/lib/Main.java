package com.muver.chars.lib;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static PrintStream output = System.out;

    public static void main(String[] args) {
        String mess = "ц";
        output.println(mess);
        String container = "Somebody was told me that world is gonna roll me... А теперь на русском...";
        //output.println(container);
        String num = NumEncoder.encoding(mess);
        output.println(num);
	    String text = InsertEncoder.encoding(container, num, -1);
	    output.println(text);
	    String dec = InsertEncoder.decoding(text);
	    output.println(dec);
	    String dec_mess = NumEncoder.decoding("122");
	    output.println(dec_mess);
    }
}
