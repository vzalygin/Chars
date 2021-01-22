package com.muver.chars.lib;

import java.util.Arrays;

enum LanguageType { Ru, En }
enum CharType { Digit, RuLetter, EnLetter, Special, LangChange, None }

public class NumEncoder {
    private static final int[] SPECIAL_CHARS = new int[] {'.', ',', '!', '?', ("'").charAt(0), ' '};
    private static final char[] RU_LETTERS = new char[] {'а', 'А'};
    private static final char[] EN_LETTERS = new char[] {'a', 'A'};
    private static final LanguageType DEFAULT_LANG_TYPE = LanguageType.Ru;
    private static final int LOWER_LETTER = 0;
    private static final int UPPER_LETTER = 32;
    private static final int DIGIT_LETTER = 64;
    private static final int LANG_CHANGE = 74;
    private static final int SPECIAL_LETTER = 75;

    private static CharType get_type_char(char ch) {
        CharType type = CharType.None;
        if (Character.isDigit(ch))
            type = CharType.Digit;
        else if (Arrays.stream(SPECIAL_CHARS).anyMatch(x ->  x == ch))
            type = CharType.Special;
        else if (Character.isLetter(ch)){
            char lower_ch = Character.toLowerCase(ch);
            if (lower_ch >= RU_LETTERS[0] && lower_ch <= RU_LETTERS[0]+32)
                type = CharType.RuLetter;
            else if (lower_ch >= EN_LETTERS[0] && lower_ch <= EN_LETTERS[0]+28)
                type = CharType.EnLetter;
        }
        return type;
    }

    public static String encoding(String message) {
        String output = "1";
        LanguageType lang_type = DEFAULT_LANG_TYPE;
        for (char ch: message.toCharArray() ) {
            int num = 0;
            switch (get_type_char(ch)){
                case Digit:
                    num = (ch - '0' + DIGIT_LETTER);
                    break;
                case EnLetter:
                    if (lang_type != LanguageType.En){
                        output += LANG_CHANGE;
                        lang_type = LanguageType.En;
                    }
                    if (Character.isUpperCase(ch))
                        num = (ch - EN_LETTERS[1] + UPPER_LETTER);
                    else
                        num = (ch - EN_LETTERS[0] + LOWER_LETTER);
                    break;
                case RuLetter:
                    if (lang_type != LanguageType.Ru){
                        output += LANG_CHANGE;
                        lang_type = LanguageType.Ru;
                    }
                    if (Character.isUpperCase(ch))
                        num = (ch - RU_LETTERS[1] + UPPER_LETTER);
                    else
                        num = (ch - RU_LETTERS[0] + LOWER_LETTER);
                    break;
                case Special:
                    num = ((int)Arrays.stream(SPECIAL_CHARS).takeWhile(x -> x != ch).count() + SPECIAL_LETTER);
                    break;
            }
            output += Integer.toString(num + 100).substring(1);
        }
        return output;
    }

    public static String decoding(String num) {
        String output = "";
        LanguageType lang_type = DEFAULT_LANG_TYPE;
        for (int i = 1; i < num.length(); i += 2 ) {
            int n = Integer.parseInt(num.substring(i, i + 2));
            int ch = 0;
            if (n == LANG_CHANGE) {
                if (lang_type == LanguageType.En)
                    lang_type = LanguageType.Ru;
                else if (lang_type == LanguageType.Ru)
                    lang_type = LanguageType.En;
            }
            else if (n >= LOWER_LETTER && n <= LOWER_LETTER + 32) { // маленькие буквы
                if (lang_type == LanguageType.Ru)
                    ch += RU_LETTERS[0];
                else if (lang_type == LanguageType.En)
                    ch += EN_LETTERS[0];
                ch += n - LOWER_LETTER;
            }
            else if (n >= UPPER_LETTER && n <= UPPER_LETTER + 32) {
                if (lang_type == LanguageType.Ru)
                    ch += RU_LETTERS[1];
                else if (lang_type == LanguageType.En)
                    ch += EN_LETTERS[1];
                ch += n - UPPER_LETTER;
            }
            else if (n >= DIGIT_LETTER && n <= DIGIT_LETTER + 10) { // цифры
                ch += n - DIGIT_LETTER;
            }
            else if (n >= SPECIAL_LETTER && n <= SPECIAL_LETTER + SPECIAL_CHARS.length) {// спец. символы
                ch += SPECIAL_CHARS[n - SPECIAL_LETTER];
            }
            output += (char)ch;
        }
        return output;
    }
}
