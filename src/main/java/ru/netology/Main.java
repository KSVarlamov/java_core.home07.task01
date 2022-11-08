package ru.netology;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = "Alibaba or Alibubab? I do not know!";
        String pattern = "b*b";

        searchSubSting(source, pattern).forEach(System.out::println);
    }

    public static List<Integer> searchSubSting(String source, String pattern) {
        if (pattern.length() > source.length()) {
            return null;
        }
        List<Integer> found = new ArrayList<>();
        int asteriskPosition = pattern.indexOf('*');
        int patternHash = simpleHash(pattern, (asteriskPosition != -1));
        int windowHash = 0;
        for (int i = 0; i < source.length() - pattern.length() + 1; i++) {
            String windowString = source.substring(i, i + pattern.length());
            if (i == 0) {
                windowHash = simpleHash(windowString, false);
                windowHash -= windowString.charAt(asteriskPosition);
            } else {
                windowHash -= source.charAt(i - 1);
                windowHash += source.charAt(i + pattern.length() - 1);
                windowHash -= source.charAt(i + asteriskPosition);
            }
            if (windowHash == patternHash) {
                boolean isMatch = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if ((pattern.charAt(j) != '*') && (source.charAt(i + j) != pattern.charAt(j))) {
                        isMatch = false;
                    }
                }
                if (isMatch) {
                    found.add(i);
                }
            }
            windowHash += source.charAt(i + asteriskPosition);
        }
        return found;
    }

    public static int simpleHash(String str, boolean isPattern) {
        int hash = 0;
        for (char c : str.toCharArray()) {
            if (isPattern && (c == '*')) {
                continue;
            } else {
                hash += c;
            }
        }
        return hash;
    }
}
