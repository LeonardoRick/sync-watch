/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;

public class Slave {
   String ip; 
   int port;
   String time;
    public Slave(String ipPort, String time) throws ParseException {
        this.ip = ipPort.split(":")[0];
        this.port = Integer.parseInt(ipPort.split(":")[1]);
        this.time = time;

    }
   
    public void init() throws Exception {
        System.out.println("Client");

        DatagramSocket clientSocket = new DatagramSocket(port);
        Scanner scanner = new Scanner(System.in);
        InetAddress IPAddress = InetAddress.getByName(ip);

        String sentence = "";
        String toSend;

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];


        System.out.println("My start time: " + time);

        toSend = time;
        sendData = toSend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);     
        clientSocket.send(sendPacket);
        while(true) { 
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            sentence = new String( receivePacket.getData());
            sentence = sentence.trim();
            time = sentence;
            System.out.println("My new time: " + time);
        }
    }
}