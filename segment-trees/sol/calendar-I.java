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
