// Problem Name:
// 1846. Maximum Element After Decreasing and Rearranging

// Approach:
// 1. Sort the array in ascending order.
// 2. Set first element to 1 because arr[0] must be 1.
// 3. Traverse remaining elements:
//      - Each element can be at most previous + 1
//      - So set:
//          arr[i] = min(arr[i], arr[i-1] + 1)
// 4. The last element becomes the maximum possible value.

// Why it works:
// - Sorting helps us build numbers gradually.
// - We ensure:
//      arr[0] = 1
//      abs(arr[i] - arr[i-1]) <= 1
// - To maximize the final element, each number should increase by at most 1.

// Time Complexity:
// O(n log n)  -> Sorting

// Space Complexity:
// O(1)        -> Ignoring sorting space

// Java Solution:
import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);

        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }

        return arr[arr.length - 1];
    }
}
