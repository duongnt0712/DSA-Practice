package org.spp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraTest {

    @Test
    void testShortestPath() {
        Vector vAB = Vector.builder().sourceNode("A").destinationNode("B").distance(1).build();
        Vector vAE = Vector.builder().sourceNode("A").destinationNode("E").distance(5).build();
        Vector vAD = Vector.builder().sourceNode("A").destinationNode("D").distance(3).build();

        Vector vBC = Vector.builder().sourceNode("B").destinationNode("C").distance(6).build();
        Vector vBE = Vector.builder().sourceNode("B").destinationNode("E").distance(12).build();

        Vector vCA = Vector.builder().sourceNode("C").destinationNode("A").distance(12).build();
        Vector vCE = Vector.builder().sourceNode("C").destinationNode("E").distance(8).build();
        Vector vCG = Vector.builder().sourceNode("C").destinationNode("G").distance(9).build();

        Vector vDF = Vector.builder().sourceNode("D").destinationNode("F").distance(3).build();

        Vector vEG = Vector.builder().sourceNode("E").destinationNode("G").distance(3).build();

        Vector vFH = Vector.builder().sourceNode("F").destinationNode("H").distance(5).build();

        Vector vGH = Vector.builder().sourceNode("G").destinationNode("H").distance(8).build();

        Vector vHA = Vector.builder().sourceNode("H").destinationNode("A").distance(15).build();
        Vector vHC = Vector.builder().sourceNode("H").destinationNode("C").distance(5).build();
        Vector vHD = Vector.builder().sourceNode("H").destinationNode("D").distance(10).build();
        Vector vHG = Vector.builder().sourceNode("H").destinationNode("G").distance(4).build();

        // Given: A directed weighted graph
        Graph graph = Graph.builder()
                .adjacentNodes(Map.of(
                        "A", List.of( vAB, vAE, vAD ),
                        "B", List.of( vBC, vBE ),
                        "C", List.of( vCA, vCE, vCG ),
                        "D", List.of( vDF ),
                        "E", List.of( vEG ),
                        "F", List.of( vFH ),
                        "G", List.of( vGH ),
                        "H", List.of( vHA, vHC, vHD, vHG )
                ))
                .build();

        Dijkstra dijkstra = new Dijkstra(graph);

        // When: Find the shortest path
        Route routeAG = dijkstra.findShortestRoute( "A", "G" );
        Route routeCF = dijkstra.findShortestRoute( "C", "F" );

        // Then: Verify the path and total distance
        assertEquals( 8, routeAG.totalDistance() );
        assertEquals( 2, routeAG.route().size() );
        assertEquals( "AE → EG", routeAG.toString() );

        assertEquals( 18, routeCF.totalDistance() );
        assertEquals( 3, routeCF.route().size() );
        assertEquals( "CA → AD → DF", routeCF.toString() );
    }

    @Test
    void testNoPathAvailable() {
        Vector vAB = Vector.builder().sourceNode("A").destinationNode("B").distance(1).build();
        Vector vCG = Vector.builder().sourceNode("C").destinationNode("G").distance(9).build();

        // Given: A disconnected graph
        Graph graph = Graph.builder()
                .adjacentNodes( Map.of(
                        "A", List.of( vAB ),
                        "B", List.of(),
                        "C", List.of( vCG ),
                        "D", List.of()
                ))
                .build();

        Dijkstra dijkstra = new Dijkstra(graph);

        // When: Find the shortest path
        Route route = dijkstra.findShortestRoute("A", "D");

        // Then: Verify no path is found
        assertEquals( -1, route.totalDistance() );
        assertTrue( route.route().isEmpty() );
        assertEquals( "", route.toString() );
    }
}
