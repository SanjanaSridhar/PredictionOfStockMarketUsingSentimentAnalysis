import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartUtilities;
import java.sql.*;
import java.awt.Color;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



public class BarChartTotalCount {

    public void createCategoryChart()
    {

        DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
        String apos=new String();
        String tpos=new String();
        String mpos=new String();
        String aneg=new String();
        String tneg=new String();
        String mneg=new String();


        try
        {
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/";
            String db = "features";
            String driver = "com.mysql.jdbc.Driver";
            ResultSet rs = null;

            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanju");
            Statement st = con.createStatement();
            rs = st.executeQuery("select * from count where type = 'article';");
            if(rs.first()== true)
            {
                apos=rs.getString("positive");
                aneg=rs.getString("negative");
            }

            rs = st.executeQuery("select * from count where type = 'tweet';");
            if(rs.first()== true)
            {
                tpos=rs.getString("positive");
                tneg=rs.getString("negative");
            }

            rs = st.executeQuery("select * from count where type = 'message';");
            if(rs.first()== true)
            {
                mpos=rs.getString("positive");
                mneg=rs.getString("negative");
            }




        categoryDataset.setValue(Integer.parseInt(apos)+Integer.parseInt(aneg), "Articles", "Articles");
        categoryDataset.setValue(Integer.parseInt(tpos)+Integer.parseInt(tneg), "Tweets", "Tweets");
        categoryDataset.setValue(Integer.parseInt(mpos)+Integer.parseInt(mneg), "Messages", "Messages");




        }

        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        JFreeChart chart = ChartFactory.createBarChart3D
                     ("Total Count of training samples", // Title
                      "",              // X-Axis label
                      "Count",// Y-Axis label
                      categoryDataset,         // Dataset
                      PlotOrientation.VERTICAL,
                      true,                     // Show legend
                      true,
                      false
                     );
        final CategoryPlot plot = chart.getCategoryPlot();
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...

       final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.BLUE,
            0.0f, 0.0f, Color.BLUE
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.YELLOW,
            0.0f, 0.0f, Color.YELLOW
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.MAGENTA,
            0.0f, 0.0f, Color.MAGENTA
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        saveChart(chart);
    }

    public void saveChart(JFreeChart chart)
    {
        String fileName="C:/Documents and Settings/new user/Desktop/TotalCount.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
       ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
    public static void main(String args [])
    {
        new BarChartTotalCount().createCategoryChart();


    }

}