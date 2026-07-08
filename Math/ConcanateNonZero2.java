// Problem Name:
// 3754. Concatenate Non-Zero Digits and Multiply by Sum II

// Topic:
// Prefix Sum, Math, String, Modular Arithmetic

// Approach:
// Step 1:
// Precompute prefix information.
//
// Maintain four arrays:
//
// 1. nonZeroCount[i]
//    = Number of non-zero digits in s[0...i]
//
// 2. numberUpTo[i]
//    = Number formed by concatenating all non-zero digits
//      from s[0...i] (stored modulo MOD)
//
// 3. digitSumUpTo[i]
//    = Prefix sum of digits in s[0...i]
//
// 4. pow10[i]
//    = 10^i modulo MOD
//
// Step 2:
// For every query [l, r]:
//
// Find:
// - Number of non-zero digits before l.
// - Number formed before l.
// - Number of non-zero digits till r.
//
// This gives the number of non-zero digits inside
// the current substring.
//
// Step 3:
// If substring contains no non-zero digit,
// answer is 0.
//
// Step 4:
// Construct the required number using prefix values.
//
// Remove the contribution of digits before l:
//
// x = numberUpTo[r]
//     - numberUpTo[l-1] × 10^(non-zero digits in substring)
//
// Use modulo arithmetic to avoid overflow.
//
// Step 5:
// Compute digit sum of substring using prefix sums:
//
// sum = digitSumUpTo[r] - digitSumUpTo[l-1]
//
// Step 6:
// Return:
//
// (x × sum) % MOD
//
// This allows every query to be answered in O(1)
// after preprocessing.

// Time Complexity:
// Preprocessing: O(n)
// Each Query: O(1)
//
// Total:
// O(n + q)

// Space Complexity:
// O(n)

// Java Solution:
class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[] nonZeroCount = new int[n];
        long[] numberUpTo = new long[n];
        long[] digitSumUpTo = new long[n];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        nonZeroCount[0] = (s.charAt(0) != '0') ? 1 : 0;
        numberUpTo[0] = s.charAt(0) - '0';
        digitSumUpTo[0] = s.charAt(0) - '0';

        for (int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';
            nonZeroCount[i] = nonZeroCount[i - 1] + (digit != 0 ? 1 : 0);
        }

        for (int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';

            if (digit != 0)
                numberUpTo[i] = (numberUpTo[i - 1] * 10 + digit) % MOD;
            else
                numberUpTo[i] = numberUpTo[i - 1];
        }

        for (int i = 1; i < n; i++)
            digitSumUpTo[i] = digitSumUpTo[i - 1] + (s.charAt(i) - '0');

        int q = queries.length;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int startCount = (l == 0) ? 0 : nonZeroCount[l - 1];
            long numBefore = (l == 0) ? 0 : numberUpTo[l - 1];

            int endCount = nonZeroCount[r];
            int subStrLen = endCount - startCount;

            if (subStrLen == 0) {
                result[i] = 0;
                continue;
            }

            long x = (numberUpTo[r]
                    - (numBefore * pow10[subStrLen]) % MOD
                    + MOD) % MOD;

            long sum = digitSumUpTo[r]
                    - ((l == 0) ? 0 : digitSumUpTo[l - 1]);

            result[i] = (int) ((x * sum) % MOD);
        }

        return result;
    }
}