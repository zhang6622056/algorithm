package loadbalance.impl;

import loadbalance.LoadBalance;
import loadbalance.ServerHost;

import java.util.List;
import java.util.Random;



/***
 *
 * 随机权重算法
 * 权重情况-  将权重相加，思想为将权重分段，然后对权重值随机，将随机值采用减法来确定随机区间，为负数则为命中区间
 * 非权重情况-  对list下标随机，随机取出一个即可
 * @author Nero
 * @date 2019-11-14
 * *@param: null
 * @return 
 */
public class RandomLoadBalance implements LoadBalance {


    @Override
    public ServerHost select(List<ServerHost> list) {
        Random random = new Random();


        //- 是否不考虑权重
        boolean sameWeight = true;

        //- 依次对比判定是否是相同的权重值
        int lastWeight = list.get(0).getWeight();

        //- 权重之和
        int weightSum = 0;

        for (ServerHost serverHost : list){
            weightSum += serverHost.getWeight();

            if (serverHost.getWeight() != lastWeight){
                sameWeight = false;
            }
        }

        //- 考虑权重的算法
        if (!sameWeight){
            System.out.println("采用随机---权重算法.....");
            int randomServerWeight = random.nextInt(weightSum);

            for (ServerHost serverHost : list){
                randomServerWeight = randomServerWeight - serverHost.getWeight();
                if (randomServerWeight < 0){
                    return serverHost;
                }
            }
        }



        System.out.println("采用随机---非权重算法.....");
        return list.get(random.nextInt(list.size()));
    }
}
