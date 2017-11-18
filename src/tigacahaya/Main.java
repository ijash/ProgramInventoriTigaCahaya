/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import tigacahaya.gui.GUI;

/**
 *
 * @author ijash
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MySQLconn.connect();
        GUI.guiStart();
        MySQLconn.get();
   
    }
}
