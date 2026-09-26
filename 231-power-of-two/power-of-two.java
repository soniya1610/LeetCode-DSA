class Solution {
    public boolean isPowerOfTwo(int n) {
        //return n > 0 && ( n & (n -1)) == 0;
        return fun(n);
    } 
    //using recursion 
    private boolean fun(int n) {

        // Base cases
        if (n == 1) {
            return true;
        }

        if (n <= 0 || n % 2 != 0) {
            return false;
        }

        // Recursive call
        return fun(n / 2);
    }
}