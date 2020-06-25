import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> result = new ArrayList<>(q.size());

        for(int query : q){
            List<Integer> results= IntStream.range(0, p.size()).parallel()
                    .filter(index -> p.get(index) >= query)
                    .limit(k)
                    .mapToObj(index -> index+1)
                    .collect(Collectors.toList());
                    /*.mapToObj(index -> index)
                    .collect(Collectors.toList());*/
            if(results.size() < k){
                result.add(0);
            }else{
                result.add(results.get(k-1));
            }
            //System.out.println(results);
            //finalList.forEach(System.out::println);
            /*int index = 0;
            int capacity = 0;

                for(int x = 0; x < p.size() && capacity < k; x++){
                    index++;
                    if(p.get(x) >= query){
                        capacity++;
                    }
                }

            if(index == 0 || capacity < k){
                result.add(0);
            }else {
                result.add(index);
            }*/
        }
        return result;
    }

    public static void main(String[] args){
        //int capacity = 3;//k
        //int numPeople = 3;//n
        //List<Integer> p = Arrays.asList(2, 5, 3);
        //List<Integer> q = Arrays.asList(1, 5);
        int capacity = 2;//k
        int numPeople = 7;//n
        /*List<Integer> p = Arrays.asList(1, 4, 4, 3, 1, 2, 6);
        //Collections.reverse(p);
        List<Integer> q = Arrays.asList(1, 2, 3, 4, 5, 6, 7);*/
        List<Integer> p = Arrays.asList(1, 4, 4 ,3 , 1, 2, 6);
        //Collections.reverse(p);
        List<Integer> q = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> kth = kthPerson(capacity, p, q);
        System.out.println(kth);

    }
}
