import java.util.*;

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
        // Write your code here
        Map<Schedule, Integer> scheduleMap = new HashMap<>();
        for(int i = 0; i < firstDay.size(); i++){
            int startDay = firstDay.get(i);
            int endDay = lastDay.get(i);
            boolean success= false;
            /*if(startDay == endDay){
                Schedule schedule = new Schedule(startDay, endDay);
                scheduleMap.put(schedule, i);
            }else{*/
            for(; startDay <= endDay; startDay++){
                Schedule schedule = new Schedule(startDay, startDay/*, i*/);
                if(scheduleMap.get(schedule) == null) {
                    scheduleMap.put(schedule, i);
                    break;
                }else{
                    /*if(scheduleMap.get(schedule) == i){
                        continue;
                    }else{
                        scheduleMap.put(schedule, i);
                    }*/
                }
                success = true;
               // break;
            }
            if(/*startDay != endDay &&*/ !success){
                Schedule schedule = new Schedule(startDay, startDay/*, i*/);
                scheduleMap.put(schedule, i);
            }
        }
        //int result = scheduleMap.values().stream().collect()
        Collection<Integer> investors = scheduleMap.values();
        Set<Integer> resultSet = new HashSet<>();
        resultSet.addAll(investors);
        //return scheduleMap.keySet().size();
        return resultSet.size();
    }

    static class Schedule{
        private int firstDay;
        private int lastDay;
        private int investorId;

        public Schedule(int firstDay, int lastDay/*, int investorId*/) {
            this.firstDay = firstDay;
            this.lastDay = lastDay;
            //this.investorId = investorId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Schedule schedule = (Schedule) o;
            return firstDay == schedule.firstDay &&
                    lastDay == schedule.lastDay &&
                    investorId == schedule.investorId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstDay, lastDay, investorId);
        }

        @Override
        public String toString() {
            return "Schedule{" +
                    "firstDay=" + firstDay +
                    ", lastDay=" + lastDay +
                    ", investorId=" + investorId +
                    '}';
        }
    }

    public static void main(String[] args){
        /*List<Integer> firstDay = Arrays.asList(1, 2, 3, 3, 3);
        List<Integer> lastDay = Arrays.asList(2, 2, 3, 4, 4);*/
        /*List<Integer> firstDay = Arrays.asList(1, 1, 2);
        List<Integer> lastDay = Arrays.asList(1, 2, 2);*/
        List<Integer> firstDay = Arrays.asList(1, 10, 11);
        List<Integer> lastDay = Arrays.asList(11, 10, 11);
        int result = countMeetings(firstDay, lastDay);
        System.out.println(result);
    }
}
