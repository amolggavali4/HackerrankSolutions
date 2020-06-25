import java.util.*;

public class MeetupScheduleNew {
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
        List<Schedule> scheduleList = new ArrayList<>();
        for(int i = 0; i< firstDay.size(); i++){
            scheduleList.add(new Schedule(firstDay.get(i), lastDay.get(i)));
        }
        scheduleList.sort(Comparator.comparing(Schedule::getFirstDay));
        Set<Schedule> scheduleSet = new HashSet<>();
        for(int i = 0; i < firstDay.size(); i++){
            int startDay = scheduleList.get(i).getFirstDay();
            int endDay = scheduleList.get(i).getLastDay();
            for(; startDay <= endDay; startDay++){
                Schedule schedule = new Schedule(startDay, startDay/*, i*/);
                scheduleSet.add(schedule);
            }
        }
        return scheduleSet.size();
    }

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
            return firstDay == schedule.firstDay &&
                    lastDay == schedule.lastDay ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstDay, lastDay);
        }

        @Override
        public String toString() {
            return "Schedule{" +
                    "firstDay=" + firstDay +
                    ", lastDay=" + lastDay+
                    '}';
        }
    }

    public static void main(String[] args){
        List<Integer> firstDay = Arrays.asList(1, 2, 3, 3, 3);
        List<Integer> lastDay = Arrays.asList(2, 2, 3, 4, 4);
        /*List<Integer> firstDay = Arrays.asList(1, 1, 2);
        List<Integer> lastDay = Arrays.asList(1, 2, 2);*/
        /*List<Integer> firstDay = Arrays.asList(1, 10, 11);
        List<Integer> lastDay = Arrays.asList(11, 10, 11);*/
        int result = countMeetings(firstDay, lastDay);
        System.out.println(result);
    }
}
