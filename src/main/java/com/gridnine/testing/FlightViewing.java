package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightViewing extends Flight {

    FlightViewing(List<Segment> segs) {
        super(segs);
    }

    public LocalDateTime getDepartureDate(){
        return getSegments().get(0).getDepartureDate();
    }

    public boolean isValidated(){
        return getSegments().stream().allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()));
    }

    public Duration getTravelTime(){
        if(!getSegments().isEmpty()){
            return Duration.between(getSegments().get(0).getDepartureDate(), getSegments().get(getSegments().size()-1).getArrivalDate());
        }else {
            return Duration.ZERO;
        }
    }

    public Duration getWaitingTimeForTransfer(){
        int size = getSegments().size();
        if(size > 1){
            Duration waitingTimeForTransfer = this.getTravelTime();
            for (int i = 0; i < size; i++) {
                Duration travelTimeOnSegment = Duration.between(getSegments().get(i).getDepartureDate(), getSegments().get(i).getArrivalDate());
                waitingTimeForTransfer = waitingTimeForTransfer.minus(travelTimeOnSegment);
            }
            return waitingTimeForTransfer;
        }else {
            return Duration.ZERO;
        }
    }

}
