// Problem Name:
// 3867. Sum of GCDs of Powerful Subsequences

// Topic:
// Arrays, Prefix Maximum, GCD, Sorting, Greedy

// Approach:
// Step 1:
// For every index i, calculate:
//
// mx[i] = maximum element from nums[0] to nums[i]
//
// Then calculate:
//
// prefixGcd[i] = gcd(nums[i], mx[i])
//
// Step 2:
// Sort the prefixGcd array.
//
// Step 3:
// Pair elements from opposite ends:
//
// smallest with largest
// second smallest with second largest
// and so on.
//
// For every pair, calculate their GCD
// and add it to the answer.
//
// Step 4:
// Iterate only through the first half of the array,
// because every element is used exactly once.

// Time Complexity:
// O(n log n)
//
// Sorting takes O(n log n).

// Space Complexity:
// O(n)

// Java Solution:
class Solution {
    private int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }

        return gcd(num2, num1 % num2);
    }

    public long gcdSum(int[] nums) {
        int n = nums.length;

        int[] mx = new int[n];
        int[] prefixGcd = new int[n];

        mx[0] = nums[0];
        prefixGcd[0] = nums[0];

        for (int i = 1; i < n; i++) {
            mx[i] = Math.max(mx[i - 1], nums[i]);

            prefixGcd[i] = gcd(nums[i], mx[i]);
        }

        Arrays.sort(prefixGcd);

        long sum = 0;

        for (int i = 0; i < n / 2; i++) {
            sum += gcd(
                prefixGcd[i],
                prefixGcd[n - 1 - i]
            );
        }

        return sum;
    }
}