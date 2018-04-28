package com.chengmboy.network.udp.unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author cheng_mboy
 */
public class UniCastClientA {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(3000);
        InetAddress host = InetAddress.getByName("localhost");

        System.out.println("发送数据报到3001端口");
        System.out.println("请输入消息：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            byte[] b = msg.getBytes();
            DatagramPacket request = new DatagramPacket(b, b.length, host, 3001);
            socket.send(request);
        }
    }
}
