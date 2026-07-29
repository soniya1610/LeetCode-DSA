// Problem Name:
// 3260. Find the K-th Smallest Palindrome

// Topic:
// String, Greedy, Backtracking, Combinatorics

// Approach:
// Step 1:
// Count the frequency of every character.
//
// Step 2:
// Construct:
//
// - half[i] = frequency of character / 2
// - middle character (if any)
//
// The palindrome is completely determined
// by its first half.
//
// Step 3:
// Before constructing the answer,
// compute the total number of distinct
// palindromes that can be formed.
//
// If this count is smaller than k,
// return an empty string.
//
// Step 4:
// Build the first half greedily.
//
// For every position:
//
// Try characters from 'a' to 'z'.
//
// - Temporarily place the character.
// - Compute how many palindromes are possible
//   with the remaining characters.
//
// If the number of possible palindromes
// is at least k:
//
// - Keep this character.
//
// Otherwise:
//
// - Skip all those palindromes.
// - Subtract their count from k.
// - Restore the character and try the next one.
//
// Step 5:
// After constructing the first half:
//
// Answer =
//
// firstHalf
// + middle character (if present)
// + reverse(firstHalf)

// Time Complexity:
// O(26 × n²)
//
// (For each position, at most 26 characters are tried,
// and each call to getWays() processes all characters.)

// Space Complexity:
// O(26)

// Java Solution:
class Solution {

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        StringBuilder middle = new StringBuilder();

        int halfLength = 0;

        for (int i = 0; i < 26; i++) {

            if ((freq[i] & 1) == 1) {
                middle.append((char) (i + 'a'));
            }

            half[i] = freq[i] / 2;
            halfLength += half[i];
        }

        if (getWays(half, k) < k) {
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();

        for (int pos = 0; pos < halfLength; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0)
                    continue;

                half[ch]--;

                long ways = getWays(half, k);

                if (ways >= k) {
                    firstHalf.append((char) (ch + 'a'));
                    break;
                }

                k -= ways;
                half[ch]++;
            }
        }

        StringBuilder result = new StringBuilder(firstHalf);

        result.append(middle);
        result.append(firstHalf.reverse());

        return result.toString();
    }

    private long getWays(int[] freq, long targetK) {

        long ways = 1;
        int length = 0;

        for (int count : freq) {

            if (count == 0)
                continue;

            length += count;

            long n = length;
            long r = count;

            if (r > n - r)
                r = n - r;

            long nCr = 1;

            for (int i = 1; i <= r; i++) {

                nCr = nCr * (n - i + 1) / i;

                if (nCr > targetK) {
                    nCr = targetK + 1;
                    break;
                }
            }

            ways *= nCr;

            if (ways > targetK)
                return targetK + 1;
        }

        return ways;
    }
}