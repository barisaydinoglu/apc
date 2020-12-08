package com.factory;

import java.io.IOException;

public class Application {

	public static void main(final String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("input file path must provider as args[0], extra args is forbidden");
			System.exit(1);
		}
		System.out.println(SolutionUtil.findOptimalSolutions(args[0]));
	}
}
