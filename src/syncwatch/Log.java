/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncwatch;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author leona
 */
public class Log {
    PrintStream fileOut;
    public Log (String file) throws Exception {
        fileOut = new PrintStream(new FileOutputStream(file));
    }
    protected void print(String msg) {
        System.out.println(msg);
        fileOut.println(msg);
    }
}
