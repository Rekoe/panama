package org.leo.server.panama.vpn;

import org.leo.server.panama.server.Server;
import org.leo.server.panama.vpn.configuration.ShadowSocksConfiguration;
import org.leo.server.panama.vpn.constant.VPNConstant;
import org.leo.server.panama.vpn.reverse.server.ReverseShadowSocksServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 反向代理ss，部署在内网服务器，配合OuterReverseShadowSocks使用
 * @author xuyangze
 * @date 2018/10/9 下午1:16
 */
public class InnerReverseShadowSocks {
    public static void main(String []args) throws IOException {
        ShadowSocksConfiguration.setType("aes-256-cfb");
        ShadowSocksConfiguration.setPassword("1234567890");
        ShadowSocksConfiguration.setReverseHost("127.0.0.1");
        ShadowSocksConfiguration.setReversePort(8080);

        Server server = new ReverseShadowSocksServer(ShadowSocksConfiguration.getReverseHost(), ShadowSocksConfiguration.getReversePort());
        System.out.println(server.getClass().getSimpleName() + " start");
        server.start(VPNConstant.MAX_SERVER_THREAD_COUNT);
    }

    private static String read(String message) throws IOException {
        System.out.print(message + ":");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
