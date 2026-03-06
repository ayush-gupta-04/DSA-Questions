// ------------------------------------------------ BRUTE FORCE ---------------------------------------------------------------------
// TC -> N
// SC -> N
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
    List<Event> singleBooking;
    List<Event> doubleBooking;
    static class Event{
        int start;
        int end;
        public Event(int s, int e){
            this.start = s;
            this.end = e;
        }
    }
    public MyCalendarTwo() {
        singleBooking = new ArrayList<>();
        doubleBooking = new ArrayList<>();
    }
    
    public boolean book(int s, int e) {
        Event newEve = new Event(s,e);
        if(doubleBooking(newEve)){
            return false;
        }
        for(Event eve : singleBooking){
            int a = Math.max(eve.start , newEve.start);
            int b = Math.min(eve.end , newEve.end);
            if(b > a){
                doubleBooking.add(new Event(a,b));
            }
        }
        singleBooking.add(newEve);
        return true;
    }
    private boolean doubleBooking(Event event){
        for(Event e : doubleBooking){
            if(!(event.end <= e.start || event.start >= e.end)){
                return true;
            }
        }
        return false;
    }
}



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
