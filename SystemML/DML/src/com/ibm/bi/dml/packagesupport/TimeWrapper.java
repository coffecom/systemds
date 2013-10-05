/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2013
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.packagesupport;

//import com.ibm.bi.dml.packagesupport.Scalar;
//import com.ibm.bi.dml.packagesupport.FIO;

import com.ibm.bi.dml.packagesupport.Scalar.ScalarType;
import com.ibm.bi.dml.packagesupport.PackageFunction;
import com.ibm.bi.dml.packagesupport.PackageRuntimeException;

/**
 * Wrapper class for time invocation
 * 
 * time = externalFunction(Integer i) return (Double B) implemented in
 * (classname="com.ibm.bi.dml.packagesupport.TimeWrapper",exectype="mem");
 * 
 * t = time (1);
 * 
 * print( "Time in millsecs= " + t);
 * 
 */
public class TimeWrapper extends PackageFunction 
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2013\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
	
	private static final long serialVersionUID = 1L;

	private Scalar _ret;

	@Override
	public int getNumFunctionOutputs() {
		return 1;
	}

	@Override
	public FIO getFunctionOutput(int pos) {
		if (pos == 0)
			return _ret;

		throw new PackageRuntimeException(
				"Invalid function output being requested");
	}

	@Override
	public void execute() {
		try {
			// int past = Integer.parseInt(((Scalar) getFunctionInput(0))
			// .getValue());
			long present = System.currentTimeMillis();
			_ret = new Scalar(ScalarType.Double, String.valueOf(present));
		} catch (Exception e) {
			throw new PackageRuntimeException(
					"Error executing external time function", e);
		}
	}

}
