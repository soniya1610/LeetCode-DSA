// Problem Name:
// 3500. Maximize Active Sections After Trade II

// Topic:
// Strings, Prefix Processing, Run-Length Encoding,
// Sparse Table, RMQ, Range Maximum Query

// Approach:
// Step 1:
// Count the total number of '1's in the complete string.
//
// This is the base answer for every query.
//
// Step 2:
// Compress the string into segments (runs).
//
// Example:
//
// "001110011"
//
// becomes:
//
// 000 | 111 | 00 | 11
//
// For every segment, store:
//
// type[i]  = 0 or 1
// start[i] = starting index
// end[i]   = ending index
//
// Step 3:
// For every position, store the segment containing it:
//
// posToSeg[position] = segment index
//
// This allows us to find the segment of query boundaries
// in O(1).
//
// Step 4:
// For every middle segment of type '1',
// calculate the possible gain:
//
// gain[i] = length(left 0-segment)
//         + length(right 0-segment)
//
// This represents the number of additional active sections
// obtained by performing the trade around this 1-segment.
//
// Step 5:
// Build a Sparse Table over all gains.
//
// This allows us to find the maximum gain in any range
// in O(1) after O(N log N) preprocessing.
//
// Step 6:
// For every query [L, R]:
//
// Find:
//
// segL = segment containing L
// segR = segment containing R
//
// Only 1-segments between these boundaries can be used.
//
// There are three possibilities:
//
// 1. A candidate near the left boundary.
//    Its left 0-block may be partially included.
//
// 2. A candidate near the right boundary.
//    Its right 0-block may be partially included.
//
// 3. A completely internal candidate.
//    Use the Sparse Table to find the maximum gain.
//
// Final answer:
//
// totalOnes + maximumGain

// Time Complexity:
// Run construction: O(n)
// Sparse Table: O(N log N)
// Each query: O(1)
//
// Total:
// O(n + N log N + q)
//
// N = number of segments
//
// Since N <= n:
//
// O(n log n + q)

// Space Complexity:
// O(n log n)

// Java Solution:
class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(
            String s, int[][] queries) {

        int n = s.length();
        int totalOnes = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        // Run-Length Encoding
        List<Integer> typeList = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        for (int i = 0; i < n; ) {

            int j = i;

            while (j < n
                    && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            typeList.add(s.charAt(i) - '0');
            startList.add(i);
            endList.add(j - 1);

            i = j;
        }

        int N = typeList.size();

        int[] type = new int[N];
        int[] start = new int[N];
        int[] endIdx = new int[N];

        for (int i = 0; i < N; i++) {
            type[i] = typeList.get(i);
            start[i] = startList.get(i);
            endIdx[i] = endList.get(i);
        }

        // Map every position to its segment
        int[] posToSeg = new int[n];

        for (int i = 0; i < N; i++) {
            for (int j = start[i];
                 j <= endIdx[i];
                 j++) {

                posToSeg[j] = i;
            }
        }

        // gain[i] = length of adjacent 0-segments
        int[] gain = new int[N];

        for (int i = 1; i < N - 1; i++) {

            if (type[i] == 1) {

                gain[i] =
                    (endIdx[i - 1] - start[i - 1] + 1)
                    +
                    (endIdx[i + 1] - start[i + 1] + 1);
            }
        }

        // Log table
        int[] logTable = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            logTable[i] =
                logTable[i / 2] + 1;
        }

        // Sparse Table
        int K = logTable[N] + 1;
        int[][] st = new int[K][N];

        for (int i = 0; i < N; i++) {
            st[0][i] = gain[i];
        }

        for (int j = 1; j < K; j++) {

            for (int i = 0;
                 i + (1 << j) <= N;
                 i++) {

                st[j][i] = Math.max(
                    st[j - 1][i],
                    st[j - 1][
                        i + (1 << (j - 1))
                    ]
                );
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {

            int L = query[0];
            int R = query[1];

            int segL = posToSeg[L];
            int segR = posToSeg[R];

            // Not enough segments for a valid trade
            if (segR - segL < 2) {
                result.add(totalOnes);
                continue;
            }

            int maxGain = 0;

            // Candidate near left boundary
            maxGain = Math.max(
                maxGain,
                evaluateEdge(
                    segL + 1,
                    L,
                    R,
                    segL,
                    segR,
                    type,
                    start,
                    endIdx
                )
            );

            // Candidate near right boundary
            maxGain = Math.max(
                maxGain,
                evaluateEdge(
                    segR - 1,
                    L,
                    R,
                    segL,
                    segR,
                    type,
                    start,
                    endIdx
                )
            );

            // Completely internal candidates
            if (segL + 2 <= segR - 2) {

                int left = segL + 2;
                int right = segR - 2;

                int j = logTable[right - left + 1];

                int rmqVal = Math.max(
                    st[j][left],
                    st[j][
                        right - (1 << j) + 1
                    ]
                );

                maxGain = Math.max(
                    maxGain,
                    rmqVal
                );
            }

            result.add(totalOnes + maxGain);
        }

        return result;
    }

    private int evaluateEdge(
            int i,
            int L,
            int R,
            int segL,
            int segR,
            int[] type,
            int[] start,
            int[] endIdx) {

        if (i <= segL || i >= segR) {
            return 0;
        }

        if (type[i] == 0) {
            return 0;
        }

        int leftLen;

        if (i - 1 == segL) {
            leftLen =
                Math.max(
                    0,
                    endIdx[i - 1] - L + 1
                );
        } else {
            leftLen =
                endIdx[i - 1]
                - start[i - 1]
                + 1;
        }

        int rightLen;

        if (i + 1 == segR) {
            rightLen =
                Math.max(
                    0,
                    R - start[i + 1] + 1
                );
        } else {
            rightLen =
                endIdx[i + 1]
                - start[i + 1]
                + 1;
        }

        return leftLen + rightLen;
    }
}