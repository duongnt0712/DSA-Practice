package org.spp;

import java.util.*;

public class Dijkstra {

    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public Route findShortestRoute(String sourceNode, String destinationNode) {

        Map<String, Integer> distances = new HashMap<>();
        Map<String, Vector> previousEdges = new HashMap<>();
        Set<String> unsettledNodes = new HashSet<>(graph.adjacentNodes().keySet());

        for (String node : graph.adjacentNodes().keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(sourceNode, 0);

        while (!unsettledNodes.isEmpty()) {
            String currentNode = getClosestNode(unsettledNodes, distances);
            if (currentNode == null || distances.get(currentNode) == Integer.MAX_VALUE || currentNode.equals(destinationNode)) {
                break;
            }
            unsettledNodes.remove(currentNode);

            List<Vector> neighbors = graph.adjacentNodes().getOrDefault(currentNode, List.of());
            for (Vector vector : neighbors) {
                calculateMinimumDistance(vector, unsettledNodes, distances, currentNode, previousEdges);
            }
        }

        return buildRoute(destinationNode, previousEdges, distances);
    }

    private void calculateMinimumDistance(Vector vector, Set<String> unsettledNodes, Map<String, Integer> distances, String currentNode, Map<String, Vector> previousEdges) {
        String evaluationNode = vector.destinationNode();
        if (!unsettledNodes.contains(evaluationNode)) return;

        int newDistance = distances.get(currentNode) + vector.distance();

        // Update distances to its neighbors if a shorter path is found.
        if (newDistance < distances.get(evaluationNode)) {
            distances.put(evaluationNode, newDistance);
            previousEdges.put(evaluationNode , vector);
        }
    }

    private Route buildRoute(String destinationNode, Map<String, Vector> previousEdges, Map<String, Integer> distances) {
        // Build the Route
        List<Vector> path = new ArrayList<>();
        String currentNode = destinationNode;

        while (previousEdges.containsKey(currentNode)) {
            Vector edge = previousEdges.get(currentNode);
            path.add(0, edge);
            currentNode = edge.sourceNode();
        }

        // Handle No Route
        if (distances.get(destinationNode) == Integer.MAX_VALUE) {
            return Route.builder().route(List.of()).totalDistance(-1).build();
        }

        return Route.builder().route(path).totalDistance(distances.get(destinationNode)).build();
    }

    // Finds the unvisited node with the smallest distance.
    private String getClosestNode(Set<String> unvisited, Map<String, Integer> distances) {
        String closestNode = null;
        int smallestDistance = Integer.MAX_VALUE;

        for (String node : unvisited) {
            int currentDistance = distances.getOrDefault(node, Integer.MAX_VALUE);
            if (currentDistance < smallestDistance) {
                smallestDistance = currentDistance;
                closestNode = node;
            }
        }
        return closestNode;
    }
}
