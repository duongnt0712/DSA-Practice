package org.spp;

import lombok.Data;

import java.util.*;

@Data
public class Node {

    private String name;

    private List<Node> shortestPath = new ArrayList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
}