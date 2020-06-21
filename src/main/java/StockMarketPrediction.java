import java.io.*;
import java.math.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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

        for(Integer query : queries){
            int stockDataValue = stockData.get(query-1);

            StockDataMapper optionalPre = data.subList(0, query -1).stream().sorted(Comparator.comparing(StockDataMapper::getDay).reversed())
                    .filter(data1 -> data1.getData() < stockDataValue)
                    //.collect(toList());
                    //.findFirst().get().day
                    .findFirst().orElseGet(()-> {return new StockDataMapper(0, -1);});

            //List<Integer> subDataPre =  stockData.subList(0, query -1);
            StockDataMapper optionalPost = data.subList(query, stockData.size()).stream()
                    .filter(data1 -> data1.getData() < stockDataValue)
                    .findFirst().orElseGet(()->{ return new StockDataMapper(0, -1);});
            //List<Integer> subDataPost = stockData.subList(query, stockData.size());
            //Integer preIndex= new Integer(0);



            /*if(subDataPre.size() > 0){
                preIndex = subDataPre.get(subDataPre.size()-1).getDay();
            }
            if(subDataPost.size() > 0){
                postIndex = subDataPost.get(0).getDay();
            }*/
            Integer preIndex = optionalPre.day;
            Integer postIndex = optionalPost.day;
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

        //System.out.println(predictedAnswer);

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

        List<Integer> stockData = Arrays.asList(5, 6, 8, 4, 9, 10, 8, 3,6, 4);
        List<Integer> queries =  Arrays.asList(3, 1, 8);
        //File f = new File("/home/amol/idea-IU-201.6668.121/workspace/HackerrankSolutions/src/main/resources//StockMarketPredictionData.txt");
        //List<String> resultData=Files.readAllLines(f.toPath());
        //List<Integer> stockData =Arrays.asList(89214, 26671, 75144, 32445, 13656, 66289, 21951, 10265, 59857, 59133, 63227, 86121, 37411, 54628, 25859, 43510, 63756, 54763, 30852, 53243, 76238, 96885, 33074, 17745, 81814, 43436, 79172, 92819, 30001, 68442, 54021, 35566, 95113, 29164, 84362, 25120, 11804, 6313, 51736, 71661, 81797, 14962, 57781, 35560, 85941, 99991, 95421, 66048, 54754, 26272, 35642, 47343, 39508, 85068, 65087, 21321, 28503, 60611, 30491, 58503, 29052, 84512, 94069, 40516, 13675, 78430, 65635, 25479, 1094, 17370, 13491, 99243, 48683, 71271, 34802, 34624, 87613, 46574, 671, 42366, 89197, 36313, 89708, 28704, 21380, 54795, 66376, 49882, 15405, 96867, 24737, 60808, 81378, 35157, 1324, 11404, 29938, 66958, 53234, 47384);
        //List<Integer> stockData = resultData.stream().map(x -> Integer.parseInt(x)).collect(toList()).subList(1, Integer.parseInt(resultData.get(0))+1);
        //List<Integer> queries = resultData.stream().map((x -> Integer.parseInt(x))).collect(toList()).subList(Integer.parseInt(resultData.get(0))+2, resultData.size());
        long start = new Date().getTime();

        List<Integer> result = predictAnswer(stockData, queries);
        long end = new Date().getTime();

        result.forEach(System.out::println);
        System.out.println("Total time = "+(end-start));


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
