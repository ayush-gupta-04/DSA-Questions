// posts -> a map of..... userId -> {[postId1 , time1] , [postId2 , time2] ...}
// following -> a map of .... userId -> hashSet of {others whom user follows}.
// for feed , take out all the posts of user and other users which user follows.
// put them in pq according to time.
// maintain a min-heap of max-size 10.

class Twitter {
    HashMap<Integer , List<int[]>> posts;
    HashMap<Integer , HashSet<Integer>> following;
    static int time = 0;
    public Twitter() {
        this.posts = new HashMap<>();
        this.following = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        if(!posts.containsKey(userId)){
            posts.put(userId , new ArrayList<>());
        }
        posts.get(userId).add(new int[]{Twitter.time++ , tweetId});
    }
    
    public List<Integer> getNewsFeed(int userId) {
        // we will maintain the size of pq to be 10 only.
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> Integer.compare(x[0],y[0]));   // make it min-heap.
        List<Integer> ans = new ArrayList<>();
        if(posts.containsKey(userId)) {
            for(int[] post : posts.get(userId)){
                pq.offer(post);
                if(pq.size() > 10) pq.poll();
            }
        }

        if(following.containsKey(userId)){
            for(int otherId : following.get(userId)){
                if(posts.containsKey(otherId)){
                    for(int[] post : posts.get(otherId)){
                        pq.offer(post);
                        if(pq.size() > 10) pq.poll();
                    }
                }
            }
        }
        
        while(!pq.isEmpty()){
            ans.add(pq.poll()[1]);
        }
        Collections.reverse(ans);
        return ans;
    }
    
    public void follow(int followerId, int followeeId) {
        if(!following.containsKey(followerId)){
            following.put(followerId , new HashSet<>());
        } 
        following.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(following.containsKey(followerId)){
            following.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
