public class NumberOfZigZagArrays {
    static final long MOD = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {
        long m = r - l + 1;

        if (n == 1) {
            return (int) (m % MOD);
        }

        long[] up = new long[(int) m + 1];
        long[] down = new long[(int) m + 1];

        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;
            down[v] = m - v;
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[(int) m + 1];
            long[] newDown = new long[(int) m + 1];

            long pref = 0;
            for (int v = 1; v <= m; v++) {
                newUp[v] = pref;
                pref = (pref + down[v]) % MOD;
            }

            long suff = 0;
            for (int v = (int) m; v >= 1; v--) {
                newDown[v] = suff;
                suff = (suff + up[v]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}
