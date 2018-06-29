package com.chengmboy.algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * 寻找最长字串长度
 *
 * @author cheng_mboy
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        String r = l.longestSubstring("abcabecdcd");
        System.out.println(r);
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> longest = new HashSet<>();
        int r = 0;
        for (int i = 0; i < chars.length; i++) {
            if (longest.contains(chars[i])) {
                r = Math.max(longest.size(), r);
                longest = new HashSet<>();
            }
            longest.add(chars[i]);
        }
        return r;
    }

    /**
     * 寻找不重复最长子串
     */
    public String longestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> longest = new HashSet<>();
        StringBuilder sub = new StringBuilder();
        int r = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (longest.contains(chars[i])) {
                if (longest.size() > r) {
                    r = longest.size();
                    result = sub;
                }
                longest = new HashSet<>();
                sub = new StringBuilder();
            }
            longest.add(chars[i]);
            sub.append(chars[i]);
        }
        return result.toString();
    }
}
