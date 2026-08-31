// Problem Name:
// 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points

// Topic:
// Linked List, Traversal

// Approach:
// Traverse the linked list while keeping track of three
// consecutive values:
//
// prevValue -> previous node
// currValue -> current node
// nextValue -> next node
//
// A node is a critical point if it is:
// - A local minimum: prev > curr < next
// - A local maximum: prev < curr > next
//
// For the first critical point, store its index.
//
// For every next critical point:
// - Minimum distance = minimum gap between consecutive
//   critical points.
// - Maximum distance = current index - first critical index.
//
// If fewer than two critical points exist, return {-1, -1}.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCriticalIndex = 0;
        int previousCriticalIndex = 0;

        int prevValue = 0;
        int currValue = 0;
        int nextValue = 0;

        int minDistance = Integer.MAX_VALUE;
        int index = 0;

        int[] result = {-1, -1};

        while (head != null) {

            prevValue = currValue;
            currValue = nextValue;
            nextValue = head.val;

            // Check whether current node is a critical point
            if (prevValue != 0 && currValue != 0 && nextValue != 0 &&
                ((prevValue > currValue && currValue < nextValue) ||
                 (prevValue < currValue && currValue > nextValue))) {

                // First critical point
                if (firstCriticalIndex == 0) {
                    firstCriticalIndex = index;
                } 
                // Another critical point
                else {
                    minDistance = Math.min(
                        minDistance,
                        index - previousCriticalIndex
                    );

                    result[0] = minDistance;

                    // Distance between first and current critical point
                    result[1] = index - firstCriticalIndex;
                }

                previousCriticalIndex = index;
            }

            index++;
            head = head.next;
        }

        return result;
    }
}