package com.tree.validator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Graph {

	private final Map<Character, List<Character>> adjVertices = new HashMap<>();

	public void addEdge(Character edge1, Character edge2) {
		adjVertices.putIfAbsent(edge1, new ArrayList<>());
		adjVertices.putIfAbsent(edge2, new ArrayList<>());
		adjVertices.get(edge1).add(edge2);
	}
}
