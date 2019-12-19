/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Xuan Truong PC
 */
import models.*;
public class ExecuteFirstExam {
    String oldPos = "";
    String newPos = "";
    public ExecuteFirstExam(String oldPos,String newPos){
        this.oldPos = oldPos;
        this.newPos = newPos;
    }
    
    public boolean check(){
        Position newPosition = new Position();
        Position oldPosition = new Position();
        if(oldPosition.parsePoint(oldPos)==false){
            return false;
        }
        if(newPosition.parsePoint(newPos)==false){
            return false;
        }
        if(newPosition.checkPosition(oldPosition.getLine())==true){
            System.out.println("Vi tri moi hop le");
        }else{
            System.err.println("VI tri moi khong hop le");
        }
        return true;
    }
}
