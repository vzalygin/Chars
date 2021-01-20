package com.muver.chars.lib;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static PrintStream output = System.out;

    public static void main(String[] args) {
	    String enc = NumEncoder.encoding("Hello world! Привет, мир!");
        String dec = NumEncoder.decoding(enc);
	    output.println(enc);
	    output.println(dec);
    }
}
