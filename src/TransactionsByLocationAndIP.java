import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TransactionsByLocationAndIP {
    /*
     * Complete the 'getTransactions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER userId
     *  2. INTEGER locationId
     *  3. INTEGER netStart
     *  4. INTEGER netEnd
     *
     *  https://jsonmock.hackerrank.com/api/transactions/search?userId=
     */

    public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {
        int amount = 0;
        double finalAmount =0;

        String jsonData = getJsonData(userId, 1);
        Map jsonMap = parseJsonObject(jsonData);
        int totalPages = (Integer) jsonMap.get("total_pages");
        int total = (Integer) jsonMap.get("total");
        List data =(List) jsonMap.get("data");

        finalAmount = finalAmount + finalAmount(data, userId,locationId, netStart, netEnd);

        for(int pageNum = 2; pageNum <= totalPages; pageNum++){
            //System.out.println("----------------Page "+ pageNum + "----------------");
            jsonData = getJsonData(userId, pageNum);
            jsonMap = parseJsonObject(jsonData);
            data =(List) jsonMap.get("data");
            finalAmount =  finalAmount +  finalAmount(data, userId, locationId, netStart, netEnd);
        }
        //System.out.println("Final Amount -- "+ finalAmount);
        //System.out.println(Double.valueOf(finalAmount).intValue());
        BigDecimal bd = new BigDecimal(finalAmount);
        //System.out.println(bd.setScale(0, RoundingMode.CEILING));
        //return Double.valueOf(finalAmount).intValue();
        return bd.setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private static double finalAmount(List data, int userId, int locationId, int netStart, int netEnd){
        double amount = 0;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        for(Object o : data) {
            String ip = ((Map) o).get("ip").toString();
            int dataUserId = Integer.parseInt(((Map) o).get("userId").toString());
            int ipRange = Integer.parseInt(ip.substring(0, ip.indexOf('.')));
            if (dataUserId == userId){
                if (ipRange >= netStart && ipRange <= netEnd) {
                    //System.out.println("ipRange "+ ipRange);
                    Map locationMap = (Map) ((Map) o).get("location");
                    //System.out.println("location id "+ locationMap.get("id"));
                    if (locationMap.get("id").toString().equals(locationId + "")) {
                        try {
                            amount += (numberFormat.parse(((Map) o).get("amount").toString())).doubleValue();
                            //System.out.println("Amount-> "+ amount);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return amount;
    }

    private static Map parseJsonObject(String jsonObject){

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        String script = "Java.asJSONCompatible(" + jsonObject + ")";
        Map jsonMap = null;
        try {
            jsonMap = (Map) scriptEngine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }

    private static String getJsonData(int userId, int pageNumber)  {
        try{
            URL url = new URL("https://jsonmock.hackerrank.com/api/transactions/search?userId=" + userId
                +"&page="+pageNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept","application/json");

            if(connection.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return br.readLine();
            }else{
                return null;
            }
        }catch(Exception ex){

        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int userId = Integer.parseInt(bufferedReader.readLine().trim());

        int locationId = Integer.parseInt(bufferedReader.readLine().trim());

        int netStart = Integer.parseInt(bufferedReader.readLine().trim());

        int netEnd = Integer.parseInt(bufferedReader.readLine().trim());*/

        int result = getTransactions(2, 8, 5, 50);

        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();*/

     //   bufferedReader.close();
        //bufferedWriter.close();
    }
}
