// Problem Name:
// 3348. Smallest Divisible Digit Product II

// Topic:
// Greedy, Math, Number Theory, GCD, String

// Approach:
// Step 1:
// Check whether t contains any prime factor
// other than 2, 3, 5, or 7.
//
// Since every digit (1–9) can only contribute
// these prime factors, any other prime factor
// makes the answer impossible.
//
// Step 2:
// Precompute:
//
// remainingFactor[i]
//
// = the factor of t still needed after using
// the first i digits of num.
//
// For every digit:
//
// remainingFactor[i + 1] =
// remainingFactor[i] / gcd(remainingFactor[i], digit)
//
// Step 3:
// If remainingFactor[n] == 1,
// the given number itself already satisfies
// the condition.
//
// Return num.
//
// Step 4:
// Starting from the rightmost position,
// try to increase the current digit.
//
// For every larger digit:
//
// - Update the remaining required factor.
// - Fill the remaining positions with the
//   lexicographically smallest zero-free number
//   whose digit product satisfies the remaining factor.
//
// If such a suffix exists,
// return the constructed number.
//
// Step 5:
// If no valid number of the same length exists,
// construct the smallest valid zero-free number
// of length n + 1.
//
// The helper function greedily places larger
// factors (9 to 2), then pads the remaining
// positions with '1's and reverses the result
// to obtain the smallest lexicographical number.

// Time Complexity:
// O(n × 9 × log t)
//
// n = length of num
// GCD computations take O(log t).

// Space Complexity:
// O(n)

// Java Solution:
class Solution {

    // Smallest zero-free number of length at least "length"
    // whose digit product is divisible by "required"
    private String freeSlotsFiller(long required, int length) {

        StringBuilder str = new StringBuilder();

        for (int digit = 9; digit >= 2; digit--) {

            while (required % digit == 0) {
                str.append((char) (digit + '0'));
                required /= digit;
            }
        }

        while (str.length() < length) {
            str.append('1');
        }

        str.reverse();

        return str.toString();
    }

    public String smallestNumber(String num, long t) {

        int n = num.length();

        // Check whether t contains only
        // prime factors 2, 3, 5, and 7.
        long temp = t;

        for (int prime : new int[]{2, 3, 5, 7}) {

            while (temp % prime == 0) {
                temp /= prime;
            }
        }

        if (temp != 1) {
            return "-1";
        }

        long[] remainingFactor = new long[n + 1];
        remainingFactor[0] = t;

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            if (digit == 0) {
                break;
            }

            remainingFactor[i + 1] =
                    remainingFactor[i] /
                    gcd(remainingFactor[i], digit);
        }

        if (remainingFactor[n] == 1) {
            return num;
        }

        int zeroPos = num.indexOf('0');
        int zeroIdx = (zeroPos == -1) ? n - 1 : zeroPos;

        for (int i = zeroIdx; i >= 0; i--) {

            long required = remainingFactor[i];

            int freeSlots = n - 1 - i;

            for (int digit = (num.charAt(i) - '0') + 1;
                 digit <= 9;
                 digit++) {

                long furtherRequired =
                        required / gcd(required, digit);

                String suffix =
                        freeSlotsFiller(furtherRequired, freeSlots);

                if (suffix.length() == freeSlots) {

                    return num.substring(0, i)
                            + (char) (digit + '0')
                            + suffix;
                }
            }
        }

        return freeSlotsFiller(t, n + 1);
    }

    private long gcd(long a, long b) {

        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}