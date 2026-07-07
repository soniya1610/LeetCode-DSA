// Problem Name:
// 3754. Concatenate Non-Zero Digits and Multiply by Sum I

// Topic:
// Math, String

// Approach:
// Step 1:
// Traverse the digits of n from right to left.
//
// - Extract each digit using n % 10.
// - Ignore digits equal to 0.
// - Append every non-zero digit to a StringBuilder.
//
// Since digits are extracted in reverse order,
// reverse the StringBuilder to restore the original order.
//
// Step 2:
// Traverse the formed string.
//
// - Construct the new number x.
// - Simultaneously calculate the sum of its digits.
//
// Step 3:
// Return:
//
// x * sum

// Time Complexity:
// O(d)
//
// d = number of digits in n

// Space Complexity:
// O(d)
// (Used by StringBuilder)

// Java Solution:
class Solution {
    public long sumAndMultiply(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                sb.append(digit);
            }
            n /= 10;
        }
        sb.reverse();

        long x = 0;
        int sum = 0;

        for (int i = 0; i < sb.length(); i++) {
            int digit = sb.charAt(i) - '0';

            x = x * 10 + digit;
            sum += digit;
        }
        return x * sum;
    }
}