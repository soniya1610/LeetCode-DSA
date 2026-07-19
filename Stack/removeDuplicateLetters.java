package Stack;

import java.util.*;
// Problem Name:
// 316. 1081 Remove Duplicate Letters

// Topic:
// Greedy, Stack, Monotonic Stack, String

// Approach:
// Step 1:
// Find the last occurrence of every character.
//
// lastIndex[c] = last position where character c appears.
//
// This helps us determine whether a character can be removed
// from the current answer and added again later.
//
// Step 2:
// Traverse the string.
//
// If the current character is already present in the stack,
// skip it.
//
// Step 3:
// Before adding the current character,
// remove characters from the stack while:
//
// 1. The stack is not empty.
// 2. Current character is lexicographically smaller.
// 3. The top character appears again later.
//
// This is greedy because we remove a larger character
// only when we know it can be added again later.
//
// Step 4:
// Add the current character to the stack
// and mark it as taken.
//
// Step 5:
// The stack contains the answer in order.
// Reverse it to create the final string.

// Time Complexity:
// O(n)
//
// Each character is pushed and popped at most once.

// Space Complexity:
// O(n)

// Java Solution:
class Solution {
    public String removeDuplicateLetters(String s) {

        Stack<Character> st = new Stack<>();

        boolean[] taken = new boolean[26];
        int[] lastIndex = new int[26];

        // Store the last occurrence of every character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastIndex[ch - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            int idx = ch - 'a';

            // Character is already present
            if (taken[idx])
                continue;

            // Remove larger characters if they appear again later
            while (!st.isEmpty()
                    && ch < st.peek()
                    && lastIndex[st.peek() - 'a'] > i) {

                taken[st.pop() - 'a'] = false;
            }

            st.push(ch);
            taken[idx] = true;
        }

        StringBuilder result = new StringBuilder();

        while (!st.isEmpty()) {
            result.append(st.pop());
        }

        return result.reverse().toString();
    }
}