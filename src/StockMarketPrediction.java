import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StockMarketPrediction {
    /*
     * Complete the 'predictAnswer' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stockData
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> predictedAnswer = new ArrayList<>(queries.size());
        List<StockDataMapper> data = IntStream.range(0, stockData.size())
                .mapToObj(index-> new StockDataMapper(index+1, stockData.get(index))).collect(Collectors.toList());
        //data.forEach(System.out::println);
        for(int query : queries){
            int stockDataValue = stockData.get(query-1);
            //System.out.println("**************************************");
            //System.out.println(" Query day is "+ query);

            List<StockDataMapper> subDataPre = data.subList(0, query -1).stream()
                    //stream().filter(data1 -> data1.day < query)
                    .filter(data1 -> data1.getData() < stockDataValue)
                    //.sorted(Comparator.comparing(StockDataMapper::getData))//.thenComparing(StockDataMapper::getDay))
                    .collect(toList());
            //System.out.println("printing collected data");
            //subDataPre.forEach(System.out::println);
            List<StockDataMapper> subDataPost = data.subList(query, stockData.size() -1).stream()//.filter(data1 -> data1.day > query)
                    .filter(data1 -> data1.getData() < stockDataValue)
                    //.sorted(Comparator.comparing(StockDataMapper::getData).thenComparing(StockDataMapper::getDay))
                    .collect(toList());
            int preIndex= 0;
            int postIndex = 0;
            if(subDataPre.size() > 0){
                //predictedAnswer.add(subDataPre.get(subDataPre.size()-1).getDay());
                preIndex = subDataPre.get(subDataPre.size()-1).getDay();
            }
            if(subDataPost.size() > 0){
                //predictedAnswer.add(subDataPre.get(subDataPre.size()-1).getDay());
                postIndex = subDataPost.get(0).getDay();
            }
            if(preIndex == 0 && postIndex == 0){
                predictedAnswer.add(-1);
            }else if(preIndex == 0 && postIndex != 0){
                predictedAnswer.add(postIndex);
            }else if(preIndex != 0 && postIndex == 0){
                predictedAnswer.add(preIndex);
            }else if(query - preIndex <= postIndex- query){
                predictedAnswer.add(preIndex);
            }else if (query - preIndex > postIndex- query) {
                predictedAnswer.add(postIndex);
            }
        }

        return predictedAnswer;
    }

    static class StockDataMapper{
        private int day;
        private int data;

        public StockDataMapper(int day, int data) {
            this.day = day;
            this.data = data;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "StockDataMapper{" +
                    "day=" + day +
                    ", data=" + data +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int stockDataCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> stockData = IntStream.range(0, stockDataCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());*/
        List<Integer> queries =  Arrays.asList(50, 80, 24, 26, 62, 46, 79, 85, 59, 52, 8, 76, 48, 72, 84, 3, 3, 30, 30, 36, 86, 96, 72, 93, 25, 28, 68, 81, 18, 78, 14, 1, 57, 90, 26, 18, 87, 56, 55, 97, 59, 62, 73, 58, 85, 8, 60, 87, 89, 89, 22);
        List<Integer> stockData =Arrays.asList(89214, 26671, 75144, 32445, 13656, 66289, 21951, 10265, 59857, 59133, 63227, 86121, 37411, 54628, 25859, 43510, 63756, 54763, 30852, 53243, 76238, 96885, 33074, 17745, 81814, 43436, 79172, 92819, 30001, 68442, 54021, 35566, 95113, 29164, 84362, 25120, 11804, 6313, 51736, 71661, 81797, 14962, 57781, 35560, 85941, 99991, 95421, 66048, 54754, 26272, 35642, 47343, 39508, 85068, 65087, 21321, 28503, 60611, 30491, 58503, 29052, 84512, 94069, 40516, 13675, 78430, 65635, 25479, 1094, 17370, 13491, 99243, 48683, 71271, 34802, 34624, 87613, 46574, 671, 42366, 89197, 36313, 89708, 28704, 21380, 54795, 66376, 49882, 15405, 96867, 24737, 60808, 81378, 35157, 1324, 11404, 29938, 66958, 53234, 47384);
        List<Integer> result = predictAnswer(stockData, queries);
        result.forEach(System.out::println);
       /* bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
