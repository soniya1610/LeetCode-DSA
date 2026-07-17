// Problem Name:
// 3312. Sorted GCD Pair Queries

// Topic:
// Number Theory, GCD, Sieve, Frequency Array, Prefix Sum, Binary Search

// Approach:
// Step 1:
// Count the frequency of every number.
//
// frequency[x] = number of times x appears in nums.
//
// Step 2:
// For every possible GCD value g,
// count how many numbers are divisible by g.
//
// If count numbers are divisible by g,
// then:
//
// count * (count - 1) / 2
//
// gives the number of pairs whose GCD is a multiple of g.
//
// Step 3:
// Convert these counts into the number of pairs
// whose GCD is exactly g.
//
// Process g from maximum to minimum.
//
// Subtract the pairs whose exact GCD is a multiple of g:
//
// exactGcd[g] -= exactGcd[2g]
// exactGcd[g] -= exactGcd[3g]
// ...
//
// This is similar to the Sieve of Eratosthenes.
//
// Step 4:
// Build a prefix sum:
//
// prefix[g] = number of pairs whose GCD <= g
//
// The pairs are considered in sorted order of their GCD values.
//
// Step 5:
// For each query:
// Find the smallest GCD value g such that:
//
// prefix[g] > query
//
// Use Binary Search to find it.

// Time Complexity:
// O(M log M + Q log M)
//
// M = maximum value in nums
// Q = number of queries

// Space Complexity:
// O(M)

// Java Solution:
class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int n = nums.length;
        int max = 50000;

        // frequency[x] = number of occurrences of x
        int[] frequency = new int[max + 1];

        for (int num : nums) {
            frequency[num]++;
        }

        // exactGcd[g] = number of pairs
        // whose GCD is exactly g
        long[] exactGcd = new long[max + 1];

        // Count pairs whose GCD is a multiple of g
        for (int g = 1; g <= max; g++) {

            long count = 0;

            for (int multiple = g;
                 multiple <= max;
                 multiple += g) {

                count += frequency[multiple];
            }

            exactGcd[g] = count * (count - 1) / 2;
        }

        // Remove pairs whose exact GCD is a multiple of g
        for (int g = max; g >= 1; g--) {

            for (int multiple = 2 * g;
                 multiple <= max;
                 multiple += g) {

                exactGcd[g] -= exactGcd[multiple];
            }
        }

        // Prefix sum of pairs by GCD
        long[] prefix = new long[max + 1];

        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exactGcd[g];
        }

        int[] answer = new int[queries.length];

        // Binary Search for each query
        for (int i = 0; i < queries.length; i++) {

            long query = queries[i];

            int left = 1;
            int right = max;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (prefix[mid] > query) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
        }

        return answer;
    }
}