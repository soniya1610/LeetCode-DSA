// Problem Name:
// 1358. Number of Substrings Containing All Three Characters

// Approach:
// 1. Track the latest index of 'a', 'b', and 'c'.
// 2. Traverse the string character by character.
// 3. Update the index of current character.
// 4. Once all three characters are seen:
//      - Find the minimum among last seen indices of a, b, c.
//      - Any substring starting from index 0 to min and ending at current index
//        will contain all three characters.
// 5. Add (min + 1) to total count.

// Why (min + 1)?
// Suppose:
// last a = 5
// last b = 3
// last c = 7
// min = 3
//
// Valid starting positions = 0,1,2,3  => total 4
// Hence add (min + 1)

// Time Complexity:
// O(n)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int numberOfSubstrings(String s) {
        int a = -1;
        int b = -1;
        int c = -1;
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'a') {
                a = i;
            } else if (ch == 'b') {
                b = i;
            } else {
                c = i;
            }

            if (a > -1 && b > -1 && c > -1) {
                int min = Math.min(a, Math.min(b, c));
                total += (min + 1);
            }
        }

        return total;
    }
}