package com.factory.model;

import java.util.List;
import lombok.Data;

@Data
public class Solution {

	private final List<Machine> machines;

	public int getTotalSpeed() {
		return getMachines().stream()
				.map(Machine::getSpeed)
				.reduce(0, Integer::sum);
	}
}
