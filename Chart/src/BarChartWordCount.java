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



public class BarChartWordCount {

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
            rs = st.executeQuery("select count(pcount) from words where pcount>0;");
            if(rs.first()== true)
            {
                apos=rs.getString(1);
                System.out.println(apos);
                
            }
            rs = st.executeQuery("select count(ncount) from words where ncount>0;");
            if(rs.first()== true)
            {
                aneg=rs.getString(1);
                System.out.println(aneg);

            }

            rs = st.executeQuery("select count(pcount) from twitter_words where pcount>0;");
            if(rs.first()== true)
            {
                tpos=rs.getString(1);
                System.out.println(tpos);
            }

            rs = st.executeQuery("select count(ncount) from twitter_words where ncount>0;");
            if(rs.first()== true)
            {
                tneg=rs.getString(1);
                System.out.println(tneg);

            }

            rs = st.executeQuery("select count(pcount) from messageboard_words where pcount>0;");
            if(rs.first()== true)
            {
                mpos=rs.getString(1);
                System.out.println(mpos);
            }

            rs = st.executeQuery("select count(ncount) from messageboard_words where ncount>0;");
            if(rs.first()== true)
            {
                mneg=rs.getString(1);
                System.out.println(mneg);
            }




        categoryDataset.setValue(Integer.parseInt(apos), "Positive", "Articles");
        categoryDataset.setValue(Integer.parseInt(tpos), "Positive", "Tweets");
        categoryDataset.setValue(Integer.parseInt(mpos), "Positive", "Messages");


        categoryDataset.setValue(Integer.parseInt(aneg), "Negative", "Articles");
        categoryDataset.setValue(Integer.parseInt(tneg), "Negative", "Tweets");
        categoryDataset.setValue(Integer.parseInt(mneg), "Negative", "Messages");


        }

        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        JFreeChart chart = ChartFactory.createBarChart3D
                     ("Count of words in database", // Title
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
            0.0f, 0.0f, Color.green,
            0.0f, 0.0f, Color.green
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.red,
            0.0f, 0.0f, Color.red
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        saveChart(chart);
    }

    public void saveChart(JFreeChart chart)
    {
        String fileName="C:/Documents and Settings/new user/Desktop/WordCount.jpg";
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
        new BarChartWordCount().createCategoryChart();


    }

}