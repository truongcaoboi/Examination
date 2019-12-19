/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Vector;

/**
 *
 * @author Xuan Truong PC
 */
public class Position {

    private Point top_left;
    private Point top_right;
    private Point bottom_left;
    private Point bottom_right;
    private Position parent = null;
    private int distance = Integer.MAX_VALUE;
    private String name="";
    private Vector<Position> neighbor = new Vector<Position>();
    private boolean visited = false;
    
    public void setVisited(boolean a){
        visited = a;
    }
    public boolean isVisited(){
        return visited;
    }

    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    

    //Xac dinh toa do 2 duong thang di qua diem dc chon
    public int[] getLine() {
        int line[] = new int[6];
        //toa do duong top_left bottom_rigt
        line[0] = -(top_left.getX() - bottom_right.getX());
        line[1] = top_left.getY() - bottom_right.getY();
        line[2] = 0 - top_left.getX() * line[0] - top_left.getY() * line[1];
        //toa do duong top_right bottom_left
        line[3] = -(top_right.getX() - bottom_left.getX());
        line[4] = (top_right.getY() - bottom_left.getY());
        line[5] = 0 - top_right.getX() * line[3] - top_right.getY() * line[4];
        return line;
    }

    //kiem tra vi tri cua quan co co hop le hay khong
    public boolean checkPosition(int[] line) {
        int value1 = top_left.getX() * line[0] + top_left.getY() * line[1] + line[2];
        int value2 = top_right.getX() * line[3] + top_right.getY() * line[4] + line[5];
        if (value1 != 0 & value2 != 0) {
            return false;
        }
        return true;
    }

    //chuyen doi vi tri tren ban co thanh toa do 4 goc cua quan co
    public boolean parsePoint(String pos) {
        String items[] = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String parts[] = pos.split("");
        int y = 0;
        try {
            y = Integer.parseInt(parts[1]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        int x = 0;
        int flag = 0;
        for (int i = 0; i < 8; i++) {
            if (parts[0].equals(items[i])) {
                x = i + 1;
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.err.println("Loi nhap lieu");
            return false;
        }
        top_left = new Point(x - 1, y);
        top_right = new Point(x, y);
        bottom_left = new Point(x - 1, y - 1);
        bottom_right = new Point(x, y - 1);
        this.name = pos;
        return true;
    }
    
    public Point getTop_left() {
        return top_left;
    }

    public Point getTop_right() {
        return top_right;
    }

    public Point getBottom_left() {
        return bottom_left;
    }

    public Point getBottom_right() {
        return bottom_right;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Vector<Position> getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String barries[]) {
        this.neighbor = new Vector<Position>();
        String keys [] = {"a","b","c","d","e","f","g","h"};
        int x = this.top_right.getX()-1;
        int y = this.top_right.getY();
        Position nei;
        //hàng xóm bên trái
        if(x > 0){
            //hàng xom bên trên
            if(y < 8){
                String name = keys[x-1]+(y+1);
                if(!checkBarries(barries, name)){
                    nei = new Position();
                    nei.parsePoint(name);
                    this.neighbor.add(nei);
                }
            }
            
            //hàng xóm bên dưới
            if(y>1){
                String name = keys[x-1]+(y-1);
                if(!checkBarries(barries, name)){
                    nei = new Position();
                    nei.parsePoint(name);
                    this.neighbor.add(nei);
                }
            }
        }
        //hàng xóm bên phải
        if(x<7){
            //hàng xóm bên trên
            if(y<8){
                String name = keys[x+1]+(y+1);
                if(!checkBarries(barries, name)){
                    nei = new Position();
                    nei.parsePoint(name);
                    this.neighbor.add(nei);
                }
            }
            //hàng xóm bên dưới
            if(y>1){
                String name = keys[x+1]+(y-1);
                if(!checkBarries(barries, name)){
                    nei = new Position();
                    nei.parsePoint(name);
                    this.neighbor.add(nei);
                }
            }
        }
    }
    //kiểm tra xem pos có phải là điểm chặn không
    public boolean checkBarries(String barries[],String pos){
        for(int i = 0;i<barries.length;i++){
            if(pos.equals(barries[i])){
                return true;
            }
        }
        return false;
    }
}
