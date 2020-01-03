package lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;



/***
 *
 * 功能描述 
 * @author Nero
 * @date 2020-01-03
 * *@param: null
 * @return 
 */
public class LruByLinkedHashMap<K,V> {


    private final LinkedHashMap<K,V> dataStore;
    private final int total;




    public LruByLinkedHashMap(int quantity) throws Exception {
        if (quantity <= 0){
            throw new Exception("quantity must >= 1");
        }
        total = quantity;
        dataStore = new LinkedHashMap(quantity);
    }





    public synchronized void put(K k,V v) {
        //- 在容器内，放到首部
        if (dataStore.containsKey(k)) {
            dataStore.remove(k);
        } else {  //- 不在容器内，判定容量，put
            if (dataStore.size() == total) {
                dataStore.remove(getTail().getKey());
            }
        }
        dataStore.put(k, v);
    }




    //- 获取尾部entry
    private Map.Entry getTail(){
           Iterator<Map.Entry<K, V>> iterator = dataStore.entrySet().iterator();
           Map.Entry entry = null;
           return iterator.next();
    }






    public int size(){
        return dataStore.size();
    }









    public static void main(String[] args) throws Exception {
        LruByLinkedHashMap<String,String> lruByLinkedHashMap = new LruByLinkedHashMap<>(2);

        for (int i = 0 ; i < 10 ; i++){
            lruByLinkedHashMap.put(String.valueOf(i),"123");
        }

        System.out.println(lruByLinkedHashMap.size());
    }

}
