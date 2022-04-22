// 
// Decompiled by Procyon v0.5.36
// 

package br.com.alelo.consumer.consumerpat.util;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil
{
    public static Boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }
    
    public static String unaccent(final String string) {
        final String temp = Normalizer.normalize(string, Normalizer.Form.NFD);
        final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    
    public static String removeSpecialCharacters(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        return string.replaceAll("[^\\p{ASCII}]", "");
    }
    
    public static void addPunctuationCharacter(final List<?> entityList, final StringBuilder string, final int index) {
        if (index == entityList.size() - 2) {
            string.append(" e ");
        }
        else if (index != entityList.size() - 1) {
            string.append(", ");
        }
    }
}
