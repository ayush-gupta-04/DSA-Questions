class MinHeap{
    List<Integer> heap;

    public MinHeap(){
        this.heap = new ArrayList<>();
    }

    public void offer(int a){
        heap.add(a);
        heapUp(heap.size()-1);
    }
    private void heapUp(int node){
        if(node == 0) return;

        int parent = parent(node);
        if(heap.get(parent) > heap.get(node)){
            swap(node, parent);
            heapUp(parent);
        }
        return;
    }
    public int poll() throws Exception{
        if(this.isEmpty()){
            throw new Exception("Empty heap !");
        }
        int smallest = heap.get(0);
        swap(0,heap.size()-1);
        heap.removeLast();
        heapDown(0);
        return smallest;
    }
    private void heapDown(int node){
        int left = left(node);
        int right = right(node);
        int smallestIdx = node;

        if(left < heap.size() && heap.get(left) < heap.get(node)){
            smallestIdx = left;
        }

        if(right < heap.size() && heap.get(right) < heap.get(smallestIdx)){
            smallestIdx = right;
        }

        if(smallestIdx != node){
            swap(node,smallestIdx);
            heapDown(smallestIdx);
        }

    }
    public int peek() throws Exception{
        if(this.isEmpty()){
            throw new Exception("Empty heap !");
        }
        return heap.get(0);
    }
    public boolean isEmpty(){
        return heap.size() == 0;
    }
    private void swap(int i , int j){
        int temp = heap.get(i);
        heap.set(i , heap.get(j));
        heap.set(j , temp);
    }
    private int left(int i){
        return 2*i + 1;
    }
    private int right(int i){
        return 2*i + 2;
    }
    private int parent(int i){
        return (i-1)/2;
    }
}
