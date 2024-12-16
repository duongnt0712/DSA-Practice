package org.spp;

import lombok.Builder;

@Builder(toBuilder = true)
public record Vector (String sourceNode, String destinationNode, int distance) {
}