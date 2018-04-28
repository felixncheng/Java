package com.chengmboy.network.udp.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author cheng_mboy
 */
public class BroadCastClentB {


    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(3000);

        while (true) {
            DatagramPacket data = new DatagramPacket(new byte[1024], 1024);
            socket.receive(data);
            System.out.println(new String(data.getData()));
        }
    }
}
