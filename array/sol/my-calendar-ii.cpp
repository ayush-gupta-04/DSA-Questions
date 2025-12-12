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

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */
