package com.gridnine.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Component displaying a list of flights.
 */

class FlightBrowser {
    private final List<Flight> flightList;

    FlightBrowser(List<Flight> flightList) {
        this.flightList = flightList;
    }

    void viewing() {
        List<Flight> testList = checkList(flightList);      // List validation for condition 1 and condition 2
        printInfo();                                        // the available commands are described in this method
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            Filter filter = Filter.ALL;
            String condition = " ";
            int index;
            do {
                testList = applyFilter(testList, filter, condition);
                System.out.println(testList);
                String command_line = bufferedReader.readLine();
                index = command_line.indexOf(" ");
                if(index==-1){
                    filter = Filter.valueOf(command_line.toUpperCase());
                    condition = " ";
                }else {
                    filter = Filter.valueOf(command_line.substring(0, index).toUpperCase());
                    condition = command_line.substring(index + 1);
                }
            } while (filter != Filter.EXIT);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Flight> applyFilter(List<Flight> testList, Filter filter, String condition) throws ParseException {
        switch (filter){
            case ALL:
                return checkList(flightList);
            case DEPARTURE:
                DateTimeFormatter formatD = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate dateD = LocalDate.parse(condition, formatD);
                return Filter.filterFlights(testList, Filter.isDeparture(dateD));
            case ARRIVAL:
                DateTimeFormatter formatA = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate dateA = LocalDate.parse(condition, formatA);
                return Filter.filterFlights(testList, Filter.isArrival(dateA));
            case TRAVEL_TIME:
                Long hoursT = Long.parseLong(condition);
                return Filter.filterFlights(testList, Filter.isTravelTimeLessThan(hoursT));
            case COUNT_OF_TRANSFER:
                Integer count = Integer.parseInt(condition);
                return Filter.filterFlights(testList, Filter.isCountOfTransferLessOrEqual(count));
            case WAITING_TIME:
                Long hoursW = Long.parseLong(condition);
                return Filter.filterFlights(testList, Filter.isWaitingTimeForTransferLessThan(hoursW));
            case HELP:
                printInfo();
            default:
                return testList;
        }
    }

    List<Flight> checkList(List<Flight> testList) {  // Третье правило вывел отдельно, так как его можно считать фильтром
        return conditionTwo(conditionOne(testList));
    }

    static List<Flight> conditionOne(List<Flight> testList) {
        return testList.stream()
                .filter(flight -> flight.getSegments()
                        .stream().findFirst().get().getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    static List<Flight> conditionTwo(List<Flight> testList) {
        return testList.stream()
                .filter(flight -> flight.getSegments()
                        .stream().allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
    }

    static List<Flight> conditionThree(List<Flight> testList) {  // Не включил данное условие в checkList(), т.к. оно относится к фильтрам
        return testList.stream()
                .filter(flight -> flight.getWaitingTimeForTransfer().toHoursPart() <= 2)
                .collect(Collectors.toList());
    }

    static void printInfo(){
        System.out.println("Для фильтрации и поиска нужного перелёта используйте команды :\n" +
                Arrays.toString(Filter.values()) + "\n ALL - снять все фильтры;" +
                "\n DEPARTURE dd.mm.yyyy - вывести перелёты с датой вылета;" +
                "\n ARRIVAL dd.mm.yyyy - вывести перелёты с датой прилёта;" +
                "\n TRAVEL_TIME 5 - вывести перелёты продолжительностью не более 5 часов;" +
                "\n COUNT_OF_TRANSFER 1 - вывести перелёты не более чем с 1 пересадкой;" +
                "\n WAITING_TIME 3 - вывести перелёты с ожиданием пересадки не более 3 часов;" +
                "\n HELP - вывести информацию о командах;" +
                "\n EXIT - для выхода из просмотра.");
    }

}
