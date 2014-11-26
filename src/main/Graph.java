package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by laurieompraret on 26/11/2014.
 */
public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }


    public int getDistance(String from, String to) {
        int distance = -1;
        int ind = findIndex(from);

        if (ind != -1){
            Vertex vertexFrom = vertices.get(ind);
            for (Edge edge : vertexFrom.getEdges()) {
                if (edge.getTarget().getName().equals(to)) {
                    distance = edge.getDistance();
                }
            }
        }
        return distance ;

    }

    public int findIndex(String name) {
        boolean found = false ;
        int ind = 0 ;
        while (!found && ind < vertices.size()) {
            if (vertices.get(ind).getName().equals(name)) {
                found = true;
            }
            ind++;
        }

        if (found == false) {
            ind = 0;
        }

        return ind - 1;


    }

    public int getDistanceByOneVertexGiven(String from, String by, String to) {
        int distance1 = this.getDistance(from, by);
        int distance2 = this.getDistance(by, to);
        int distanceTot = -1;

        if ( distance1 != -1 && distance2 != -1) {
            distanceTot = distance1 + distance2;
        }

        return distanceTot;

    }

    public int getDistanceByOneVertex(String from, String to){
        int distance1 = -1;
        int distance2 = -1;
        int distance = -1;
        ArrayList<Integer> distances = new ArrayList<Integer>();
        for (Vertex by : vertices) {
            distance1 = getDistance(from, by.getName());
            distance2 = getDistance(by.getName(), to);
            if ( distance1 != -1 && distance2 != -1){
                distance = distance1 + distance2 ;
                distances.add(distance);

            }
        }

        if (distances.size() != 0) {
            distance = Collections.min(distances);
        }
        return distance ;


    }

    public String getBestVertexToGo(String from, String to){
        int distance1 = -1;
        int distance2 = -1;
        int distance = -1;


        ArrayList<Integer> distances = new ArrayList<Integer>();
        ArrayList<String> towns = new ArrayList<String>() ;
        for (Vertex by : vertices) {
            distance1 = getDistance(from, by.getName());
            distance2 = getDistance(by.getName(), to);
            if ( distance1 != -1 && distance2 != -1){
                distance = distance1 + distance2 ;
                distances.add(distance);
                towns.add(by.getName());

            }
        }
        distance = Collections.min(distances);
        int ind = distances.indexOf(distance);
        String town = towns.get(ind);
        return town ;

    }



    public int getDistanceBy2Vertex(String from, String to) {
        int distanceTot = -1;

        ArrayList<Integer> distances = new ArrayList<Integer>();
        int indice = findIndex(from);
        Vertex start = vertices.get(indice);
        for (Edge edge : start.getEdges()) {
            String by = edge.getTarget().getName();
            int d = getDistanceByOneVertex(by, to);
            if (d != -1) {
                int distance = d + getDistance(from, by);
                distances.add(distance);
            }
        }
        if (distances.size() != 0) {
            distanceTot = Collections.min(distances);
        }


        return distanceTot;
    }

    public int getDistanceByNVertex(String from, String to, int n) {
        int Distance = -1;
        if (n>=2){
            ArrayList<Integer> distances = new ArrayList<Integer>();
            int indice = findIndex(from);
            Vertex start = vertices.get(indice);
            for (Edge edge : start.getEdges()) {
                String by = edge.getTarget().getName();
                int d = getDistanceByNVertex(by, to, n-1);
                if (d != -1) {
                    int distance = d + getDistance(from, by);
                    distances.add(distance);
                }
            }
            if (distances.size() != 0) {
                Distance = Collections.min(distances);
            }
        }
        if (n==1){
            Distance= getDistanceByOneVertex(from, to);
        }
        if (n==0){
            getDistance(from, to);
        }
        return Distance;
    }

}

