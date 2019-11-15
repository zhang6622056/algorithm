package loadbalance.impl;

import loadbalance.InvocationClient;
import loadbalance.LoadBalance;
import loadbalance.ServerHost;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;




/***
 *
 * 采用TreeMap 实现hash一致性模型，很好的思路。顺时针采用tail api 计算
 * @author Nero
 * @date 2019-11-15
 * *@param: null
 * @return 
 */
public class HashLoadBalance implements LoadBalance {

    private static final SortedMap<Integer,ServerHost> sortedMap = new TreeMap<>();



    @Override
    public ServerHost select(List<ServerHost> list, InvocationClient invocationClient) {
        //- 初始化hash环
        if (sortedMap.size() == 0){ initConsistenHash(list);  }


        //- 由于是顺时针，所以找到树节点的第一个右侧节点
        SortedMap<Integer,ServerHost> tailMap = sortedMap.tailMap(invocationClient.getRequestId().hashCode());

        //- 为空情况指的是命中时钟23点->00点之间的区域，默认返回00点
        if (tailMap.isEmpty()){ return sortedMap.get(sortedMap.firstKey()); }

        return tailMap.get(tailMap.firstKey());
    }


    /***
     * 初始化hash环,每个节点有160个虚拟节点
     * @author Nero
     * @date 2019-11-15
     * *@param: list
     * @return void
     */
    private static final void initConsistenHash(List<ServerHost> list){
        for (ServerHost serverHost : list){
            //- 每一个服务160个节点
            for (int i = 0 ; i < 160 ; i++){
                String hashKey = serverHost.getHost()+"VIRTUAL"+i;
                sortedMap.put(hashKey.hashCode(),serverHost);
            }
        }
    }

}
