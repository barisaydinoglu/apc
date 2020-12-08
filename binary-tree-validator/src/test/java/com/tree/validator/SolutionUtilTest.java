package com.tree.validator;

import com.tree.validator.model.Pair;
import com.tree.validator.model.Problem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class SolutionUtilTest {

	@Test
	public void testSolveSuccessful() {
		//GIVEN
		val pairs = new ArrayList<Pair>();
		pairs.add(new Pair('a','b'));
		pairs.add(new Pair('b','f'));
		pairs.add(new Pair('a','c'));
		pairs.add(new Pair('b','e'));
		pairs.add(new Pair('c','n'));
		val testCase = new Problem(pairs);
		//WHEN
		val result = SolutionUtil.solve(testCase);
		//THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testValidateTreeSuccessful() throws IOException {
		//GIVEN
		val resourcesDirectory = new File("src/test/resources");
		val filePath = resourcesDirectory.getAbsolutePath() + "/example.txt";
		//WHEN
		val result = SolutionUtil.validateTree(filePath);
		//THEN
		Assert.assertEquals("YES", result);
	}
}
