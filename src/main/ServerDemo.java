/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jai
 */
public class ServerDemo {
    public static void main(String[] args) {
        try {
            Server s = new Server();
            s.start();
        } catch (Exception e) {
        }
    }
}
