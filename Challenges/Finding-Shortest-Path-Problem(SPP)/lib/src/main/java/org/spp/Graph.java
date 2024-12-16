package org.spp;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record Graph(Map<String, List<Vector>> adjacentNodes){
}