/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examination;

/**
 *
 * @author Xuan Truong PC
 */
import models.*;
import controllers.*;
import java.util.Scanner;
public class Examination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Examination e = new Examination();
//        while(1==1){
//            e.Exam1(input);
//        }
        e.Exam1_2(input);
    }
    
    public void Exam1(Scanner input){
        System.out.println("Nhap vi tri ban dau cua quan tuong:");
        String oldPos = input.nextLine();
        System.out.println("Nhap vi tri ban dau cua quan tuong:");
        String newPos = input.nextLine();
        ExecuteFirstExam exam1 = new ExecuteFirstExam(oldPos, newPos);
        exam1.check();
    }
    public void Exam1_2(Scanner input){
        System.out.println("Nhap dau vao:");
        String inputString = input.nextLine();
//        System.out.println("Nhap vi tri ban dau cua quan tuong:");
//        String newPos = input.nextLine();
        String oldPos = inputString.split("=>")[0];
        String newPos = inputString.split("=>")[1];
        System.out.println("Nhap vi tri can cua quan tuong:");
        String barries = input.nextLine();
        ChessBroad cb = new ChessBroad();
        cb.printResult(oldPos, newPos, cb.findMinPath(oldPos, cb.createGraph(oldPos, barries)));
    }
}
