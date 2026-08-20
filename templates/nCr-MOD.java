static final long MOD = 1_000_000_007L;
static long[] fact;
static long[] invFact;

static long power(long a, long b) {
    long result = 1;

    while (b > 0) {
        if ((b & 1) == 1)
            result = result * a % MOD;

        a = a * a % MOD;
        b >>= 1;
    }

    return result;
}

static void init(int N) {
    fact = new long[N + 1];
    invFact = new long[N + 1];

    fact[0] = 1;

    for (int i = 1; i <= N; i++)
        fact[i] = fact[i - 1] * i % MOD;

    invFact[N] = power(fact[N], MOD - 2);

    for (int i = N - 1; i >= 0; i--)
        invFact[i] = invFact[i + 1] * (i + 1) % MOD;
}

static long nCr(int n, int r) {
    if (r < 0 || r > n)
        return 0;

    return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
}
