#include <bits/stdc++.h>
using namespace std;

class SegmentTree {
private:
    vector<int> seg;
    int n;

    void build(int idx, int low, int high, const vector<int>& nums) {
        if (low == high) {
            seg[idx] = nums[low];
            return;
        }

        int mid = low + (high - low) / 2;
        build(2 * idx + 1, low, mid, nums);
        build(2 * idx + 2, mid + 1, high, nums);

        seg[idx] = seg[2 * idx + 1] + seg[2 * idx + 2];
    }

    void update(int idx, int low, int high, int pos, int val) {
        if (low == high) {
            seg[idx] = val;
            return;
        }

        int mid = low + (high - low) / 2;

        if (pos <= mid)
            update(2 * idx + 1, low, mid, pos, val);
        else
            update(2 * idx + 2, mid + 1, high, pos, val);

        seg[idx] = seg[2 * idx + 1] + seg[2 * idx + 2];
    }

    int query(int idx, int low, int high, int l, int r) {
        // No overlap
        if (r < low || high < l)
            return 0;

        // Total overlap
        if (l <= low && high <= r)
            return seg[idx];

        // Partial overlap
        int mid = low + (high - low) / 2;
        int left = query(2 * idx + 1, low, mid, l, r);
        int right = query(2 * idx + 2, mid + 1, high, l, r);

        return left + right;
    }

public:
    SegmentTree(const vector<int>& nums) {
        n = nums.size();
        seg.resize(4 * n);
        build(0, 0, n - 1, nums);
    }

    void update(int pos, int val) {
        update(0, 0, n - 1, pos, val);
    }

    int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }
};
