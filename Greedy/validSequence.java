// Problem Name:
// 3302. Find the Lexicographically Smallest Valid Sequence

// Topic:
// String, Greedy, Two Pointers, Subsequence, Prefix/Suffix Matching

// Approach:
// Step 1:
// We need to select indices from word1 to form word2
// as a subsequence, with permission to change
// at most one character.
//
// Step 2:
// Precompute:
//
// rightHandSideMatchLength[i]
//
// It stores how many characters of word2 can be
// matched from the suffix word1[i...n-1].
//
// This helps us determine whether, after changing
// the current character, the remaining characters
// of word2 can still be matched.
//
// Step 3:
// Traverse word1 from left to right.
//
// Case 1:
// If word1[i] == word2[j],
// select index i normally.
//
// Case 2:
// If they don't match, we can use our one allowed
// character change.
//
// We use the change only if:
//
// rightHandSideMatchLength[i + 1] >= m - j - 1
//
// This means the remaining part of word2 can be
// successfully matched after using index i.
//
// Step 4:
// Store selected indices in seq.
//
// Since we scan from left to right and always select
// the earliest possible valid index, the resulting
// sequence is lexicographically smallest.
//
// Step 5:
// If all m characters of word2 are matched,
// return seq.
//
// Otherwise, return an empty array.

// Time Complexity:
// O(n + m)

// Space Complexity:
// O(n + m)
//
// O(n) for the suffix matching array
// and O(m) for the answer.

// Java Solution:
class Solution {

    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // rightHandSideMatchLength[i] =
        // number of characters of word2 that can be
        // matched using word1[i...n-1].
        int[] rightHandSideMatchLength = new int[n];

        int rightMatched = 0;

        int i = n - 1;
        int j = m - 1;

        while (i >= 0) {

            if (j >= 0 &&
                word1.charAt(i) == word2.charAt(j)) {

                rightMatched++;
                j--;
            }

            rightHandSideMatchLength[i] = rightMatched;
            i--;
        }

        int[] seq = new int[m];

        int idx = 0;

        // We can change at most one character.
        boolean changePower = true;

        i = 0;
        j = 0;

        while (i < n && j < m) {

            // Characters already match
            if (word1.charAt(i) == word2.charAt(j)) {

                seq[idx++] = i;
                j++;
            }

            // Use the one allowed modification
            else if (changePower
                    && i + 1 < n
                    && rightHandSideMatchLength[i + 1] >= m - j - 1) {

                seq[idx++] = i;
                j++;

                changePower = false;
            }

            i++;
        }

        // Return sequence only if word2 was fully matched.
        return j == m ? seq : new int[0];
    }
}