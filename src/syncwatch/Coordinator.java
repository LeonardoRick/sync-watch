/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;

import java.util.Date;

/**
 *
 * @author 41607252
 */
public class Coordinator {
    
    public static void main(String[] args) throws Exception {
        
        String type = args[0];
        
        if (type.equals("-m")) {
            String time = args[1];
            Master master = new Master(time);
            try {
                master.init();
            } catch(Exception e) {
                return;
            }
        }
        else if (type.equals("-s")) {
            String ipPort = args[1];
            String time = args[2];
            try {
                Slave slave = new Slave(ipPort, time);
                slave.init();
            } catch(Exception e) {
                System.out.println(e.toString());
                return;
            }
        }//
        else {
            try {
                String ipPort = args[1];
                Breaker breaker = new Breaker(ipPort);
                breaker.init();
            } catch(Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        

     }
    
}
