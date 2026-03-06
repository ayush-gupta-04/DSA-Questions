// ------------------------------------------------ BRUTE FORCE ---------------------------------------------------------------------
// Calendar - I
// -> for an event {s,e} .... 
//    if this event intersects with any of the other interval .. then we cannot add this event.
//    else we add this event to the set.


// Calendar - II
// -> we will maintain 2 sets ... allEvents , doubleBookings.
// -> doubleBooking -> will contain all the intervals which have the double booking.
// -> ex : a           b
//               c             d
//                ......
// -> we will add [c,b] to the double booking set.
// -> for an event {s,e} .... 
//    - check if it overlaps with any double booking .. if yes then return false.
//    - if it do not overlap with any double booking . then
//    - we will traverse in the allEvents set with event and find the overlapping intervals 
//      and add those intervals to the doubleBooking set.


class MyCalendarTwo {
private:
    set<pair<int,int>> db;
    set<pair<int,int>> cal;
public:
    MyCalendarTwo() {
        
    }
    bool book(int s, int e) {
        pair<int,int> event = {s,e};

        // if it overlapps with any doubleBooking intervals .. return false;
        if(isOverLapping(event)){
            return false;
        }

        // find the overlapping interval.
        // add those interval to the double booking set.
        for(auto& it : cal){
            int a = max(s , it.first);
            int b = min(e , it.second);
            if(b > a){
                db.insert({a,b});
            }
        }
        cal.insert({s,e});
        return true;

    }

    bool isOverLapping(pair<int,int>& event){
        for(auto& it : db){
            if(!(event.second <= it.first || it.second <= event.first)){
                return true;
            }
        }
        return false;
    }

};


// ------------------------------------------------ LINE SWEEP ALGORITHM ---------------------------------------------------------------------
// TC -> N
// SC -> N

// We will use line-sweep.
// Algo : 
//   - First make the booking.
//   - Check if number of overlapping > 2.
//   - If yes .. remove bookings , return false;
//   - If no .. return true;
// Data Structure : 
// - TreeMap because it will be easier to remove.
class MyCalendarTwo {
    TreeMap<Integer,Integer> bookings;
    public MyCalendarTwo() {
        this.bookings = new TreeMap<>();
    }
    
    public boolean book(int s, int e) {
        bookings.put(s,bookings.getOrDefault(s,0) + 1);
        bookings.put(e,bookings.getOrDefault(e,0) - 1);
        int cnt = 0;
        for(Map.Entry<Integer,Integer> entry : bookings.entrySet()){
            int val = entry.getValue();
            cnt += val;
            if(cnt > 2){
                bookings.put(s , bookings.get(s) - 1);
                bookings.put(e , bookings.get(e) + 1);
                if(bookings.get(s) == 0){
                    bookings.remove(s);
                }
                if(bookings.get(e) == 0){
                    bookings.remove(e);
                }
                return false;
            }
        }
        return true;
    }
}

    }
};
