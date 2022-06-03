package com.jf.moonson.starter.es;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author LiuGong
 * @version $
 * @since 2019年11月28日 21:24
 */
@ConfigurationProperties(prefix = "es")
public class EsRestClientProperties {
    /** 字符串list配置链接节点，格式ip:port形式 */
    private List<String> hosts;
    /** 连接超时，默认：5000ms */
    private int connectTimeout = 20000;
    /** 接口超时，默认：6000ms */
    private int socketTimeout = 20000;
    /** 连接最大重试时间，默认：60000ms */
    private int maxRetryTimeoutMillis = 20000;
    /** 登录用户名 */
    private String username = "";
    /** 登录用户密码 */
    private String password = "";

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }

    public void setMaxRetryTimeoutMillis(int maxRetryTimeoutMillis) {
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
