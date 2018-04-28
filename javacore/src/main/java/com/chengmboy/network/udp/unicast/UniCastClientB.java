package com.chengmboy.network.udp.unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author cheng_mboy
 */
public class UniCastClientB {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(3001);

        System.out.println("监听3001端口UDP数据包...");

        while (true) {
            DatagramPacket data = new DatagramPacket(new byte[1024], 1024);
            socket.receive(data);
            String msg = new String(data.getData());
            System.out.println(msg);
        }
    }
}
