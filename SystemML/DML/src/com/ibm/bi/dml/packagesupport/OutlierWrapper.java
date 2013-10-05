/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2013
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.packagesupport;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.nimble.algorithms.outlier.OutlierTask;
import org.nimble.hadoop.HDFSFileManager;
import org.nimble.io.utils.FixedWidthDataset;
import org.nimble.test.algorithms.MatrixUtils;
import org.nimble.utils.MatrixtoFixedWidth;

import com.ibm.bi.dml.packagesupport.Matrix.ValueType;



/**
 * Wrapper for outlier detection. This class first converts the 
 * matrix into a fixedwidthdataset appropriate for outlier detection. 
 * It then invokes outlierTask and returns the result as a matrix.
 * It takes 3 parameters, the input matrix, number of outliers, and k
 * neighborhood for the outliers 
 * It returns a matrix representing the outliers
 * 
 *
 */
public class OutlierWrapper extends PackageFunction 
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2013\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";

	//to be used when aggregating rows.
	final long BLOCK_SIZE = 10000000;
	private static final long serialVersionUID = 6799705939733343000L;
	final String OUTPUT_FILE = "outlierWrapperOutput";
	final String TASK_OUTPUT = "outliertaskOutput";
	
	Matrix return_outliers; 

	@Override
	public int getNumFunctionOutputs() {
		return 1;
	}

	@Override
	public FIO getFunctionOutput(int pos) {
		return return_outliers;
	}

	@Override
	public void execute() {
		try
		{
			//preprocess matrix to convert to fixed width dataset
			Matrix o = (Matrix) this.getFunctionInput(0);
			
			MatrixtoFixedWidth dataConv = new MatrixtoFixedWidth(o.getNumRows(), o.getNumCols(), BLOCK_SIZE, BLOCK_SIZE);
			FixedWidthDataset d = new FixedWidthDataset();
			d.setFilePath(o.getFilePath());
			d.setNumFields(1);
			d.setFieldType(0, "java.lang.String");
			dataConv.setNumInputDatasets(1);
			dataConv.addInputDataset(d, 0);
			this.getDAGQueue().pushTask(dataConv);
			dataConv = (MatrixtoFixedWidth) this.getDAGQueue().waitOnTask(dataConv);
			FixedWidthDataset outlierDataset = dataConv.getProcessedDataset();
			
			String fnameOutput = createOutputFilePathAndName( OUTPUT_FILE );
			String fnameTask = createOutputFilePathAndName( TASK_OUTPUT );
			
			
			//execute outlier detection on converted dataset
			//top m outliers
			Scalar m = (Scalar) this.getFunctionInput(1);
			//distance wrt kth nn
			Scalar k = (Scalar) this.getFunctionInput(2);
			OutlierTask outlierTask;
			
			outlierTask = new OutlierTask();
			outlierTask.setWithoutConfig(true);
			outlierTask.setBinnerOutputInfo(createOutputFilePathAndName("binnerOutput"));
			outlierTask.setEntries_per_file(50000);
			outlierTask.setK(Integer.parseInt(k.getValue()));
			outlierTask.setM(Integer.parseInt(m.getValue()));
			outlierTask.setMaxBinSize(2000);
			outlierTask.setNumCenters(3);
			outlierTask.setPruneInMemory(false);
			outlierTask.setResultFile(fnameTask);
			outlierTask.setRunBinner(true);
			outlierTask.setRunProcessor(true);
			outlierTask.setTriHeuristic(false);
			outlierTask.setMaxIterations(2);
			
			outlierTask.setWithoutConfig(true);
			
			
			outlierTask.setNumInputDatasets(1);
			outlierTask.addInputDataset(outlierDataset, 0);
			this.getDAGQueue().pushTask(outlierTask);
			outlierTask = (OutlierTask) this.getDAGQueue().waitOnTask(outlierTask);
			
			DataInputStream inStream = 
					this.getDAGQueue().getRuntimeDriver().getFileManager().getInputStream
					( fnameTask );
			
			double [][] outliers = MatrixUtils.readMatrixAsIVJText(inStream, Integer.parseInt(m.getValue()), (int)o.getNumCols(), ",");
			
			//write out centers
			DataOutputStream ostream = HDFSFileManager.getOutputStreamStatic(fnameOutput, true);
			for(int i=0; i < outliers.length; i++)
			{
				for(int j=0; j < outliers[i].length; j++)
				{
				    ostream.writeBytes((i+1) + " " + (j+1) + " " + outliers[i][j] + "\n");	
				}
			}
			
			ostream.close();

			//setup output to be returned
			return_outliers = new Matrix(fnameOutput, outliers.length , o.getNumCols(), ValueType.Double);
			

		}
		catch(Exception e)
		{
			throw new PackageRuntimeException("Error executing outlier detection",e);
		}


	}

}
