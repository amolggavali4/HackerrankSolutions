import java.util.*;
import java.util.stream.Collectors;

public class MeetupSchedule {
    /*
     * Complete the 'countMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY firstDay
     *  2. INTEGER_ARRAY lastDay
     */
    public static int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
        int n = (int)Math.pow(10, 5);
        System.out.println("limit is "+n);
        System.out.println("Total investors "+firstDay.size());
        int i = n-1;
        if(firstDay.size() > n || lastDay.size() > n){
            return 0;
        }
        /*ListIterator<Integer> fdIterator = firstDay.listIterator();
        ListIterator<Integer> ldIterator = lastDay.listIterator();
        while(fdIterator.hasNext() && ldIterator.hasNext()){
            int fd = fdIterator.next();
            int ld = ldIterator.next();
            if(fd > n){
                fdIterator.set(n);
                System.out.println("Broke constraint "+fd+" index "+(fdIterator.nextIndex()-1));
            }
            if(ld > n){
                ldIterator.set(n);
                System.out.println("Broke constraint "+ld+" index "+(ldIterator.nextIndex()-1));
            }
        }*/

        Map<Schedule, Integer> scheduleMap = new HashMap<>();
        List<Schedule> schedules = new ArrayList<>(firstDay.size());
        for(int x = 0; x < firstDay.size(); x++){
            int startDay = firstDay.get(x);
            int endDay = lastDay.get(x);
            //boolean success= false;
            schedules.add(new Schedule(startDay, endDay));
        }
        List<Schedule> finalSchedule = schedules.stream()
                .sorted((Comparator.comparingInt(Schedule::getFirstDay)
                        .thenComparingInt(Schedule::getLastDay))).collect(Collectors.toList());

        System.out.println("finalSchedule size "+finalSchedule.size());
        //Adding investors which are available only for a day.
        ListIterator<Schedule> fdIterator = schedules.listIterator();
        //ListIterator<Integer> ldIterator = lastDay.listIterator();


        while(fdIterator.hasNext() /*&& ldIterator.hasNext()*/){
            //int fd = fdIterator.next();
            //int ld = ldIterator.next();
            Schedule s = fdIterator.next();
            if(s.getFirstDay() == s.getLastDay()){
                int index = 0;
                if(fdIterator.hasNext()){
                    index = fdIterator.nextIndex() -1;
                }else{
                    index = fdIterator.nextIndex();
                }
                scheduleMap.put(s, index);
                fdIterator.remove();
                //System.out.println("Broke constraint "+fd+" index "+(fdIterator.nextIndex()-1));
            }

        }
        System.out.println("Single day investor count "+ scheduleMap.keySet().size());
        //Adding all the investors to the map.
        for(Schedule s : schedules){
            int startDay = s.getFirstDay();
            int endDay = s.getLastDay();
            //boolean success = false;
            for(; startDay <= endDay /*&& startDay < n*/; startDay++){
                Schedule schedule = new Schedule(startDay, startDay/*, i*/);
                if(scheduleMap.get(schedule) == null) {
                    scheduleMap.put(schedule, startDay);
                    break;
                }else{
                    /*if(scheduleMap.get(schedule) == i){
                        continue;
                    }else{
                        scheduleMap.put(schedule, i);
                    }*/
                }
                //success = true;
                // break;
            }
            /*if(*//*startDay != endDay &&*//* !success){
                Schedule schedule = new Schedule(startDay, startDay*//*, i*//*);
                scheduleMap.put(schedule, i);
            }*/
        }

        //int result = scheduleMap.values().stream().collect()
        Collection<Integer> investors = scheduleMap.values();
        /*Set<Integer> resultSet = new HashSet<>();
        resultSet.addAll(investors);*/

        //return scheduleMap.keySet().size();
        return investors.size();
    }
    /*public static int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
        // Write your code here
        Map<Schedule, Integer> scheduleMap = new HashMap<>();
        for(int i = 0; i < firstDay.size(); i++){
            int startDay = firstDay.get(i);
            int endDay = lastDay.get(i);
            boolean success= false;
            *//*if(startDay == endDay){
                Schedule schedule = new Schedule(startDay, endDay);
                scheduleMap.put(schedule, i);
            }else{*//*
            for(; startDay <= endDay; startDay++){
                Schedule schedule = new Schedule(startDay, startDay*//*, i*//*);
                if(scheduleMap.get(schedule) == null) {
                    scheduleMap.put(schedule, i);
                    break;
                }else{
                    *//*if(scheduleMap.get(schedule) == i){
                        continue;
                    }else{
                        scheduleMap.put(schedule, i);
                    }*//*
                }
                success = true;
               // break;
            }
            if(*//*startDay != endDay &&*//* !success){
                Schedule schedule = new Schedule(startDay, startDay*//*, i*//*);
                scheduleMap.put(schedule, i);
            }
        }
        //int result = scheduleMap.values().stream().collect()
        Collection<Integer> investors = scheduleMap.values();
        Set<Integer> resultSet = new HashSet<>();
        resultSet.addAll(investors);
        //return scheduleMap.keySet().size();
        return resultSet.size();
    }*/

    static class Schedule{
        private int firstDay;
        private int lastDay;
        //private int investorId;

        public Schedule(int firstDay, int lastDay/*, int investorId*/) {
            this.firstDay = firstDay;
            this.lastDay = lastDay;
            //this.investorId = investorId;
        }

        public int getFirstDay() {
            return firstDay;
        }

        public void setFirstDay(int firstDay) {
            this.firstDay = firstDay;
        }

        public int getLastDay() {
            return lastDay;
        }

        public void setLastDay(int lastDay) {
            this.lastDay = lastDay;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Schedule schedule = (Schedule) o;
            return getFirstDay() == schedule.getFirstDay() &&
                    getLastDay() == schedule.getLastDay();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFirstDay(), getLastDay());
        }

        @Override
        public String toString() {
            return "Schedule{" +
                    "firstDay=" + firstDay +
                    ", lastDay=" + lastDay +
                    '}';
        }
    }

    public static void main(String[] args){
        /*List<Integer> firstDay = Arrays.asList(1, 2, 3, 3, 3);
        List<Integer> lastDay = Arrays.asList(2, 2, 3, 4, 4);*/
        List<Integer> firstDay = Arrays.asList(1, 2, 1, 2, 2);
        List<Integer> lastDay = Arrays.asList(3, 2 , 1, 3, 3);
        //List<Integer> firstDay = Arrays.asList(1, 10, 11);
        //List<Integer> lastDay = Arrays.asList(11, 10, 11);
        int result = countMeetings(firstDay, lastDay);
        System.out.println(result);
    }
}
