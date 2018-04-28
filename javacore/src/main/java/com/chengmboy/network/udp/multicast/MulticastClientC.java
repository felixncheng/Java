package com.chengmboy.network.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author cheng_mboy
 */
public class MulticastClientC {

    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket(3000);

        InetAddress group = InetAddress.getByName("224.5.6.7");
        socket.joinGroup(group);
        System.out.println("加入组播"+group.getHostAddress());
        while (true) {
            DatagramPacket data = new DatagramPacket(new byte[1024], 1024);
            socket.receive(data);
            System.out.println(new String(data.getData()));
        }
    }
}
