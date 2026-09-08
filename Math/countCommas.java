```java
/*
 * Problem: Count Commas
 * Topic: Math, Simulation
 *
 * Approach:
 * Keep subtracting 1 from n while n is at least 1000.
 * Each subtraction represents one number containing a comma.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int countCommas(int n) {
        int count = 0;

        while (n >= 1000) {
            n -= 1;
            count++;
        }

        return count;
    }
}
```
