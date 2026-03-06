// ------------------------------- METHOD-1 : TreeSet -------------------------------------------
// TC -> N + logN
// SC -> N
// 1. Have a tree-set to have everything in ASC order.
// 2. for an event just iterate and check if double booking or not.



class Event{
    int start;
    int end;
    public Event(int s, int e){
        this.start = s;
        this.end = e;
    }
}
class MyCalendar {
    TreeSet<Event> bookings;
    public MyCalendar() {
        bookings = new TreeSet<Event>((x , y) -> {
            if(x.start != y.start) return Integer.compare(x.start , y.start);
            else return Integer.compare(x.end , y.end);
        });
    }
    
    public boolean book(int s, int e) {
        for(Event event : bookings){
            if(!(e <= event.start || s >= event.end)){
                // double booking.
                return false;
            }
        }
        Event newEvent = new Event(s,e);
        bookings.add(newEvent);
        return true;
    }
}




// -------------------------- METHOD-2 : TreeSet (floor , ceil) -----------------------
// TC -> logN
// SC -> N
// 1. Have a tree-set to have everything in ASC order.
// 2. for an event :
//    find it's floor , it's ceiling.
//    if event does'nt overlapp with any of these , good to go.
//    otherwise return false;



class Event{
    int start;
    int end;
    public Event(int s, int e){
        this.start = s;
        this.end = e;
    }
}
class MyCalendar {
    TreeSet<Event> bookings;
    public MyCalendar() {
        bookings = new TreeSet<Event>((x , y) -> {
            if(x.start != y.start) return Integer.compare(x.start , y.start);
            else return Integer.compare(x.end , y.end);
        });
    }
    
    public boolean book(int s, int e) {
        Event eve = new Event(s,e);
        Event floorEve = bookings.floor(eve);
        Event ceilingEve = bookings.ceiling(eve);
        if(floorEve != null){
            if(overlap(eve,floorEve)) return false;
        }
        if(ceilingEve != null){
            if(overlap(eve,ceilingEve)) return false;
        }
        bookings.add(eve);
        return true;
    }
    public boolean overlap(Event e1 , Event e2){
        return (!(e2.end <= e1.start || e2.start >= e1.end));
    }
}



// ------------------- METHOD - 3 : BST ------------------------------------
// Intervals that end before a node's start time go to the left subtree
// Intervals that start after a node's end time go to the right subtree
// If it overlaps with a node during traversal, we reject it.

// TC -> logN (avg case) , N (worst case)
// SC -> N
class TreeNode {
    int start, end;
    TreeNode left, right;

    TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = null;
        this.right = null;
    }
}

public class MyCalendar {
    private TreeNode root;

    public MyCalendar() {
        root = null;
    }

    private boolean insert(TreeNode node, int start, int end) {
        if (end <= node.start) {
            if (node.left == null) {
                node.left = new TreeNode(start, end);
                return true;
            }
            return insert(node.left, start, end);
        } else if (start >= node.end) {
            if (node.right == null) {
                node.right = new TreeNode(start, end);
                return true;
            }
            return insert(node.right, start, end);
        }
        return false;
    }

    public boolean book(int startTime, int endTime) {
        if (root == null) {
            root = new TreeNode(startTime, endTime);
            return true;
        }
        return insert(root, startTime, endTime);
    }
}
