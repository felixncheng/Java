package com.chengmboy.network.udp.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author cheng_mboy
 */
public class BroadCastClentA {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入广播消息：");
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            byte[] b = msg.getBytes();
            DatagramPacket data = new DatagramPacket(b, b.length, InetAddress.getByName("255.255.255.255"),3000);
            socket.send(data);
        }
    }
}
