// The key insight is that with only 4 numbers, we can think of this problem as repeatedly combining two numbers until we're left with just one final result.
// Think about how you would solve this manually: 
// you'd pick any two numbers, apply an operation between them, and get a result.
// Now you have 3 numbers instead of 4. 
// You'd repeat this process - pick two from these three, apply an operation, and now you have 2 numbers.
// Finally, apply one last operation to get a single number, and check if it equals 24.


class Solution {
public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> numbers;
        vector<char> operators = {'+','-','*','/'};

        for (int card : cards) {
            numbers.push_back(static_cast<double>(card));
        }
        return fun(numbers,operators);
    }

    double calc(double a,double b,char op){
        if(op == '+') return a + b;
        if(op == '-') return a - b;
        if(op == '*') return a * b;
        return a / b;
    }

    bool fun(vector<double>& nums,vector<char>& operators){
        if(nums.size() == 1){
            return abs(nums[0] - 24.0) < 1e-5;
        }
        for(int i = 0;i < nums.size();i++){
            for(int j = 0; j < nums.size();j++){
                if(i == j) continue;
                
                double a = nums[i];
                double b = nums[j];

                for(char op : operators){
                    double res = calc(a,b,op);

                    vector<double> rem;
                    rem.push_back(res);

                    for(int s = 0; s < nums.size();s++){
                        if(s != i && s != j){
                            rem.push_back(nums[s]);
                        }
                    }

                    // i have rem elem + res.
                    if(fun(rem , operators)) return true;
                }
            }
        }

        return false;
    }
};
