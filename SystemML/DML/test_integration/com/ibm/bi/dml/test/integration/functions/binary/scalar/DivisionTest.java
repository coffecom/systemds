/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2013
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.test.integration.functions.binary.scalar;

import org.junit.Test;

import com.ibm.bi.dml.test.integration.AutomatedTestBase;
import com.ibm.bi.dml.test.integration.TestConfiguration;


public class DivisionTest extends AutomatedTestBase 
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2013\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
	
	@Override
	public void setUp() {
		baseDirectory = SCRIPT_DIR + "functions/binary/scalar/";
		availableTestConfigurations.put("ConstConstTest", new TestConfiguration("DivisionTest", new String[] {
				"int_int", "int_double", "double_double", "double_int" }));
		availableTestConfigurations.put("VarConstTest", new TestConfiguration("DivisionTest", new String[] { "int_int",
				"int_double", "double_double", "double_int" }));
		availableTestConfigurations.put("ConstVarTest", new TestConfiguration("DivisionTest", new String[] { "int_int",
				"int_double", "double_double", "double_int" }));
		availableTestConfigurations.put("VarVarTest", new TestConfiguration("DivisionTest", new String[] { "int_int",
				"int_double", "double_double", "double_int" }));
		availableTestConfigurations.put("PositiveDivisionByZeroTest", new TestConfiguration("DivisionSingleTest",
				new String[] { "computed" }));
		availableTestConfigurations.put("NegativeDivisionByZeroTest", new TestConfiguration("DivisionSingleTest",
				new String[] { "computed" }));
		availableTestConfigurations.put("ZeroDivisionByZeroTest", new TestConfiguration("DivisionSingleTest",
				new String[] { "computed" }));
	}

	@Test
	public void testConstConst() {
		double intIntValue1 = 2;
		double intIntValue2 = 3;
		double intDoubleValue1 = 2;
		double intDoubleValue2 = 3;
		double doubleDoubleValue1 = 2;
		double doubleDoubleValue2 = 3;
		double doubleIntValue1 = 2;
		double doubleIntValue2 = 3;

		TestConfiguration config = availableTestConfigurations.get("ConstConstTest");
		config.addVariable("intintvardeclaration", "");
		config.addVariable("intintop1", intIntValue1);
		config.addVariable("intintop2", intIntValue2);
		config.addVariable("intdoublevardeclaration", "");
		config.addVariable("intdoubleop1", intDoubleValue1);
		config.addVariable("intdoubleop2", intDoubleValue2);
		config.addVariable("doubledoublevardeclaration", "");
		config.addVariable("doubledoubleop1", doubleDoubleValue1);
		config.addVariable("doubledoubleop2", doubleDoubleValue2);
		config.addVariable("doubleintvardeclaration", "");
		config.addVariable("doubleintop1", doubleIntValue1);
		config.addVariable("doubleintop2", doubleIntValue2);

		loadTestConfiguration("ConstConstTest");

		double computedIntIntValue = intIntValue1 / intIntValue2;
		double computedIntDoubleValue = intDoubleValue1 / intDoubleValue2;
		double computedDoubleDoubleValue = doubleDoubleValue1 / doubleDoubleValue2;
		double computedDoubleIntValue = doubleIntValue1 / doubleIntValue2;

		createHelperMatrix();
		writeExpectedHelperMatrix("int_int", computedIntIntValue);
		writeExpectedHelperMatrix("int_double", computedIntDoubleValue);
		writeExpectedHelperMatrix("double_double", computedDoubleDoubleValue);
		writeExpectedHelperMatrix("double_int", computedDoubleIntValue);

		runTest();

		compareResults();
	}

	@Test
	public void testVarConst() {
		double intIntValue1 = 2;
		double intIntValue2 = 3;
		double intDoubleValue1 = 2;
		double intDoubleValue2 = 3;
		double doubleDoubleValue1 = 2;
		double doubleDoubleValue2 = 3;
		double doubleIntValue1 = 2;
		double doubleIntValue2 = 3;

		TestConfiguration config = availableTestConfigurations.get("VarConstTest");
		config.addVariable("intintvardeclaration", "IntIntVar = " + intIntValue1 + ";");
		config.addVariable("intintop1", "IntIntVar");
		config.addVariable("intintop2", intIntValue2);
		config.addVariable("intdoublevardeclaration", "IntDoubleVar = " + intDoubleValue1 + ";");
		config.addVariable("intdoubleop1", "IntDoubleVar");
		config.addVariable("intdoubleop2", intDoubleValue2);
		config.addVariable("doubledoublevardeclaration", "DoubleDoubleVar = " + doubleDoubleValue1 + ";");
		config.addVariable("doubledoubleop1", "DoubleDoubleVar");
		config.addVariable("doubledoubleop2", doubleDoubleValue2);
		config.addVariable("doubleintvardeclaration", "DoubleIntVar = " + doubleIntValue1 + ";");
		config.addVariable("doubleintop1", "DoubleIntVar");
		config.addVariable("doubleintop2", doubleIntValue2);

		loadTestConfiguration("VarConstTest");

		double computedIntIntValue = intIntValue1 / intIntValue2;
		double computedIntDoubleValue = intDoubleValue1 / intDoubleValue2;
		double computedDoubleDoubleValue = doubleDoubleValue1 / doubleDoubleValue2;
		double computedDoubleIntValue = doubleIntValue1 / doubleIntValue2;

		createHelperMatrix();
		writeExpectedHelperMatrix("int_int", computedIntIntValue);
		writeExpectedHelperMatrix("int_double", computedIntDoubleValue);
		writeExpectedHelperMatrix("double_double", computedDoubleDoubleValue);
		writeExpectedHelperMatrix("double_int", computedDoubleIntValue);

		runTest();

		compareResults();
	}

	@Test
	public void testConstVar() {
		double intIntValue1 = 2;
		double intIntValue2 = 3;
		double intDoubleValue1 = 2;
		double intDoubleValue2 = 3;
		double doubleDoubleValue1 = 2;
		double doubleDoubleValue2 = 3;
		double doubleIntValue1 = 2;
		double doubleIntValue2 = 3;

		TestConfiguration config = availableTestConfigurations.get("ConstVarTest");
		config.addVariable("intintvardeclaration", "IntIntVar = " + intIntValue2 + ";");
		config.addVariable("intintop1", intIntValue1);
		config.addVariable("intintop2", "IntIntVar");
		config.addVariable("intdoublevardeclaration", "IntDoubleVar = " + intDoubleValue2 + ";");
		config.addVariable("intdoubleop1", intDoubleValue1);
		config.addVariable("intdoubleop2", "IntDoubleVar");
		config.addVariable("doubledoublevardeclaration", "DoubleDoubleVar = " + doubleDoubleValue2 + ";");
		config.addVariable("doubledoubleop1", doubleDoubleValue1);
		config.addVariable("doubledoubleop2", "DoubleDoubleVar");
		config.addVariable("doubleintvardeclaration", "DoubleIntVar = " + doubleIntValue2 + ";");
		config.addVariable("doubleintop1", doubleIntValue1);
		config.addVariable("doubleintop2", "DoubleIntVar");

		loadTestConfiguration("ConstVarTest");

		double computedIntIntValue = intIntValue1 / intIntValue2;
		double computedIntDoubleValue = intDoubleValue1 / intDoubleValue2;
		double computedDoubleDoubleValue = doubleDoubleValue1 / doubleDoubleValue2;
		double computedDoubleIntValue = doubleIntValue1 / doubleIntValue2;

		createHelperMatrix();
		writeExpectedHelperMatrix("int_int", computedIntIntValue);
		writeExpectedHelperMatrix("int_double", computedIntDoubleValue);
		writeExpectedHelperMatrix("double_double", computedDoubleDoubleValue);
		writeExpectedHelperMatrix("double_int", computedDoubleIntValue);

		runTest();

		compareResults();
	}

	@Test
	public void testVarVar() {
		double intIntValue1 = 2;
		double intIntValue2 = 3;
		double intDoubleValue1 = 2;
		double intDoubleValue2 = 3;
		double doubleDoubleValue1 = 2;
		double doubleDoubleValue2 = 3;
		double doubleIntValue1 = 2;
		double doubleIntValue2 = 3;

		TestConfiguration config = availableTestConfigurations.get("VarVarTest");
		config.addVariable("intintvardeclaration", "IntIntVar1 = " + intIntValue1 + ";" + "IntIntVar2 = "
				+ intIntValue2 + ";");
		config.addVariable("intintop1", "IntIntVar1");
		config.addVariable("intintop2", "IntIntVar2");
		config.addVariable("intdoublevardeclaration", "IntDoubleVar1 = " + intDoubleValue1 + ";" + "IntDoubleVar2 = "
				+ intDoubleValue2 + ";");
		config.addVariable("intdoubleop1", "IntDoubleVar1");
		config.addVariable("intdoubleop2", "IntDoubleVar2");
		config.addVariable("doubledoublevardeclaration", "DoubleDoubleVar1 = " + doubleDoubleValue1 + ";"
				+ "DoubleDoubleVar2 = " + doubleDoubleValue2 + ";");
		config.addVariable("doubledoubleop1", "DoubleDoubleVar1");
		config.addVariable("doubledoubleop2", "DoubleDoubleVar2");
		config.addVariable("doubleintvardeclaration", "DoubleIntVar1 = " + doubleIntValue1 + ";" + "DoubleIntVar2 = "
				+ doubleIntValue2 + ";");
		config.addVariable("doubleintop1", "DoubleIntVar1");
		config.addVariable("doubleintop2", "DoubleIntVar2");

		loadTestConfiguration("VarVarTest");

		double computedIntIntValue = intIntValue1 / intIntValue2;
		double computedIntDoubleValue = intDoubleValue1 / intDoubleValue2;
		double computedDoubleDoubleValue = doubleDoubleValue1 / doubleDoubleValue2;
		double computedDoubleIntValue = doubleIntValue1 / doubleIntValue2;

		createHelperMatrix();
		writeExpectedHelperMatrix("int_int", computedIntIntValue);
		writeExpectedHelperMatrix("int_double", computedIntDoubleValue);
		writeExpectedHelperMatrix("double_double", computedDoubleDoubleValue);
		writeExpectedHelperMatrix("double_int", computedDoubleIntValue);

		runTest();

		compareResults();
	}

	@Test
	public void testPositiveIntegerDivisionByZero() {
		double op1 = 5;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("PositiveDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("PositiveDivisionByZeroTest");
		
		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.POSITIVE_INFINITY);

		runTest();
		
		compareResults();
	}

	@Test
	public void testPositiveDoubleDivisionByZero() {
		double op1 = 5;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("PositiveDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("PositiveDivisionByZeroTest");

		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.POSITIVE_INFINITY);

		runTest();

		compareResults();
	}

	@Test
	public void testNegativeDoubleDivisionByZero() {
		double op1 = -5;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("NegativeDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("NegativeDivisionByZeroTest");

		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.NEGATIVE_INFINITY);

		runTest();

		compareResults();
	}

	@Test
	public void testNegativeIntegerDivisionByZero() {
		double op1 = -5;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("NegativeDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("NegativeDivisionByZeroTest");

		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.NEGATIVE_INFINITY);

		runTest();

		compareResults();
	}

	@Test
	public void testZeroDoubleDivisionByZero() {
		double op1 = 0;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("ZeroDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("ZeroDivisionByZeroTest");

		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.NaN);

		runTest();

		compareResults();
	}

	@Test
	public void testZeroIntegerDivisionByZero() {
		double op1 = 0;
		double op2 = 0;

		TestConfiguration config = availableTestConfigurations.get("ZeroDivisionByZeroTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", op1);
		config.addVariable("op2", op2);

		loadTestConfiguration("ZeroDivisionByZeroTest");
		
		createHelperMatrix();
		writeExpectedHelperMatrix("computed", Double.NaN);

		runTest();
		
		compareResults();
	}
}
