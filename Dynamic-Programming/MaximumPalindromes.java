```java id="c8xk2m"
/*
 * Problem: Maximum Palindromes
 * Topic: Dynamic Programming, Palindrome, String
 *
 * Approach:
 * 1. Precompute whether every substring is a palindrome using
 *    a 2D DP table.
 * 2. Use another DP array where t[i] represents the maximum
 *    number of non-overlapping palindromic substrings of length
 *    at least k that can be selected from the first i characters.
 * 3. For every ending position, either:
 *      - Skip the current character: t[len - 1]
 *      - Select a palindrome ending at len - 1:
 *        1 + t[start]
 * 4. Take the maximum of both choices.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */

class Solution {
    boolean[][] isPalindrome;
    int[] t;

    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // Precompute palindrome substrings
        isPalindrome = new boolean[n][n];

        for (int L = 1; L <= n; L++) {
            for (int i = 0; i + L <= n; i++) {
                int j = i + L - 1;

                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (i + 1 == j) {
                    isPalindrome[i][j] =
                            s.charAt(i) == s.charAt(j);
                } else {
                    isPalindrome[i][j] =
                            s.charAt(i) == s.charAt(j)
                            && isPalindrome[i + 1][j - 1];
                }
            }
        }

        // DP: t[i] = maximum palindromes using first i characters
        t = new int[n + 1];

        // Fewer than k characters cannot form a valid palindrome
        for (int len = 0; len < k; len++) {
            t[len] = 0;
        }

        for (int len = k; len <= n; len++) {
            // Skip the current character
            int result = t[len - 1];

            int j = len - 1;

            // Try every palindrome ending at j
            for (int i = 0; j - i + 1 >= k; i++) {
                if (isPalindrome[i][j]) {
                    result = Math.max(result, 1 + t[i]);
                }
            }

            t[len] = result;
        }

        return t[n];
    }
}
```
