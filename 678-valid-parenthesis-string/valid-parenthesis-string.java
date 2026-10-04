class Solution {
    public boolean checkValidString(String s) {
        int l = 0, r = 0;

        for (int i = 0; i < s.length(); i++) {
            l += s.charAt(i) == '(' ? 1 : -1;
            r += s.charAt(i) == ')' ? -1 : 1;

            if (r < 0) return false;

            l = Math.max(l, 0);
        }

        return l == 0;
    }
}