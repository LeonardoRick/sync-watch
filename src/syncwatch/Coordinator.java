/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;

/**
 *
 * @author 41607252
 */
public class Coordinator {
    
    public static void main(String[] args) throws Exception {
        
        String type = args[0];
        
        if (type.equals("-m")) { //instance of master
            String time = args[1];
            String file = args[2];
            Master master = new Master(time, file);
            try {
                master.init();
            } catch(Exception e) {
                return;
            }
        }
        else if (type.equals("-s")) { //instance of slave
            String ipPort = args[1];
            String time = args[2];
            String file = args[3];
            try {
                Slave slave = new Slave(ipPort, time, file);
                slave.init();
            } catch(Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
        else { //instance of breaker
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
