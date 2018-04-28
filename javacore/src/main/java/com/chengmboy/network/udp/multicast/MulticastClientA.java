package com.chengmboy.network.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * @author cheng_mboy
 */
public class MulticastClientA {

    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket();

        InetAddress group = InetAddress.getByName("224.5.6.7");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入组播消息: ");
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            byte[] b = msg.getBytes();
            DatagramPacket data = new DatagramPacket(b, b.length, group, 3000);
            socket.send(data);
        }
    }
}
