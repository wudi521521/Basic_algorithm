package com.basic.three.stack;

import java.util.Stack;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/6/17 21:03
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char toChar = stack.pop();
                if (c == ')' && toChar != '(') {
                    return false;
                }
                if (c == ']' && toChar != '[') {
                    return false;
                }
                if (c == '}' && toChar != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}