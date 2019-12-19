/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Xuan Truong PC
 */
import java.util.Vector;
public class ChessBroad {
    private Vector<Position> positions = new Vector<Position>();
    
    //Tạo đồ thị
    public Vector<Position> createGraph(String pos,String barriers){
        String keys [] = {"a","b","c","d","e","f","g","h"};
        Vector<Position> graph = new Vector<Position>();
        String [] bars = barriers.split(",");
        Vector<Position> darkLand = new Vector<Position>();
        Vector<Position> lightLand = new Vector<Position>();
        Position position = new Position();
        position.parsePoint(pos);
        position.setNeighbor(bars);
        graph.add(position);

        //tạo đồ thị
        int x = position.getTop_right().getX();
        int y = position.getTop_right().getY();
        int mod = (x+y)%2;
        for(int i=1;i<9;i++){
            for(int j = 1;j<9;j++){
                if((i+j)%2 == mod){
                    String name = keys[i-1]+j;
                    if(name.equals(pos)){
                        continue;
                    }
                    position = new Position();
                    position.parsePoint(name);
                    position.setNeighbor(bars);
                    graph.add(position);
                }
            }
        }
        return graph;
    }
    
    public Vector<Position> findMinPath(String startName,Vector<Position>graph){
        //Tìm điểm bắt đầu
        Position start = graph.get(0);
        start.setDistance(1);
        //Tìm đường đi ngắn nhất
        for(int i=0;i<graph.size();i++){
            if(graph.get(i).isVisited()==true){
                continue;
            }
            Vector<Position> neighbor = new Vector<Position>();
            Position cur = this.getObjectMinDistance(graph);
            if(cur == null){
                continue;
            }
            if(cur.getParent() == null & i!=0){
                return graph;
            }
            cur.setVisited(true);
            neighbor = cur.getNeighbor();
            for(int j = 0;j<neighbor.size();j++){
                if(isCheck(neighbor.get(j).getName(),graph)==true){
                    continue;
                }
                int distance=0;
                if(i==0){
                    distance = 1;
                    cur.setParent(cur);
                }else{
                    if(this.checkLine(cur.getParent(), cur, neighbor.get(j))==true){
                        distance = cur.getDistance();
                    }else{
                        distance = cur.getDistance()+1;
                    }
                }
                
                if(distance<neighbor.get(j).getDistance()){
                    //neighbor.get(j).setDistance(distance);
                    if(distance == cur.getDistance()){
                        this.setDistanceParent(neighbor.get(j).getName(), distance, cur.getParent(), graph);
                        //neighbor.get(j).setParent(cur.getParent());
                    }else{
                        this.setDistanceParent(neighbor.get(j).getName(), distance, cur, graph);
                        //neighbor.get(j).setParent(cur);
                    }
                }
            }
        }
        return graph;
    }
    
    public boolean isCheck(String name,Vector<Position> graph){
        for(int i=0;i<graph.size();i++){
            if(graph.get(i).getName().equals(name)){
                if(graph.get(i).isVisited() == true){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkLine(Position one,Position two,Position three){
        int nx = - (one.getTop_right().getX() - two.getTop_right().getX());
        int ny = one.getTop_right().getY() - two.getTop_right().getY();
        int c = 0 - one.getTop_right().getX()*nx - one.getTop_right().getY()*ny;
        int value = three.getTop_right().getX()*nx+three.getTop_right().getY()*ny + c;
        if(value != 0){
            return false;
        }
        return true;
    }
    
    public void setDistanceParent(String name,int distance,Position parent,Vector<Position> graph){
        for(int i=0;i<graph.size();i++){
            if(graph.get(i).getName().equals(name)){
                graph.get(i).setDistance(distance);
                graph.get(i).setParent(parent);
                return;
            }
        }
    }
    
    public Position getObjectMinDistance(Vector<Position> graph){
        int min = Integer.MAX_VALUE;
        Position result = null;
        for(int i = 0;i<graph.size();i++){
            if(graph.get(i).isVisited()==false){
                if(graph.get(i).getDistance()<min){
                    result = graph.get(i);
                    min = result.getDistance();
                }
            }
        }
        if(result == null){
            for(int i = 0;i<graph.size();i++){
                if(graph.get(i).isVisited()==false){
                    if(graph.get(i).getDistance()==min){
                        result = graph.get(i);
                    }
                }
            }
        }
        return result;
    }
    
    public void printResult(String startName,String endName,Vector<Position> graph){
        //Tìm kiểm kết thuc
        Position end = new Position();
        for(int i=0;i<graph.size();i++){
            if(graph.get(i).getName().equals(endName)){
                end = graph.get(i);
                break;
            }
        }
        //System.err.println(end.getParent().getName());
        if(end.getParent()==null){
            System.out.println("Khong co duong di");
            return;
        }
        //Vết tìm kiếm
        Vector<String> paths = new Vector<String>();
        paths.add(endName);
        String parentName = "";
        while(!end.getParent().getName().equals(startName)){
            if(!end.getParent().getName().equals(parentName)){
                parentName = end.getParent().getName();
                paths.add(parentName);
                end = end.getParent();
            }
        }
        paths.add(startName);
        System.out.println("Ket qua:");
        System.out.print(paths.get(paths.size()-1));
        for(int i = paths.size()-2;i>=0;i--){
            System.out.print(" => "+paths.get(i));
        }
        System.err.println("");
    }
}
