class Solution {
public:
    vector<int> nse(vector<int>& nums){
        int n = nums.size();
        vector<int> ans(n);
        stack<int> st;
        int i = n-1;
        while(i >= 0){
            while(!st.empty() && (nums[st.top()] >= nums[i])){
                st.pop();
            }
            ans[i] = (st.empty() ? n : st.top());
            st.push(i);
            i--;
        }
        return ans;
    }
    vector<int> psee(vector<int>& nums){
        int n = nums.size();
        vector<int> ans(n);
        stack<int> st;
        int i = 0;
        while(i < n){
            while(!st.empty() && (nums[st.top()] > nums[i])){
                st.pop();
            }
            ans[i] = (st.empty() ? -1 : st.top());
            st.push(i);
            i++;
        }
        return ans;
    }

    vector<int> nge(vector<int>& nums){
        int n = nums.size();
        vector<int> ans(n);
        stack<int> st;
        int i = n-1;
        while(i >= 0){
            while(!st.empty() && (nums[st.top()] <= nums[i])){
                st.pop();
            }
            ans[i] = (st.empty() ? n : st.top());
            st.push(i);
            i--;
        }
        return ans;
    }

    vector<int> pgee(vector<int>& nums){
        int n = nums.size();
        vector<int> ans(n);
        stack<int> st;
        int i = 0;
        while(i < n){
            while(!st.empty() && (nums[st.top()] < nums[i])){
                st.pop();
            }
            ans[i] = (st.empty() ? -1 : st.top());
            st.push(i);
            i++;
        }
        return ans;
    }

    long long minRanges(vector<int>& nums){
        vector<int> nseArr = nse(nums);
        vector<int> pseeArr = psee(nums);
        for(auto it : nseArr){
            cout << it << ' ';
        }
        for(auto it : pseeArr){
            cout << it << ' ';
        }
        int n = nums.size();
        long long ans = 0;
        for(int i = 0 ;i < n ;i++){
            long long left = (i - pseeArr[i])*1LL;
            int right = (nseArr[i] - i)*1LL;
            ans += nums[i]*left*right*1LL;
        }
        return ans;
    }

    long long maxRanges(vector<int>& nums){
        vector<int> ngeArr = nge(nums);
        vector<int> pgeeArr = pgee(nums);
        int n = nums.size();
        long long ans = 0;
        for(int i = 0 ;i < n ;i++){
            long long left = (i - pgeeArr[i])*1LL;
            long long right = (ngeArr[i] - i)*1LL;
            ans += nums[i]*left*right*1LL;
        }
        return ans;
    }

    long long subArrayRanges(vector<int>& nums) {
        return maxRanges(nums) - minRanges(nums);
    }
};
