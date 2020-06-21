import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BuSStand {
    /*
     * Complete the 'kthPerson' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY p
     *  3. INTEGER_ARRAY q
     */

    public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        System.out.println("Capacity "+k);
        for(int i = 0; i < q.size(); i++){
            int query = q.get(i);
            System.out.println("Query "+query);
            List<Integer> patient = p.stream().filter(x-> x >= query).collect(Collectors.toList());
            System.out.println("people in the queue"+ patient);
            if(patient.isEmpty()){
                result.add(0);
            }else{
                if(patient.size() >= k){
                    result.add(patient.get(k-1));
                }else{
                    result.add(0);
                }
            }
        }
    return result;
    }

    public static void main(String[] args){
        /*int capacity = 3;//k
        int numPeople = 3;//n
        List<Integer> p = Arrays.asList(2, 5, 3);
        List<Integer> q = Arrays.asList(1, 5);*/
        int capacity = 2;//k
        int numPeople = 3;//n
        List<Integer> p = Arrays.asList(1, 4, 4, 3, 1, 2, 6);
        List<Integer> q = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        List<Integer> kth = kthPerson(capacity, p, q);
        System.out.println(kth);

    }
}
