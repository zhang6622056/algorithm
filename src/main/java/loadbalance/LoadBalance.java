package loadbalance;

import java.util.List;

public interface LoadBalance {

    /***
     *
     * 负载均衡算法模型
     * @author Nero
     * @date 2019-11-14
     * *@param: list
     * @return loadbalance.ServerHost
     */
    ServerHost select(List<ServerHost> list,InvocationClient invocationClient);
}
