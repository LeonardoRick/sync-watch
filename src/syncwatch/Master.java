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
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master extends Thread {

    String time;
    String startClock;
    long wait;
    boolean watingToSend = false;
    
    int id = -1;
    ArrayList<Long> diffs = new ArrayList<>();
    ArrayList<Integer> ports = new ArrayList<>();
    ArrayList<InetAddress> ips = new ArrayList<>();
    
    public Master(String time){
        this.time = time;
        startClock = timeNow();
    }
   
    public void init() throws Exception {
        System.out.println("Server");
        System.out.println("Master time: " + time);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket serverSocket = new DatagramSocket(9876);
        
        while(true) {
            // receiving
             byte[] receiveData = new byte[1024];
             

             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             serverSocket.receive(receivePacket);
             String sentence = new String( receivePacket.getData());
             sentence = sentence.trim();
             
             System.out.println("\nreceiving");
             if(!sentence.equals("-B")){
                id += 1;
                System.out.println("id Packet: " + id);

                System.out.println("From Client: " + sentence);
                long diff = getDiff(time, sentence);
                 System.out.println("Difference of client and master: " + diff);
                if (diff < 20 && diff > -20) {
                    diffs.add(getDiff(time, sentence));

                } else {
                    diffs.add(getDiff(time, time));
                    System.out.println("Not Considered (diff > 20s)");
                }
                ports.add(receivePacket.getPort());
                ips.add(receivePacket.getAddress());                
             }
            
            // sending
            wait = getDiff(timeNow(), sumTime(startClock, formatTime(10))); // waiting 10s
            if(!watingToSend) {
                waitToSend(serverSocket);
            }
         }

     }
    private void waitToSend(DatagramSocket serverSocket) {
        new Thread() {
            @Override
            public void run() {
                watingToSend = true;
                long miliWait = wait * 1000;
                if(miliWait > 10000 || miliWait < 0){ //making sure to wait 10s
                    miliWait = 10000; 
                }
                try {
                    Thread.sleep(miliWait);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                try {
                    sendData(serverSocket);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                clearSending();
            }
        }.start();
    }
    private void sendData (DatagramSocket serverSocket) throws Exception {
        System.out.println("\nsending");
        System.out.println("id Packet finish: " + id);
        System.out.println("Old master time: " + time );

        byte[] sendData = new byte[1024];
        long media = calcMedia(diffs);
        String sMedia = formatTime(media);

        System.out.println("Time to adjust: " + sMedia);

        time = sumTime(time, sMedia); // correcting time of master
        time = sumTime(time, "00:00:10"); //correcting time from Thread.sleep(10000);

        String newTime = (String) time;
        System.out.println("New time of all watches time (considering passed 10s): " + time);

        for(int i = 0; i < ips.size(); i ++) {
            InetAddress IPAddress = ips.get(i);
            int port = ports.get(i);

            sendData = newTime.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);
        }
    }
    private void clearSending(){
        synchronized(Master.class) {
            startClock = timeNow();
            watingToSend = false;
            diffs.clear();
            ports.clear();
            ips.clear();
        }
    }

    private long getDiff(String time1, String time2) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        Date date = fmt.parse(time1);
        Date date2 = fmt.parse(time2);
        Duration diff = Duration.between(date.toInstant(), date2.toInstant());
        return diff.getSeconds();
    }

    private String sumTime(String time1, String time2) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        
        Date date = fmt.parse(time1);
        Date toSum = fmt.parse(time2);
        long sum = date.getTime() + toSum.getTime();
        Date sumDate = new Date(sum);
        String result = fmt.format(sumDate);

        return result;
    }

    
    private long calcMedia(ArrayList<Long> diffs){
        int total = 0;
        for(int i = 0; i < diffs.size(); i++) {
           total += diffs.get(i);
        }
        return total/(diffs.size() + 1);
    }
    
    private String formatTime(long seconds) { 
        return "00:00:" + seconds;
    }
    
    private String timeNow() {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String data = fmt.format(date);
        return data;        
    }
}