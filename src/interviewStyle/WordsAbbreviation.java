package interviewStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of n distinct non-empty strings,
 * you need to generate minimal possible abbreviations for every word following rules below.
 * Begin with the first character and then the number of characters abbreviated,
 * which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation,
 * a longer prefix is used instead of only the first character
 * until making the map from word to abbreviation become unique.
 * In other words, a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 * Notice
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 * 
 * Example
 * Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 */

public class WordsAbbreviation {
    /**
     * @param dict an array of n distinct non-empty strings
     * @return an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        if (dict == null || dict.length == 0) {
            return new String[0];
        }

        int n = dict.length;
        String[] res = new String[n];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < dict.length; i++) {
            results.add(i);
        }
        int len = 2;
        while (!results.isEmpty()) {
            Map<String, Set<Integer>> map = new HashMap<String, Set<Integer>>();
            for (int i : results) {
                String curr = dict[i];
                String abbr = curr.length() <= (len + 1) ?
                    curr : curr.substring(0, len - 1) + (curr.length() - len) + curr.charAt(curr.length() - 1);
                if (map.containsKey(abbr)) {
                    map.get(abbr).add(i);
                } else {
                    Set<Integer> set = new HashSet<Integer>();
                    set.add(i);
                    map.put(abbr, set);
                }
            }
            
            List<Integer> next = new ArrayList<Integer>();
            for (String word : map.keySet()) {
                if (map.get(word).size() == 1) {
                    int index = -1;
                    for (int i : map.get(word)) {
                        index = i;
                    }
                    res[index] = word;
                } else {
                    for (int i : map.get(word)) {
                        next.add(i);
                    }
                }
            }
            results = next;
            len++;
        }
        return res;
    }
}
