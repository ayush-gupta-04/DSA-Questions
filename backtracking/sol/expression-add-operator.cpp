// for a currNum .. try to add operator before it...and try to add it to the running ans.
// doing this is easy for + , -.
// for * -> we should know what was the last number .. so that we can multiply with it.
// the next sum will be then -> sum - lastNum + lastNum*currNum

class Solution {
public:
    vector<string> res;
    void fun(string& num , int s , long long lastNum , long long sum, string exp,int target){
        if(s == num.size()){
            if(sum == target){
                res.push_back(exp);
            }
            return;
        }

        for(int i = s ; i < num.size() ; i++){
            if (i > s && num[s] == '0') return;
            string currNumStr = num.substr(s,i - s + 1);
            long long currNum = stoll(currNumStr);
            if(s == 0){
                //start of exp;
                fun(num,i+1,currNum,currNum, exp + currNumStr , target);
            }else{
                fun(num , i + 1 , currNum , sum + currNum , exp + "+" + currNumStr ,target);
                fun(num , i + 1 , -currNum , sum - currNum , exp + "-" + currNumStr ,target);
                fun(num , i + 1 , lastNum*currNum , sum - lastNum + lastNum*currNum , exp + "*" + currNumStr ,target);
            }
        }
    }
    vector<string> addOperators(string num, int target) {
        fun(num,0,0,0,"",target);
        return res;
    }
};
