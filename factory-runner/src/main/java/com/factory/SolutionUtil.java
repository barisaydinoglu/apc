package com.factory;

import com.factory.model.Machine;
import com.factory.model.Problem;
import com.factory.model.Solution;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;

public class SolutionUtil {

	public static String findOptimalSolutions(final String filePath) throws IOException {
		val output = new StringBuilder();
		val testCase = parseTestCasesFromFile(filePath);
		val optimalSolutions = getOptimalSolutions(testCase);
		output.append("Nr solutions=").append(optimalSolutions.size());
		optimalSolutions.forEach(solution -> output.append("\n").append(solution.getMachines().stream()
				.map(machine -> String.valueOf(machine.getSpeed()))
				.collect(Collectors.joining(" "))));
		output.append("\nWaste=").append(testCase.getTarget() - optimalSolutions.get(0).getTotalSpeed());
		return output.toString();
	}

	public static List<Solution> getOptimalSolutions(final Problem testCase) {
		val candidatesList = new ArrayList<List<Machine>>();
		val optimumSolutions = new ArrayList<Solution>();
		for (final Machine candidateItem : testCase.getMachines()) {
			val candidatesSize = candidatesList.size();
			for (int i = 0; i < candidatesSize; i++) {
				val candidate = candidatesList.get(i);
				val currentCandidate = new ArrayList<>(candidate);
				currentCandidate.add(candidateItem);
				determineOptimumCandidate(testCase, currentCandidate, optimumSolutions);
				candidatesList.add(currentCandidate);
			}
			val current = new ArrayList<Machine>();
			current.add(candidateItem);
			determineOptimumCandidate(testCase, current, optimumSolutions);
			candidatesList.add(current);
		}
		return optimumSolutions;
	}

	private static Problem parseTestCasesFromFile(final String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8.name()))) {
			val numberOfMachines = Integer.parseInt(br.readLine());
			val machines = new ArrayList<Machine>(numberOfMachines);
			val machineSpeeds = br.readLine();
			Arrays.stream(machineSpeeds.split(" ", numberOfMachines))
					.forEach(speed -> machines.add(new Machine(Integer.parseInt(speed))));
			val target = Integer.parseInt(br.readLine());
			return new Problem(target, machines);
		}
	}

	private static void determineOptimumCandidate(final Problem testCase, final List<Machine> currentCandidate,
			final List<Solution> optimumSolutions) {
		val currentCandidateSolution = new Solution(currentCandidate);
		val candidateSpeed = currentCandidateSolution.getTotalSpeed();
		if (candidateSpeed <= testCase.getTarget()) {
			if (!optimumSolutions.isEmpty()) {
				val optimumSpeed = optimumSolutions.get(0).getTotalSpeed();
				if (candidateSpeed > optimumSpeed) {
					optimumSolutions.clear();
					optimumSolutions.add(currentCandidateSolution);
				} else if (candidateSpeed == optimumSpeed) {
					optimumSolutions.add(currentCandidateSolution);
				}
			} else {
				optimumSolutions.add(currentCandidateSolution);
			}
		}
	}
}
