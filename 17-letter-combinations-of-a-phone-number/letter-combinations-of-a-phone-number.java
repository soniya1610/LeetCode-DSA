class Solution {
    public List<String> letterCombinations(String digits) {
        return func("", digits);
    }

    private ArrayList<String> func(String p, String up) {

        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        int digit = up.charAt(0) - '0';

        ArrayList<String> ans = new ArrayList<>();

        int start = (digit - 2) * 3;

        // Shift by one after 7 because 7 has 4 letters
        if (digit > 7) {
            start++;
        }

        int end = start + 3;

        // 7 and 9 have 4 letters
        if (digit == 7 || digit == 9) {
            end++;
        }

        for (int i = start; i < end; i++) {
            char ch = (char) ('a' + i);
            ans.addAll(func(p + ch, up.substring(1)));
        }

        return ans;
    }
}