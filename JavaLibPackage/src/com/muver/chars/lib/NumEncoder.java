package com.muver.chars.lib;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

enum LanguageType { Ru, En }
enum CharType { Digit, RuLetter, EnLetter, Special, LangChange, None }

public class NumEncoder {
    private static final int[] _special_chars = new int[] {'.', ',', '!', '?', ("'").charAt(0), ' '};
    private static final char[] _ru_letters = new char[] {'а', 'А'};
    private static final char[] _en_letters = new char[] {'a', 'A'};
    private static final LanguageType _default_lang_type = LanguageType.Ru;
    private static final int _lower_letter = 0;
    private static final int _upper_letter = 32;
    private static final int _digit_letter = 64;
    private static final int _lang_change = 74;
    private static final int _special_letter = 75;

    private static CharType get_type_char(char ch) {
        CharType type = CharType.None;
        if (Character.isDigit(ch))
            type = CharType.Digit;
        else if (Arrays.stream(_special_chars).anyMatch(x ->  x == ch))
            type = CharType.Special;
        else if (Character.isLetter(ch)){
            char lower_ch = Character.toLowerCase(ch);
            if (lower_ch >= _ru_letters[0] && lower_ch <= _ru_letters[0]+32)
                type = CharType.RuLetter;
            else if (lower_ch >= _en_letters[0] && lower_ch <= _en_letters[0]+28)
                type = CharType.EnLetter;
        }
        return type;
    }

    public static String encoding(String message) {
        String output = "1";
        LanguageType lang_type = _default_lang_type;
        for (char ch: message.toCharArray() ) {
            int num = 0;
            switch (get_type_char(ch)){
                case Digit:
                    num = (ch - '0' + _digit_letter);
                    break;
                case EnLetter:
                    if (lang_type != LanguageType.En){
                        output += _lang_change;
                        lang_type = LanguageType.En;
                    }
                    if (Character.isUpperCase(ch))
                        num = (ch - _en_letters[1] + _upper_letter);
                    else
                        num = (ch - _en_letters[0] + _lower_letter);
                    break;
                case RuLetter:
                    if (lang_type != LanguageType.Ru){
                        output += _lang_change;
                        lang_type = LanguageType.Ru;
                    }
                    if (Character.isUpperCase(ch))
                        num = (ch - _ru_letters[1] + _upper_letter);
                    else
                        num = (ch - _ru_letters[0] + _lower_letter);
                    break;
                case Special:
                    num = ((int)Arrays.stream(_special_chars).takeWhile(x -> x != ch).count() + _special_letter);
                    break;
            }
            output += Integer.toString(num + 100).substring(1);
        }
        return output;
    }

    public static String decoding(String num) {
        String output = "";
        LanguageType lang_type = _default_lang_type;
        for (int i = 1; i < num.length(); i += 2 ) {
            int n = Integer.parseInt(num.substring(i, i + 2));
            int ch = 0;
            if (n == _lang_change) {
                if (lang_type == LanguageType.En)
                    lang_type = LanguageType.Ru;
                else if (lang_type == LanguageType.Ru)
                    lang_type = LanguageType.En;
            }
            else if (n >= _lower_letter && n <= _lower_letter + 32) { // маленькие буквы
                if (lang_type == LanguageType.Ru)
                    ch += _ru_letters[0];
                else if (lang_type == LanguageType.En)
                    ch += _en_letters[0];
                ch += n - _lower_letter;
            }
            else if (n >= _upper_letter && n <= _upper_letter + 32) {
                if (lang_type == LanguageType.Ru)
                    ch += _ru_letters[1];
                else if (lang_type == LanguageType.En)
                    ch += _en_letters[1];
                ch += n - _upper_letter;
            }
            else if (n >= _digit_letter && n <= _digit_letter + 10) { // цифры
                ch += n - _digit_letter;
            }
            else if (n >= _special_letter && n <= _special_letter + _special_chars.length) {// спец. символы
                ch += _special_chars[n - _special_letter];
            }
            output += (char)ch;
        }
        return output;
    }
}
