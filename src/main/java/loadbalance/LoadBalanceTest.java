package loadbalance;

import loadbalance.impl.RandomLoadBalance;
import loadbalance.impl.RoundRobinLoadBalance;

import java.util.ArrayList;
import java.util.List;

public class LoadBalanceTest {

    private static final List<ServerHost> list = new ArrayList<>();
    static{
        list.add(new ServerHost("192.168.1.1:1000","192.168.1.1",1000,2));
        list.add(new ServerHost("192.168.1.2:1000","192.168.1.2",1000,2));
        list.add(new ServerHost("192.168.1.3:1000","192.168.1.3",1000,3));
        list.add(new ServerHost("192.168.1.4:1000","192.168.1.4",1000,3));
    }




    public static void main(String[] args) {
        //- 随机权重算法
//        LoadBalance loadBalance = new RandomLoadBalance();
//        for (int i = 0 ; i < 10 ; i++){
//            System.out.println(loadBalance.select(list).getId());
//        }

        //- 轮询权重算法
//        LoadBalance loadBalance = new RoundRobinLoadBalance();
//        for (int i = 0 ; i < 10 ; i++){
//            System.out.println(loadBalance.select(list).getId());
//        }


        //- 最少使用算法



        //- hash算法











    }



}
