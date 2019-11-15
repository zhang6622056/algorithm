package loadbalance.impl;

import loadbalance.InvocationClient;
import loadbalance.LoadBalance;
import loadbalance.ServerHost;

import java.util.*;


/***
 *
 * 负载均衡最小使用
 * -首先判定是否有最小使用的一个host
 * -按照权重从高到低
 * -random
 *
 *
 *
 * @author Nero
 * @date 2019-11-15
 * *@param: null
 * @return 
 */
public class LeastUseLoadBalance implements LoadBalance {

    //- 记录每个host的使用分配次数
    public static final Map<ServerHost,Long> useTimeCount = new HashMap<>();


    @Override
    public ServerHost select(List<ServerHost> list, InvocationClient invocationClient) {
        //- 防止server变更，每次动态维护该usecount
        for (ServerHost serverHost : list){
            if (useTimeCount.get(serverHost) == null){
                useTimeCount.put(serverHost,0L);
            }
        }

        //- 查找当前使用下标，是否存在最小数为1的，如果有，直接返回
        Iterator<ServerHost> iterator = useTimeCount.keySet().iterator();
        List<ServerHost> leastUseTimeServer = new ArrayList<>();
        while(iterator.hasNext()){
            if (leastUseTimeServer.size() == 0){    //- 初始化第一个下标
                leastUseTimeServer.add(iterator.next());
            }else{
                //- 比较，碰到小的次数，则直接赋值为最新，如果有多个，则add进去
                ServerHost currCompare = iterator.next();
                for (ServerHost serverHost : leastUseTimeServer){

                    if (useTimeCount.get(currCompare) < useTimeCount.get(serverHost)){
                        leastUseTimeServer = new ArrayList<>();
                        leastUseTimeServer.add(currCompare);
                        break;
                    }else if (useTimeCount.get(currCompare).equals(useTimeCount.get(serverHost))){
                        leastUseTimeServer.add(currCompare);
                        break;
                    }
                }
            }
        }

        if(leastUseTimeServer.size() == 1) {
            addUseCount(leastUseTimeServer.get(0));
            return leastUseTimeServer.get(0);
        }



        //- 按照权重分配
        List<ServerHost> maxWeightList = new ArrayList<>();
        for(ServerHost serverHost : leastUseTimeServer){
            if (maxWeightList.size() == 0){
                maxWeightList.add(serverHost);
            }else{
               for (ServerHost serverHostWeight : maxWeightList){
                   if (serverHost.getWeight() > serverHostWeight.getWeight()){
                       maxWeightList = new ArrayList<>();
                       maxWeightList.add(serverHost);
                       break;
                   }else if (serverHost.getWeight() == serverHostWeight.getWeight()){
                       maxWeightList.add(serverHost);
                       break;
                   }
               }
            }
        }
        if (maxWeightList.size() == 1) {
            addUseCount(maxWeightList.get(0));
            return maxWeightList.get(0);
        }



        //- 权重有相同的，直接随机
        Random random = new Random();
        ServerHost serverHost = maxWeightList.get(random.nextInt(maxWeightList.size()));
        addUseCount(serverHost);
        return serverHost;
    }



    /**
     *
     * 增加累计使用次数
     * @author Nero
     * @date 2019-11-15
     * *@param: serverHost
     * @return void
     */
    private static void addUseCount(ServerHost serverHost){
        useTimeCount.put(serverHost,useTimeCount.get(serverHost)+1);
    }







}
