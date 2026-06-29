// Problem Name:
// 1967. Number of Strings That Appear as Substrings in Word

// Approach:
// 1. Traverse each string in patterns.
// 2. Check whether word contains that string as a substring using contains().
// 3. If yes, increment count.
// 4. Return count.

// Time Complexity:
// O(n * m)
// n = number of patterns
// m = average substring search cost (depends on contains implementation)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;

        for (String s : patterns) {
            if (word.contains(s)) {
                count++;
            }
        }

        return count;
    }
}
