// Thought process :
// 1. If i know the max of the whole buckets be x...
// 2. and x < fruit .. i am damm sure that i won;t have any buckets.
// 3. if x >= fruit ... i can have a basket.
//    ans i have to process from the left -> right .. 
//    so i will look to the left half first then the right half..
//    ans this structure is built by segment trees.
// 4. when a basket is choosen then it will update the max .. point update.






class SegmentTree{

private :
    vector<int> seg;

public: 
    SegmentTree(int N){
        this->seg.resize(4*N);
    }

    void build(int idx ,int low ,int high ,vector<int>& nums){
        if(low == high){
            seg[idx] = nums[low];
            return;
        }
        int m = low + (high - low)/2;
        build(2*idx + 1 , low , m , nums);
        build(2*idx + 2 , m+1 , high , nums);
        seg[idx] = max(seg[2*idx + 1] , seg[2*idx + 2]);
    }

    bool update(int idx , int low , int high , int val){
        // we don;t have any basket.
        if(val > seg[idx]){
            return false;
        }


        // this basket will be acquired.
        if(low == high){
            seg[idx]  = -1;
            return true;
        }


        
        int m = low + (high - low)/2;
        bool placed = false;

        // i will process from left to right.
        if(seg[idx*2 + 1] >= val){
            placed = update(2*idx + 1 , low , m , val);  // going left.
        }else{
            placed = update(2*idx + 2 , m + 1 ,high , val);   // goint right.
        }

        // process this node as child nodes might have been changed.
        seg[idx] = max(seg[2*idx + 1] , seg[2*idx + 2]);
        return placed;
    }

    ~SegmentTree(){};
};








class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int cnt = 0;
        int n = fruits.size();
        SegmentTree* seg = new SegmentTree(n);
        seg->build(0,0,n-1,baskets);

        for(auto& f : fruits){
            if(!seg->update(0,0,n-1,f)){
                cnt++;
            }
        }
        return cnt;
    }
};



