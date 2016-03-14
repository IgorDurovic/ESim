package MainSimulation;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import GraphTypes.WorldGraph;

public class Main{
	
	/*
	 	Keeping track of different demographics.
		The separate groups are based on the SIR/SIS model demographic distinction.
		At the beginning of each simulation the majority of the population is part of the susceptible group.
		After each cycle the groups are updated to account for the spread of the demographic.
		If an individual has been exposed to the pathogen for a specified period they are added to the recovered
		group. An individual can only be part of one group at any given time in the simulation.
	*/
	
	public static int totalPopulation; //total amount of people in the scope of the simulation
								       //updated for deaths, births, immigration, and emigration
	public static int susceptible, infected, recovered;
	
	public int deaths; //keeps track of the death count throughout the simulation, used in data
					   //visualization after the simulation is complete.
	
	public static WorldGraph world;
	public static WorldGraph tempWorld;
	public static ArrayList<Person> population;
	
	final static XYSeries series1 = new XYSeries("Susceptible");
    final static XYSeries series2 = new XYSeries("Infected");
    final static XYSeries series3 = new XYSeries("Recovered");
    final static XYSeries series4 = new XYSeries("Deaths");
    final static XYSeriesCollection dataset = new XYSeriesCollection();
    
    final static XYDataset dataset1 = createDataset();
    final static JFreeChart chart = createChart(dataset1);
    final static ChartPanel chartPanel = new ChartPanel(chart);
	
	public static void setup() throws Exception{
		world = new WorldGraph("World", 0);
		tempWorld = new WorldGraph("TempWorld", 0);
		totalPopulation = 50000;
		susceptible = 50000;
		infected = 0;
		recovered = 0;
		
		population = Person.generatePopulation(totalPopulation);
		
		world.generateRandom(population, totalPopulation);
		
		Pathogen mainPathogen = Pathogen.constructPathogen();
		startSim(mainPathogen);
			
	}
	
	public static void startSim(Pathogen p){
		for(int i = 0; i < 20000; i++){
			world.movement(); //movement phase
			world.infection(); //infection phase;
		}
	}
	
	public void graphing(int sp, int ip, int rp, int d){
		dataset(sp, ip, rp, d);
	}
	
	private void dataset(int s, int i, int r, int d) {
        
        series1.add(count, s);

        series2.add(count, i);

        series3.add(count, r);
        
        series4.add(count, r);
                   
    }
	
	public static XYDataset createDataset(){
		final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
		return dataset;
	}
	
	private static JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Epidemic Simulation",      // chart title
            "cycle",                      // x axis label
            "number of people",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        //final StandardLegend legend = (StandardLegend) chart.getLegend();
        //legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customization
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
	
	public static void main(String[] args) throws Exception{
		
		setup();
		
		world.display();
		PropGraph pg = new PropGraph("simulation", Main.createChart(Main.createDataset()), chartPanel);
		pg.pack();
		RefineryUtilities.centerFrameOnScreen(pg);
        pg.setVisible(true);
	}
}
