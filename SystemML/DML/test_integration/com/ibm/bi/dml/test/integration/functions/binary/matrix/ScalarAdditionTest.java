/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2013
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.test.integration.functions.binary.matrix;

import org.junit.Test;

import com.ibm.bi.dml.test.integration.AutomatedTestBase;
import com.ibm.bi.dml.test.integration.TestConfiguration;



public class ScalarAdditionTest extends AutomatedTestBase 
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2013\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
	
	@Override
	public void setUp() {
		baseDirectory = SCRIPT_DIR + "functions/binary/matrix/";
		
		// positive tests
		availableTestConfigurations.put("IntConstTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		availableTestConfigurations.put("IntVarTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		availableTestConfigurations.put("DoubleConstTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		availableTestConfigurations.put("DoubleVarTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		availableTestConfigurations.put("SparseTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		availableTestConfigurations.put("EmptyTest", new TestConfiguration("ScalarAdditionTest",
				new String[] { "vector_left", "vector_right", "matrix_left", "matrix_right" }));
		
		// negative tests
	}
	
	@Test
	public void testIntConst() {
		int rows = 10;
		int cols = 10;
		int summand = 2;
		
		TestConfiguration config = availableTestConfigurations.get("IntConstTest");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);
		config.addVariable("vardeclaration", "");
		config.addVariable("summand", summand);
		
		loadTestConfiguration("IntConstTest");
		
		double[][] vector = getRandomMatrix(rows, 1, 0, 1, 1, -1);
		double[][] computedVector = new double[rows][1];
		for(int i = 0; i < rows; i++) {
			computedVector[i][0] = vector[i][0] + summand;
		}
		writeInputMatrix("vector", vector);
		writeExpectedMatrix("vector_left", computedVector);
		writeExpectedMatrix("vector_right", computedVector);
		
		double[][] matrix = getRandomMatrix(rows, cols, 0, 1, 1, -1);
		double[][] computedMatrix = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				computedMatrix[i][j] = matrix[i][j] + summand;
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedMatrix("matrix_left", computedMatrix);
		writeExpectedMatrix("matrix_right", computedMatrix);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testIntVar() {
		int rows = 10;
		int cols = 10;
		int summand = 2;
		
		TestConfiguration config = availableTestConfigurations.get("IntVarTest");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);
		config.addVariable("vardeclaration", "Summand = " + summand);
		config.addVariable("summand", "Summand");
		
		loadTestConfiguration("IntVarTest");
		
		double[][] vector = getRandomMatrix(rows, 1, 0, 1, 1, -1);
		double[][] computedVector = new double[rows][1];
		for(int i = 0; i < rows; i++) {
			computedVector[i][0] = vector[i][0] + summand;
		}
		writeInputMatrix("vector", vector);
		writeExpectedMatrix("vector_left", computedVector);
		writeExpectedMatrix("vector_right", computedVector);
		
		double[][] matrix = getRandomMatrix(rows, cols, 0, 1, 1, -1);
		double[][] computedMatrix = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				computedMatrix[i][j] = matrix[i][j] + summand;
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedMatrix("matrix_left", computedMatrix);
		writeExpectedMatrix("matrix_right", computedMatrix);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testDoubleConst() {
		int rows = 10;
		int cols = 10;
		double summand = 2;
		
		TestConfiguration config = availableTestConfigurations.get("DoubleConstTest");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);
		config.addVariable("vardeclaration", "");
		config.addVariable("summand", summand);
		
		loadTestConfiguration("DoubleConstTest");
		
		double[][] vector = getRandomMatrix(rows, 1, 0, 1, 1, -1);
		double[][] computedVector = new double[rows][1];
		for(int i = 0; i < rows; i++) {
			computedVector[i][0] = vector[i][0] + summand;
		}
		writeInputMatrix("vector", vector);
		writeExpectedMatrix("vector_left", computedVector);
		writeExpectedMatrix("vector_right", computedVector);
		
		double[][] matrix = getRandomMatrix(rows, cols, 0, 1, 1, -1);
		double[][] computedMatrix = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				computedMatrix[i][j] = matrix[i][j] + summand;
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedMatrix("matrix_left", computedMatrix);
		writeExpectedMatrix("matrix_right", computedMatrix);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testDoubleVar() {
		int rows = 10;
		int cols = 10;
		double summand = 2;
		
		TestConfiguration config = availableTestConfigurations.get("DoubleVarTest");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);
		config.addVariable("vardeclaration", "Summand = " + summand);
		config.addVariable("summand", "Summand");
		
		loadTestConfiguration("DoubleVarTest");
		
		double[][] vector = getRandomMatrix(rows, 1, 0, 1, 1, -1);
		double[][] computedVector = new double[rows][1];
		for(int i = 0; i < rows; i++) {
			computedVector[i][0] = vector[i][0] + summand;
		}
		writeInputMatrix("vector", vector);
		writeExpectedMatrix("vector_left", computedVector);
		writeExpectedMatrix("vector_right", computedVector);
		
		double[][] matrix = getRandomMatrix(rows, cols, 0, 1, 1, -1);
		double[][] computedMatrix = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				computedMatrix[i][j] = matrix[i][j] + summand;
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedMatrix("matrix_left", computedMatrix);
		writeExpectedMatrix("matrix_right", computedMatrix);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testSparse() {
		int rows = 100;
		int cols = 50;
		int summand = 2;
		
		TestConfiguration config = availableTestConfigurations.get("SparseTest");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);
		config.addVariable("vardeclaration", "");
		config.addVariable("summand", summand);
		
		loadTestConfiguration("SparseTest");
		
		double[][] vector = getRandomMatrix(rows, 1, -1, 1, 0.05, -1);
		double[][] computedVector = new double[rows][1];
		for(int i = 0; i < rows; i++) {
			computedVector[i][0] = vector[i][0] + summand;
		}
		writeInputMatrix("vector", vector);
		writeExpectedMatrix("vector_left", computedVector);
		writeExpectedMatrix("vector_right", computedVector);
		
		double[][] matrix = getRandomMatrix(rows, cols, -1, 1, 0.05, -1);
		double[][] computedMatrix = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				computedMatrix[i][j] = matrix[i][j] + summand;
			}
		}
		writeInputMatrix("matrix", matrix);
		writeExpectedMatrix("matrix_left", computedMatrix);
		writeExpectedMatrix("matrix_right", computedMatrix);
		
		runTest();
		
		compareResults();
	}

}
