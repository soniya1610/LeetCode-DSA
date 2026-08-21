// Problem Name:
// 3116. Kth Smallest Amount With Single Denomination Combination

// Topic:
// Binary Search, Inclusion-Exclusion Principle, GCD, LCM

// Approach:
// We need to find the kth smallest number that is divisible
// by at least one of the given coins.
//
// 1. Binary Search:
//    Search for the smallest value `mid` such that there are
//    at least k valid numbers <= mid.
//
// 2. Inclusion-Exclusion:
//    countSmaller(mid) calculates how many numbers <= mid
//    are divisible by at least one coin.
//
//    For every non-empty subset of coins:
//    - Odd number of selected coins  -> add
//    - Even number of selected coins -> subtract
//
//    The LCM of the selected coins is used because a number
//    divisible by all selected coins must be divisible by
//    their LCM.
//
// 3. GCD:
//    LCM(a, b) = a / gcd(a, b) * b
//
// 4. Binary Search:
//    If countSmaller(mid) >= k, mid can be the answer,
//    so search on the left side.
//    Otherwise, search on the right side.
//
// Time Complexity:
// O(log(maxCoin * k) * 2^n * n * log(maxCoin))
//
// Space Complexity:
// O(1)

class Solution {

    private long countSmaller(long mid, int[] coins) {

        long correctedCount = 0;
        int n = coins.length;

        // Try every non-empty subset of coins
        for (int expressions = 1;
             expressions <= (1 << n) - 1;
             expressions++) {

            long lcm = 0;
            long order = 0;

            for (int i = 0; i < n; i++) {

                if ((expressions & (1 << i)) != 0) {

                    order++;

                    if (lcm == 0) {
                        lcm = coins[i];
                    } else {
                        lcm = lcm * coins[i] / gcd(lcm, coins[i]);
                    }
                }
            }

            // Inclusion-Exclusion
            if (order % 2 == 0) {
                correctedCount -= mid / lcm;
            } else {
                correctedCount += mid / lcm;
            }
        }

        return correctedCount;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long findKthSmallest(int[] coins, int k) {

        long result = -1;

        int maxCoin = 0;

        for (int c : coins) {
            maxCoin = Math.max(maxCoin, c);
        }

        long l = 1;
        long r = (long) maxCoin * k;

        // Binary Search
        while (l <= r) {

            long mid = l + (r - l) / 2;

            if (countSmaller(mid, coins) >= k) {

                result = mid;
                r = mid - 1;

            } else {

                l = mid + 1;
            }
        }

        return result;
    }
}