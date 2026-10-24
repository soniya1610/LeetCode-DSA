class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) { 
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int AliceS = calc(arriveAlice , days);
        int AliceL = calc(leaveAlice , days);
        int BobS = calc(arriveBob , days);
        int BobL = calc(leaveBob , days);
        int start = Math.max(AliceS , BobS);
        int leave = Math.min(AliceL , BobL);

        return start <= leave ? leave-start+1 : 0;
    }
    private int calc(String s, int[] days) {
        int m = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        int d = (s.charAt(3) - '0') * 10 + (s.charAt(4) - '0');
        int res = d;
        for (int i = 0; i < m - 1; i++) res += days[i];
        return res;
    }
}