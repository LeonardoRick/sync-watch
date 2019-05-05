/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 *
 * @author leona
 */
public class Breaker {
   String ip; 
   int port;

    public Breaker(String ipPort){
       this.ip = ipPort.split(":")[0];
       this.port = Integer.parseInt(ipPort.split(":")[1]);
    }
    public int init() throws Exception {
        System.out.println("Breaker");

        DatagramSocket clientSocket = new DatagramSocket(port);
        InetAddress IPAddress = InetAddress.getByName(ip);

        String toSend;

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        toSend = "-B";


        sendData = toSend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);     
        clientSocket.send(sendPacket);
        return 0;
    }
}
