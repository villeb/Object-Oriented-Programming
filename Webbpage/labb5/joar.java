/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb5;

/**
 *
 * @author Vilhelm
 */
 public class joar{
    private static joar theInstance = null; private joar(){}
        public static joar getInstance(){
            if(theInstance == null){
                theInstance = new joar();
            }
            return theInstance;
        }
}