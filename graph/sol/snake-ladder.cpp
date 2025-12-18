// Simple BFS with unit weight.
// Min distance of a node is found at the first time itself.
// if i am at 1 .. i can jump to (2 ,3 , 4 , 5, 6 ,7).
// There is a ladder from 2 -> 15.
// i won't add 2 in my queue .. i will add 15 (if dist of 15 == -1) iff 15 is not alredy visited.

// at a node i don't need to know whether i come from a ladder for snake .. because if there is a ladder there, 
// i am not even using it .. i am rolling a dice and then jumping.
// and in that jump .. at the dest. of the jump if i found a ladder then i am using that ladder directly.


class Solution {
public:
    typedef pair<int,int> p;
    static pair<int,int> findRowCol(int n , int num){
        int forRow = (num - 1) / n;
        int row = n - forRow - 1;
        int col = -1;
        if(forRow % 2 == 0){
            col = (num - 1) % n;
        }else{
            col = (n - ((num-1)%n) - 1);
        }

        return {row,col};
    }
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        vector<int> dist(n*n + 1 , -1);
        queue<p> q;
        
        dist[1] = 0;
        q.push({0 , 1});

        while(!q.empty()){
            int steps = q.front().first;
            int node = q.front().second;
            q.pop();

            if(node == n*n) return steps;

            int left = node + 1;
            int right = min(node + 6 , n*n);


            for(int v = left ; v <= right ; v++){
                int nr = findRowCol(n , v).first;
                int nc = findRowCol(n , v).second;

                if(board[nr][nc] != -1){
                    int nextNode = board[nr][nc];  // the ladder or the snake.
                    if(dist[nextNode] == -1){
                        dist[nextNode] = steps + 1;
                        q.push({dist[nextNode],nextNode});
                    }
                }else{
                    if(dist[v] == -1){
                        dist[v] = steps + 1;
                        q.push({dist[v],v});
                    }
                }
                
            }
        }
        return dist[n*n];
    }
};
