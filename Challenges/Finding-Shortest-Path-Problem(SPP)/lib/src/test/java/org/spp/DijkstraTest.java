package org.spp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DijkstraTest {

    @Test
    public void testSPPSuccess() {

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

        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);

        List<Node> shortestPathForNodeB = List.of(nodeA);
        List<Node> shortestPathForNodeC = List.of(nodeA, nodeB);
        List<Node> shortestPathForNodeD = List.of(nodeA);
        List<Node> shortestPathForNodeE = List.of(nodeA);
        List<Node> shortestPathForNodeF = List.of(nodeA, nodeD);
        List<Node> shortestPathForNodeG = List.of(nodeA, nodeD, nodeF);
        List<Node> shortestPathForNodeH = List.of(nodeA, nodeD, nodeF);

        Map<String, List<Node>> expectedShortestPaths = Map.of(
                "B", shortestPathForNodeB,
                "C", shortestPathForNodeC,
                "D", shortestPathForNodeD,
                "E", shortestPathForNodeE,
                "F", shortestPathForNodeF,
                "G", shortestPathForNodeG,
                "H", shortestPathForNodeH
        );

        for (Node node : graph.getNodes()) {
            List<Node> expectedPath = expectedShortestPaths.get(node.getName());
            if (expectedPath != null) {
                assertTrue(node.getShortestPath().equals(expectedPath));
            }
        }
    }
}
