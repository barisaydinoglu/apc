package com.factory;

import com.factory.model.Machine;
import com.factory.model.Problem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class SolutionUtilTest {

	@Test
	public void testGetOptimalSolutionsSuccessful() {
		//GIVEN
		val things = new ArrayList<Machine>();
		val testSpeedData = Arrays.asList(1, 2, 4, 10, 5, 6);
		testSpeedData.forEach(speed -> things.add(new Machine(speed)));
		val testCase = new Problem(11, things);
		//WHEN
		val optimalSolutions = SolutionUtil.getOptimalSolutions(testCase);
		//THEN
		Assert.assertEquals(4, optimalSolutions.size());
	}

	@Test
	public void testFindOptimalSolutionsSuccessful() throws IOException {
		//GIVEN
		val resourcesDirectory = new File("src/test/resources");
		val filePath = resourcesDirectory.getAbsolutePath() + "/example.txt";
		//WHEN
		val result = SolutionUtil.findOptimalSolutions(filePath);
		//THEN
		Assert.assertEquals("Nr solutions=4\n"
				+ "1 10\n"
				+ "2 4 5\n"
				+ "1 4 6\n"
				+ "5 6\n"
				+ "Waste=0", result);
	}
}
