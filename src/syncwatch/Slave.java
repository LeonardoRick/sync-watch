/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;


import java.net.*;
import java.text.ParseException;
import java.util.Scanner;

public class Slave extends Log {
   String ip; 
   int port;
   String time;

    /**
     *
     * @param ipPort
     * @param time
     * @param file
     * @throws ParseException
     */
    public Slave(String ipPort, String time, String file) throws Exception {
        super(file);
        this.ip = ipPort.split(":")[0];
        this.port = Integer.parseInt(ipPort.split(":")[1]);
        this.time = time;
    }
   
    public void init() throws Exception {
        print("Client");

        DatagramSocket clientSocket = new DatagramSocket(port);
        Scanner scanner = new Scanner(System.in);
        InetAddress IPAddress = InetAddress.getByName(ip);

        String sentence = "";
        String toSend;

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];


        print("My start time: " + time);

        while(true) {
            toSend = time;
            sendData = toSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);     
            clientSocket.send(sendPacket);
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            sentence = new String( receivePacket.getData());
            sentence = sentence.trim();
            time = sentence;
            print("My new time: " + time);
        }
    }
}