package com.thnery.nlp.utils;

import com.thnery.nlp.document.Token;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String checkTokenType(Token token) {
        try {
            Double.parseDouble(token.getContent());
            return "Number";
        } catch (Exception e) {
//            e.printStackTrace();
        }

        if (Utils.isSymbol(token.getContent())) {
            return "Symbol";
        }

        return "Word";
    }

    public static boolean isSymbol(String token) {
        token = token.replaceAll("_", "+");

        Pattern pattern = Pattern.compile("(\\W)+");
        Matcher matcher = pattern.matcher(token);

        return matcher.matches();
    }

    public static Set<String> loadSetOfWords(File file) {
        try {
            String content = Utils.readTxtFile(file);

            if (content == null) {
                return null;
            }

            String[] lines = content.split("\r?\n");
            Set<String> words = new HashSet<String>();

            for (String line : lines) {
                words.add(Utils.normalizeString(line.toLowerCase()));
            }

            return words;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String readTxtFile(File file) {
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            fr.read(chars);
            return new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String normalizeString(String txt) {
        if (txt == null) return null;

        txt = Normalizer.normalize(txt, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        txt = txt.replaceAll("\\p{P}", "").toLowerCase();

        return txt.trim();
    }
}
