// Problem Name:
// 1288. Remove Covered Intervals

// Topic:
// Arrays, Sorting, Greedy

// Approach:
// Step 1:
// Sort the intervals:
// - Start point in ascending order.
// - If start points are the same,
//   sort end point in descending order.
//
// Why descending end?
// So that larger interval comes first.
//
// Example:
// [1,4], [1,3]
//
// [1,4] appears first, making it easy to identify
// [1,3] as a covered interval.
//
// Step 2:
// Traverse the sorted intervals.
//
// Maintain:
// maxEnd = maximum ending point seen so far.
//
// Step 3:
// For each interval:
//
// - If interval.end > maxEnd
//      -> This interval is NOT covered.
//      -> Count it.
//      -> Update maxEnd.
//
// - Otherwise
//      -> interval.end <= maxEnd
//      -> Current interval is completely covered
//         by a previously processed interval.
//      -> Ignore it.
//
// Step 4:
// Return the count of uncovered intervals.

// Time Complexity:
// O(n log n)
// (Sorting dominates)

// Space Complexity:
// O(1)
// (Ignoring sorting space)

// Java Solution:
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                count++;
                maxEnd = interval[1];
            }
        }

        return count;
    }
}