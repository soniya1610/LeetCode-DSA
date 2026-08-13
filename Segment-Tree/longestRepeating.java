// Problem Name:
// 2213. Longest Substring of One Repeating Character

// Topic:
// Segment Tree, String, Range Query, Point Update

// Approach:
// We need to find the longest substring consisting of
// the same character after every character update.
//
// A normal traversal after every update would take O(n),
// which is too slow for many queries.
//
// So we use a Segment Tree.
//
// Each Node stores:
// 1. pre      -> longest same-character prefix
// 2. suf      -> longest same-character suffix
// 3. maxLen   -> longest repeating substring in this range
// 4. leftChar -> first character of the range
// 5. rightChar -> last character of the range
//
// Merge:
// When combining two adjacent ranges L and R:
//
// - If L.rightChar == R.leftChar, their suffix and prefix
//   can be combined.
// - Update the prefix if the entire left range is a
//   repeating sequence.
// - Update the suffix if the entire right range is a
//   repeating sequence.
// - The longest answer can either be completely inside
//   L, completely inside R, or cross the boundary.
//
// Update:
// Each query changes only one character, so we perform
// a point update in O(log n).
//
// The root of the Segment Tree always represents the
// complete string, so segTree[0].maxLen gives the answer
// after every update.
//
// Time Complexity:
// Build: O(n)
// Each update: O(log n)
// Total: O(n + q log n)
//
// q = number of queries
//
// Space Complexity:
// O(n)
// Segment Tree requires O(4n) nodes.

class Solution {

    static class Node {

        int pre;       // Longest repeating prefix
        int suf;       // Longest repeating suffix
        int maxLen;    // Longest repeating substring

        char leftChar; // First character
        char rightChar; // Last character

        Node() {}

        Node(int pre, int suf, int maxLen,
             char leftChar, char rightChar) {

            this.pre = pre;
            this.suf = suf;
            this.maxLen = maxLen;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    int n;
    Node[] segTree;

    // Merge two adjacent nodes.
    Node merge(Node L, Node R,
               int leftLen, int rightLen) {

        Node res = new Node();

        res.leftChar = L.leftChar;
        res.rightChar = R.rightChar;

        // Prefix
        res.pre = L.pre;

        if (L.pre == leftLen &&
            L.rightChar == R.leftChar) {

            res.pre = L.pre + R.pre;
        }

        // Suffix
        res.suf = R.suf;

        if (R.suf == rightLen &&
            L.rightChar == R.leftChar) {

            res.suf = R.suf + L.suf;
        }

        // Maximum inside either half
        res.maxLen = Math.max(L.maxLen, R.maxLen);

        // Maximum crossing the boundary
        if (L.rightChar == R.leftChar) {

            res.maxLen = Math.max(
                res.maxLen,
                L.suf + R.pre
            );
        }

        return res;
    }

    // Build Segment Tree
    void buildSegmentTree(int i, int l, int r, String s) {

        if (l == r) {

            segTree[i] =
                new Node(
                    1,
                    1,
                    1,
                    s.charAt(l),
                    s.charAt(l)
                );

            return;
        }

        int mid = l + (r - l) / 2;

        buildSegmentTree(
            2 * i + 1,
            l,
            mid,
            s
        );

        buildSegmentTree(
            2 * i + 2,
            mid + 1,
            r,
            s
        );

        segTree[i] = merge(
            segTree[2 * i + 1],
            segTree[2 * i + 2],
            mid - l + 1,
            r - mid
        );
    }

    // Point Update
    void update(int i, int l, int r,
               int pos, char ch) {

        if (l == r) {

            segTree[i] =
                new Node(1, 1, 1, ch, ch);

            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {

            update(
                2 * i + 1,
                l,
                mid,
                pos,
                ch
            );

        } else {

            update(
                2 * i + 2,
                mid + 1,
                r,
                pos,
                ch
            );
        }

        // Recalculate current node
        segTree[i] = merge(
            segTree[2 * i + 1],
            segTree[2 * i + 2],
            mid - l + 1,
            r - mid
        );
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices) {

        n = s.length();

        segTree = new Node[4 * n];

        buildSegmentTree(
            0,
            0,
            n - 1,
            s
        );

        int q = queryIndices.length;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {

            int pos = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(
                0,
                0,
                n - 1,
                pos,
                ch
            );

            // Root represents the entire string
            result[i] = segTree[0].maxLen;
        }

        return result;
    }
}