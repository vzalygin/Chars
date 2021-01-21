package com.muver.chars.lib;

import java.util.*;

public class InsertEncoder {
    private static final char[] _chars = new char[] {'ͯ', 'ͮ', 'ͭ', 'ͬ', 'ͫ', 'ͪ', 'ͩ', 'ͨ', 'ͧ', 'ͦ', 'ͥ', 'ͤ', 'ͣ', '͘',
            '͗', '͕', '͔', '͓', '͑', '͐', '͏', '͉', '̾', '̽', '̻', '̹', '̩', '̨', '̦',
            '̥', '̣', '̠', '̟', '̞', '̝', '̛', '̚', '̕', '̔', '̓', '̑', '̏', '̉', '̶'};
    private static final int _n = _chars.length;
    private static final Random _rand = new Random();

    private static ArrayList<Integer> _to_n_sys(String mess) {
        ArrayList<Integer> n_sys = new ArrayList<>();
        while (mess.length() > 0) {
            int t_sys = Integer.parseInt(mess.substring(0, Math.min(9, mess.length())));
            while (t_sys > 0) {
                n_sys.add(t_sys % _n);
                t_sys /= _n;
            }
            mess = mess.substring(Math.min(9, mess.length()));
        }
        return n_sys;
    }

    private static String _to_t_sys(ArrayList<Integer> n_sys) {
        String output = "";
        long t_sys = 0;
        for (int i = 0; i < n_sys.size(); i += 1) {
            t_sys += n_sys.get(i)*(Math.pow(_n, i));
            if (t_sys > Integer.MAX_VALUE) {
                t_sys -= n_sys.get(i)*(Math.pow(_n, i));
                output += Long.toString(t_sys);
                t_sys = 0;
            }
        }
        output += Long.toString(t_sys);
        return output;
    }

    public static String encoding(String container, String message, int cluster_size) {
        String output = "";

        ArrayList<Integer> insert = _to_n_sys(message);
        if (cluster_size == -1)
            cluster_size = container.length() / insert.size();
        while (insert.size() > 0) {
              char[] cluster = container.substring(0, cluster_size).toCharArray();
              int position = Math.abs(_rand.nextInt() % cluster_size);
              for (int j = 0; j < cluster_size; j += 1) {
                  if (position == j)
                      output += _chars[insert.get(0)];
                  output += cluster[j];
              }
              insert.remove(0);
              container = container.substring(cluster_size);
        }
        output += container;
        return output;
    }

    private static int indexOf(char ch) {
        int index = -1;
        for (int i = 0; i < _chars.length; i += 1) {
            if (ch == _chars[i]) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String decoding(String container) {
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 0; i < container.length(); i += 1) {
            int index = indexOf(container.charAt(i));
            if (index != -1)
                num.add(index);
        };
        return _to_t_sys(num);
    }
}
