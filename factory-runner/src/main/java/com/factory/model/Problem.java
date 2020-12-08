package com.factory.model;

import java.util.List;
import lombok.Data;

@Data
public class Problem {

	private final int target;
	private final List<Machine> machines;
}
