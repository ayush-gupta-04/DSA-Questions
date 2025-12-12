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


// The Line Sweep algorithm works by marking when bookings start and end.
// For each booking (start, end), we mark the start point by increasing its count by 1 
//     (indicating a booking begins), and we mark the end point by decreasing its count by 1 
//     (indicating a booking ends).
// These marks are stored in a map, which keeps track of the number of bookings starting or ending at each point.
// Once all bookings are processed, we compute the prefix sum over the map
// The prefix sum at any point tells us how many active bookings overlap at that moment. 
// If the sum at any point exceeds 2, it means we have a triple booking. 

class MyCalendarTwo {
public:
    // Store the number of bookings at each point.
    map<int, int> bookingCount;

    MyCalendarTwo() { }

    bool book(int start, int end) {
        // Increase and decrease the booking count at the start and end
        // respectively.
        bookingCount[start]++;
        bookingCount[end]--;

        int overlappedBooking = 0;
        // Find the prefix sum.
        for (pair<int, int> bookings : bookingCount) {
            overlappedBooking += bookings.second;

            // If the number of bookings is more than 2, return false.
            // Also roll back the counts for this booking as we won't add it.
            if (overlappedBooking > 2) {
                bookingCount[start]--;
                bookingCount[end]++;

                // Remove the entries from the map to avoid unnecessary
                // iteration.
                return false;
            }
        }

        return true;
    }
};
