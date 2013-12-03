package com.example.medcenter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import com.androidplot.xy.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class GraphingActivity extends Activity {

  private XYPlot plot;
  String patientUserName = "";
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Get the directory path to the download folder and create an app
    // folder there.
    String directoryPath = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS).toString()
        + "/";
    
    String fileName = UserInformation.userName + ".txt";
    
    //Read existing user data from file.
    String data;
	try {
		data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
		String[] patientInfo = UserInformation.parseInfo(data);

	    // fun little snippet that prevents users from taking screenshots
	    // on ICS+ devices :-)
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
	        WindowManager.LayoutParams.FLAG_SECURE);

	    setContentView(R.layout.activity_graphing);

	    Log.d("weight",patientInfo[6]);
	    UserInformation.userName = patientUserName;
	    // Get the directory path to the download folder and create an app
	    // folder there.
	  
	    	//Get the patients information.

	    // initialize our XYPlot reference:
	    plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

	    // Create a couple arrays of y-values to plot:
	    Integer[] xVals = { 1, 2, 3, 4, 5, 6 };
	    Integer[] yVals = { Integer.parseInt(patientInfo[6]), Integer.parseInt(patientInfo[6]), 124, 125, 127, 160 };

	    // Turn the above arrays into XYSeries':
	    XYSeries series1 = new SimpleXYSeries(Arrays.asList(xVals), // SimpleXYSeries
	                                                                // takes a List
	                                                                // so turn our
	                                                                // array into a
	                                                                // List
	        Arrays.asList(yVals), "Weight"); // Set the display title of the series

	    // Create a formatter to use for drawing a series using LineAndPointRenderer
	    // and configure it from xml:
	    LineAndPointFormatter series1Format = new LineAndPointFormatter();
	    series1Format.setPointLabelFormatter(new PointLabelFormatter());
	    series1Format.configure(getApplicationContext(),
	        R.xml.line_point_formatter_with_plf1);

	    // add a new series' to the xyplot:
	    plot.addSeries(series1, series1Format);

	    // reduce the number of range labels
	    plot.setTicksPerRangeLabel(3);
	    plot.getGraphWidget().setDomainLabelOrientation(-45);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

  
}
}