package org.spp;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
public record Route(List<Vector> route, int totalDistance) {

    @Override
    public String toString() {
        return route.stream().map(vector -> formatVector(vector)).collect(Collectors.joining(" → "));
    }

    private String formatVector(Vector vector) {
        return vector.sourceNode() + vector.destinationNode();
    }
}
