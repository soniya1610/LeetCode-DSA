// Problem Name:
// 3622. Check Divisibility by Digit Sum and Product

// Topic:
// Math, Digit Manipulation

// Approach:
// Extract each digit of the number using:
//
//     n % 10
//
// For every digit:
// - Add it to `sum`.
// - Multiply it with `product`.
//
// After processing all digits:
//
//     totalSum = sum + product
//
// Finally, check whether the original number is divisible
// by `totalSum`.
//
// If:
//     original % totalSum == 0
//
// return true, otherwise return false.
//
// Time Complexity:
// O(log n)
//
// Space Complexity:
// O(1)

class Solution {

    public boolean checkDivisibility(int n) {

        int original = n;

        int sum = 0;
        int product = 1;

        while (n > 0) {

            int rem = n % 10;
            n /= 10;

            sum += rem;
            product *= rem;
        }

        int totalSum = sum + product;

        return original % totalSum == 0;
    }
}