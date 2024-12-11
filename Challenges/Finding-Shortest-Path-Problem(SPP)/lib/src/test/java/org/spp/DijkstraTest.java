package org.spp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    void testSPPSuccess() {
        // Given nodes and distance
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        nodeA.addDestination(nodeB, 1);
        nodeA.addDestination(nodeE, 5);
        nodeA.addDestination(nodeD, 3);

        nodeB.addDestination(nodeC, 6);
        nodeB.addDestination(nodeE, 12);

        nodeC.addDestination(nodeE, 8);
        nodeC.addDestination(nodeG, 9);
        nodeC.addDestination(nodeA, 12);

        nodeD.addDestination(nodeF, 3);

        nodeE.addDestination(nodeG, 11);

        nodeF.addDestination(nodeE, 8);
        nodeF.addDestination(nodeG, 9);
        nodeF.addDestination(nodeH, 5);

        nodeG.addDestination(nodeH, 8);

        nodeH.addDestination(nodeA, 15);
        nodeH.addDestination(nodeD, 10);
        nodeH.addDestination(nodeG, 4);
        nodeH.addDestination(nodeC, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);
        graph.addNode(nodeH);

        // When calculate the shortest path from node A
        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);

        // Then return the shortest path for each node
        Map<String, List<Node>> expectedShortestPaths = Map.of(
                "B", List.of(nodeA),
                "C", List.of(nodeA, nodeB),
                "D", List.of(nodeA),
                "E", List.of(nodeA),
                "F", List.of(nodeA, nodeD),
                "G", List.of(nodeA, nodeD, nodeF),
                "H", List.of(nodeA, nodeD, nodeF)
        );

        for (Node node : graph.getNodes()) {
            List<Node> expectedPath = expectedShortestPaths.get(node.getName());
            if (expectedPath != null) {
                assertEquals(node.getShortestPath(),expectedPath);
            }
        }
    }
}
