package loadbalance;



/**
 *
 * 服务器模型
 * @author Nero
 * @date 2019-11-14
 * *@param: null
 * @return 
 */
public class ServerHost {
    private String id;   //主机名加端口号
    private String host;    //-主机名
    private int port;   //端口号
    private int weight; //权重


    public ServerHost(String id, String host, int port, int weight) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
