 // Problem Name:
// 1291. Sequential Digits

// Topic:
// Math, String, Enumeration

// Approach:
// Step 1:
// Store the string:
//
// "123456789"
//
// Every sequential digit number is a substring of this string.
//
// Step 2:
// Determine the possible lengths of the answer.
//
// Minimum length = number of digits in low.
//
// Maximum length = number of digits in high.
//
// Step 3:
// For every possible length:
//
// Generate every substring of that length
// from "123456789".
//
// Convert each substring into an integer.
//
// Step 4:
// If the generated number lies within
// the range [low, high],
// add it to the answer.
//
// Step 5:
// Return the list of sequential digit numbers.

// Time Complexity:
// O(1)
//
// At most 36 numbers are generated,
// so the work is constant.

// Space Complexity:
// O(1)
// (Ignoring the output list)

// Java Solution:
import java.util.*;
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> ans = new ArrayList<>();

        String s = "123456789";

        String l = String.valueOf(low);
        String h = String.valueOf(high);

        for (int len = l.length(); len <= h.length(); len++) {

            for (int start = 0; start <= 9 - len; start++) {

                int num = Integer.parseInt(s.substring(start, start + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}