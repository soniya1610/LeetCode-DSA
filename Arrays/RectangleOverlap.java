/*
 * Problem: Rectangle Overlap
 * Topic: Geometry, Arrays
 *
 * Approach:
 * Two rectangles overlap only if their projections on both
 * the X-axis and Y-axis overlap.
 *
 * Conditions:
 * 1. rec1's right edge  > rec2's left edge
 * 2. rec1's top edge    > rec2's bottom edge
 * 3. rec1's left edge   < rec2's right edge
 * 4. rec1's bottom edge < rec2's top edge
 *
 * If all four conditions are true, the rectangles have a
 * positive-area overlap.
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        return (rec1[2] > rec2[0] &&
                rec1[3] > rec2[1] &&
                rec1[0] < rec2[2] &&
                rec1[1] < rec2[3]);
    }
}
