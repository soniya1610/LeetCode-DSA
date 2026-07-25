// Problem Name:
// 1464. Maximum Product of Two Elements in an Array

// Topic:
// Math, Greedy, Digit Manipulation

// Approach:
// Step 1:
// Extract every digit from n using:
//
// digit = n % 10
//
// Step 2:
// Maintain the two largest digits:
//
// max1 = largest digit
// max2 = second largest digit
//
// Step 3:
// For every extracted digit:
//
// - If digit > max1:
//      max2 = max1
//      max1 = digit
//
// - Else if digit > max2:
//      max2 = digit
//
// Step 4:
// After processing all digits,
// return:
//
// max1 × max2

// Time Complexity:
// O(d)
//
// d = number of digits in n

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int maxProduct(int n) {

        int max1 = 0;
        int max2 = 0;

        while (n > 0) {

            int digit = n % 10;

            if (digit > max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }

            n /= 10;
        }

        return max1 * max2;
    }
}