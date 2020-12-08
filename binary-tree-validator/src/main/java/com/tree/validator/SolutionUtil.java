package com.tree.validator;

import com.tree.validator.model.Graph;
import com.tree.validator.model.Pair;
import com.tree.validator.model.Problem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;

public class SolutionUtil {

	public static String validateTree(final String filePath) throws IOException {
		val problem = parseProblemFromFile(filePath);
		return solve(problem) ? "YES" : "NO";
	}

	public static boolean solve(final Problem problem) {
		val graph = createGraphFromEdgePairs(problem.getPairs());
		val roots = findRootOfTree(graph);
		if (roots.size() != 1) {
			return false;
		}
		return validateTreeByDepthFirstTraversal(graph, roots.get(0));
	}

	private static Problem parseProblemFromFile(final String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8.name()))) {
			val pairs = new ArrayList<Pair>();
			Arrays.stream(br.readLine().split(","))
					.forEach(pair -> pairs.add(new Pair(pair.charAt(0), pair.charAt(1))));
			return new Problem(pairs);
		}
	}

	private static Graph createGraphFromEdgePairs(final List<Pair> pairs) {
		val graph = new Graph();
		pairs.forEach(pair -> graph.addEdge(pair.getEdge1(), pair.getEdge2()));
		return graph;
	}

	private static List<Character> findRootOfTree(final Graph graph) {
		val roots = new ArrayList<>(graph.getAdjVertices().keySet());
		graph.getAdjVertices().values().stream().flatMap(Collection::stream).collect(
				Collectors.toSet()).forEach(roots::remove);
		return roots;
	}

	private static boolean validateTreeByDepthFirstTraversal(final Graph graph, final Character root) {
		val visited = new LinkedHashSet<Character>();
		val stack = new ArrayDeque<Character>();
		stack.push(root);
		while (!stack.isEmpty()) {
			val vertex = stack.pop();
			if (visited.contains(vertex)) {
				return false;
			}
			visited.add(vertex);
			val children = graph.getAdjVertices().get(vertex);
			if (children != null && !children.isEmpty()) {
				children.forEach(stack::push);
			}
		}
		return graph.getAdjVertices().size() == visited.size();
	}
}
