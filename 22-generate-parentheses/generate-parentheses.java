class Solution {
    public List<String> generateParenthesis(int n) {
        return allCombinations("", n, n);
    } 
    private List<String> allCombinations(String p, int open, int close) {

        if (open == 0 && close == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        } 
        ArrayList<String> ans = new ArrayList<>(); 
        if (open > 0) {
            ans.addAll(allCombinations(p + "(", open - 1, close));
        }
        if (close > open) {
            ans.addAll(allCombinations(p + ")", open, close - 1));
        }
        return ans;
    }
}