package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FlightViewing extends Flight {

    FlightViewing(List<Segment> segs) {
        super(segs);
    }

    LocalDate getDeparture(){
        return getSegments().get(0).getDepartureDate().toLocalDate();
    }

    LocalDate getArrival(){
        int size = getSegments().size();
        return getSegments().get(size - 1).getArrivalDate().toLocalDate();
    }

    Duration getTravelTime(){
        if(!getSegments().isEmpty()){
            return Duration.between(getSegments().get(0).getDepartureDate(), getSegments().get(getSegments().size()-1).getArrivalDate());
        }else {
            return Duration.ZERO;
        }
    }

    int getCountOfTransfer(){
        return getSegments().size() - 1;
    }

    Duration getWaitingTimeForTransfer(){
        if(getSegments().size() > 1){
            Duration waitingTimeForTransfer = this.getTravelTime();
            for (int i = 0; i < getSegments().size(); i++) {
                LocalDateTime arrivalDate = getSegments().get(i).getArrivalDate();
                LocalDateTime departureDate = getSegments().get(i).getDepartureDate();
                Duration travelTime = Duration.between(departureDate, arrivalDate);
                if(departureDate.isAfter(arrivalDate)) travelTime = Duration.ZERO;
                waitingTimeForTransfer = waitingTimeForTransfer.minus(travelTime);
            }
            return waitingTimeForTransfer;
        }else {
            return Duration.ZERO;
        }
    }

}
