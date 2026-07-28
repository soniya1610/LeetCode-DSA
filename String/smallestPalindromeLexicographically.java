// Problem Name:
// 2697. Lexicographically Smallest Palindrome

// Topic:
// String, Greedy, Sorting

// Approach:
// Step 1:
// A palindrome is completely determined by its first half.
//
// Extract the first half of the string.
//
// Step 2:
// Sort the first half in ascending order.
//
// This produces the lexicographically smallest
// possible first half.
//
// Step 3:
// Build the answer:
//
// - First half  = sorted half
// - Middle char = original middle character
//                 (only if length is odd)
// - Second half = reverse of the sorted half
//
// Reversing the first half ensures
// the final string remains a palindrome.
//
// Step 4:
// Return the constructed palindrome.

// Time Complexity:
// O(n log n)
//
// Sorting the first half dominates.

// Space Complexity:
// O(n)

// Java Solution:
class Solution {
    public String smallestPalindrome(String s) {

        int n = s.length();

        char[] half = s.substring(0, n / 2).toCharArray();

        Arrays.sort(half);

        String res =
                new String(half)
                + ((n & 1) == 1 ? s.charAt(n / 2) : "")
                + new StringBuilder(new String(half)).reverse();

        return res;
    }
}