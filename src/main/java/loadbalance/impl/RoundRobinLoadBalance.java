package loadbalance.impl;


import loadbalance.LoadBalance;
import loadbalance.ServerHost;

import java.util.List;

/***
 *
 * 轮训负载均衡算法
 *
 * - 需要考虑，如果运行中某个server宕机，则reset pos
 * - 权重情况：将权重相加，并分段。权重高的机器将有更多的空间段，中间变量也要以这个为上限
 * - 非权重情况：维护一个变量，每一次都+1，挨个访问。
 *
 * 难点： 确定一个客户端和一个服务端的唯一性很重要，这个唯一性，每一个维护的关系都要维护2个pos
 *
 * @author Nero
 * @date 2019-11-14
 * *@param: null
 * @return 
 */
public class RoundRobinLoadBalance implements LoadBalance {



    private int currentPos = 0;
    private int currentWeightPos = 0;




    @Override
    public ServerHost select(List<ServerHost> list) {
        //- 是否相同权重
        boolean sameWeight = true;

        //- 权重总和
        int sumWeight = 0;

        //- 维护变量lastCompareWeight 线性比较，是否相同
        int lastCompareWeight = list.get(0).getWeight();

        //- 权重数组，对应服务器列表，用来分段，轮训权重算法用到
        int[] weightArr = new int[list.size()];


        int index = 0;
        for (ServerHost serverHost : list){

            //-维护总和
            sumWeight += serverHost.getWeight();

            //-线性比较，是否相同
            if (serverHost.getWeight() != lastCompareWeight && sameWeight){
                sameWeight = false;
            }


            weightArr[index] = sumWeight;
            index++;
        }


        //- 如果是不同的权重，采用非同一权重的算法
        if (!sameWeight){
            if (currentWeightPos >= sumWeight){currentWeightPos = 0;}

            for (int i = 0 ; i < weightArr.length ; i++){
                //- 取权重阶段
                if (currentWeightPos - weightArr[i] < 0){
                    //-命中阶段则返回
                    ServerHost serverHost = list.get(i);
                    currentWeightPos++;
                    return serverHost;
                }
            }
        }



        //- 同一权重，防止下标益出，重置
        if (currentPos >= list.size()){ currentPos = 0;}
        ServerHost serverHost = list.get(currentPos);
        currentPos++;

        return serverHost;
    }







}
