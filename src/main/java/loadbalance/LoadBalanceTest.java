package loadbalance;

import loadbalance.impl.LeastUseLoadBalance;
import loadbalance.impl.RoundRobinLoadBalance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoadBalanceTest {

    public static final List<ServerHost> list = new ArrayList<>();
    static{
        list.add(new ServerHost("192.168.1.1:1000","192.168.1.1",1000,2));
        list.add(new ServerHost("192.168.1.2:1000","192.168.1.2",1000,2));
        list.add(new ServerHost("192.168.1.3:1000","192.168.1.3",1000,5));
        list.add(new ServerHost("192.168.1.4:1000","192.168.1.4",1000,2));
    }




    public static void main(String[] args) {
        //- 随机权重算法
//        LoadBalance loadBalance = new RandomLoadBalance();
//        for (int i = 0 ; i < 10 ; i++){
//            System.out.println(loadBalance.select(list).getId(),null);
//        }

        //- 轮询权重算法
//        LoadBalance loadBalance = new RoundRobinLoadBalance();
//        for (int i = 0 ; i < 10 ; i++){
//            System.out.println(loadBalance.select(list,null).getId());
//        }


        //- 最少使用算法
//        for (int i = 0 ; i < 1001 ; i++){
//            LeastUseLoadBalance leastUseLoadBalance = new LeastUseLoadBalance();
//            leastUseLoadBalance.select(list,null);
//        }
//
//        Iterator<ServerHost> iterator = LeastUseLoadBalance.useTimeCount.keySet().iterator();
//        while(iterator.hasNext()){
//            ServerHost ser1 = iterator.next();
//            System.out.println(ser1.getId()+","+LeastUseLoadBalance.useTimeCount.get(ser1));
//        }

        //- hash算法
//        LoadBalance loadBalance = new HashLoadBalance();
//        for (int i = 0 ; i < Integer.MAX_VALUE ; i = i+16000){
//            ServerHost serverHost = loadBalance.select(list,new InvocationClient(String.valueOf(i)));
//            System.out.println(serverHost.getId());
//        }
    }



}
