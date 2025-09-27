
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.
 */


// TC: O(k^n) k = avg len of block, and n - n strings.

class Solution {


    List<List<Character>> blocks;
    List<String> result;

    public String[] expand(String s) {
        if (s == null || s.length() == 0) {
            return new String[] {};
        }

        blocks = new ArrayList<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if (c == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
                
            } else if (s.charAt(i) != ',') {
                block.add(s.charAt(i));
            }
            Collections.sort(block);
            blocks.add(block);
        }

        backtrack(new StringBuilder(), 0);

        String[] answer = result.toArray(new String[0]);

        return answer;
    }

    private void backtrack(StringBuilder sb, int index) {
        if (index == blocks.size()) {
            result.add(sb.toString());
            return;
        }
        
        for (int i = index; i < blocks.size(); ++i) {
            List<Character> block = blocks.get(i);
            for (char c : block) {
                int len = sb.length();
                sb.append(c);
                backtrack(sb, index + 1);
                sb.setLength(len);
            }
        }
    }

}