package com.example.medcenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import java.util.Arrays;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class GraphingActivity extends Activity {

  private XYPlot plot;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // fun little snippet that prevents users from taking screenshots
    // on ICS+ devices :-)
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE);

    setContentView(R.layout.activity_graphing);

    // initialize our XYPlot reference:
    plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

    // Create a couple arrays of y-values to plot:
    Integer[] xVals = { 1, 2, 3, 4, 5, 6 };
    Integer[] yVals = { 120, 130, 124, 125, 127, 160 };

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

  }
}