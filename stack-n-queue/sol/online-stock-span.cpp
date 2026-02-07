// We will maintain a monotonically DEC stack.
// If any element pops out from the stack .. then i must have the knowledge of the index of those elements.
// If the indexs are known then we can easily cacl the number of element in between.
// We will store {num , index} in the stack.

class StockSpanner {
private:
    stack<pair<int,int>> st;
    int i;
public:
    StockSpanner() {
        this->i = 0;
    }
    
    int next(int price) {
        while(!st.empty() && st.top().first <= price){
            // erase out of the map also.
            st.pop();
        }
        int ans = (st.empty() ? i+1 : i - st.top().second);
        st.push({price , i});
        i++;
        return ans;
    }
};

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner* obj = new StockSpanner();
 * int param_1 = obj->next(price);
 */
