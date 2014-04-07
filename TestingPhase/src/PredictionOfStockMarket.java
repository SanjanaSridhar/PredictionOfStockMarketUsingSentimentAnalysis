
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;


public class PredictionOfStockMarket extends javax.swing.JFrame {

   
    int pn,pp,np,nn;
    public PredictionOfStockMarket() {
        initComponents();
        pn=0;
        pp=0;
        np=0;
        nn=0;
        for(int i=0;i<50;i++)
        {
            Results.setValueAt("-", i, 1);
            Results.setValueAt("-", i, 2);
            Results.setValueAt("-", i, 3);
            Results.setValueAt(" ", i, 5);
            Results.setValueAt(" ", i, 6);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Result = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Results = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Feedback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Result.setText("Result");
        Result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultActionPerformed(evt);
            }
        });

        Results.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ACC Ltd.", null, null, null, null, null, null},
                {"Ambuja Cements Ltd.", null, null, null, null, null, null},
                {"Asian Paints", null, null, null, null, null, null},
                {"Axis Bank", null, null, null, null, null, null},
                {"Bajaj Auto", null, null, null, null, null, null},
                {"Bank of Baroda", null, null, null, null, null, null},
                {"BHEL", null, null, null, null, null, null},
                {"Bharat Petroleum Corporation", null, null, null, null, null, null},
                {"Bharti Airtel", null, null, null, null, null, null},
                {"Cairn Bharat", null, null, null, null, null, null},
                {"Cipla Ltd.", null, null, null, null, null, null},
                {"Coal India", null, null, null, null, null, null},
                {"DLF Ltd.", null, null, null, null, null, null},
                {"Dr.Reddy's Lab", null, null, null, null, null, null},
                {"GAIL Ltd.", null, null, null, null, null, null},
                {"Grasim Industries", null, null, null, null, null, null},
                {"HCL Technologies", null, null, null, null, null, null},
                {"HDFC Bank Ltd.", null, null, null, null, null, null},
                {"Hero MotoCorp ", null, null, null, null, null, null},
                {"Hindalco Industries", null, null, null, null, null, null},
                {"Hindustan Unilever", null, null, null, null, null, null},
                {"HDFC", null, null, null, null, null, null},
                {"ICICI Bank", null, null, null, null, null, null},
                {"IDFC", null, null, null, null, null, null},
                {"Infosys Technologies", null, null, null, null, null, null},
                {"ITC Ltd.", null, null, null, null, null, null},
                {"Jaiprakash Associates", null, null, null, null, null, null},
                {"Jindal Steel and Power", null, null, null, null, null, null},
                {"Kotak Mahindra Bank", null, null, null, null, null, null},
                {"Larsen & Toubro Ltd.", null, null, null, null, null, null},
                {"Lupin Ltd.", null, null, null, null, null, null},
                {"Mahindra & Mahindra Ltd.", null, null, null, null, null, null},
                {"Maruti Suzuki", null, null, null, null, null, null},
                {"NTPC Ltd.", null, null, null, null, null, null},
                {"Oil & Natural Gas Corporation", null, null, null, null, null, null},
                {"Power Grid Corporation", null, null, null, null, null, null},
                {"Punjab National Bank", null, null, null, null, null, null},
                {"Ranbaxy Laboratories", null, null, null, null, null, null},
                {"Reliance Industries", null, null, null, null, null, null},
                {"Reliance Infrastructure", null, null, null, null, null, null},
                {"Sesa Goa Ltd", null, null, null, null, null, null},
                {"Siemens Ltd.", null, null, null, null, null, null},
                {"State Bank of India", null, null, null, null, null, null},
                {"Sun Pharmaceutical Industries", null, null, null, null, null, null},
                {"Tata Motors Ltd.", null, null, null, null, null, null},
                {"Tata Power Co. Ltd.", null, null, null, null, null, null},
                {"Tata Steel Ltd.", null, null, null, null, null, null},
                {"Tata Consultancy Services", null, null, null, null, null, null},
                {"Ultratech Cement Ltd.", null, null, null, null, null, null},
                {"Wipro Ltd.", null, null, null, null, null, null}
            },
            new String [] {
                "Company Name", "Article Sentiment", "Tweet Sentiment", "Message Board Sentiment", "Combined Sentiment", "Time", "Stock Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Results);

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setText("PREDICTION OF THE STOCK MARKET");

        Feedback.setText("Feedback");
        Feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeedbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(Result)
                        .addGap(79, 79, 79)
                        .addComponent(Feedback))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Result)
                    .addComponent(Feedback))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void ResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultActionPerformed
       TestingProgram test = new TestingProgram();
       String direction[]= new String[150]; 
       direction = test.testingprogram();
       
       String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
       
       String dateStamp = new SimpleDateFormat("ddMMYYYY,HH:mm").format(Calendar.getInstance().getTime());
       
       
       
       for(int i=0;i<50;i++)
       {
           Results.setValueAt(direction[i],i ,1);
           if(!(direction[i].equals("-")))
           {
               
                Results.setValueAt(timeStamp,i,5);
                RetrieveStock stock = new RetrieveStock();
                System.out.println(dateStamp);
                String stockprice = stock.getstock(i, dateStamp);
                Results.setValueAt(stockprice,i,6);
           }
           
           Results.setValueAt(direction[i+50],i ,2);
           Results.setValueAt(direction[i+100],i ,3);
           int articles=0,messages=0,tweets=0,combined=0;
           
           if(direction[i].equals("Positive"))
               articles =1;
           else if(direction[i].equals("Negative"))
               articles =-1;
           if(direction[i+50].equals("Positive"))
               tweets =1;
           else if(direction[i+50].equals("Negative"))
               tweets =-1;
           if(direction[i+100].equals("Positive"))
               messages =1;
           else if(direction[i+100].equals("Negative"))
               messages =-1;
           
           combined = (articles*3)+(messages*2)+tweets;
           if(combined>0)
               Results.setValueAt("Positive", i, 4);
           else if(combined<0)
               Results.setValueAt("Negative", i, 4);
          
       }
       
        
        
    }//GEN-LAST:event_ResultActionPerformed

    private void FeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeedbackActionPerformed
        
        Feedback fb= new Feedback();
        
        String dateStamp = new SimpleDateFormat("ddMMYYYY,HH:mm").format(Calendar.getInstance().getTime());
        String date = new SimpleDateFormat("ddMMYYYY").format(Calendar.getInstance().getTime());
        for(int i=0;i<50;i++)
        {
            String str = new String();
            if(Results.getValueAt(i,5).toString().equals(" "));
            else
            {str = Results.getValueAt(i, 5).toString();
            String[] time1 = str.split(":");
            String current = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            String[] time2 = current.split(":");
            int cur = (Integer.parseInt(time2[0])*60+Integer.parseInt(time2[1]));
            int prev = (Integer.parseInt(time1[0])*60+Integer.parseInt(time1[1]));
            if((cur-prev)>=20)
            {
                //retieving the current stockquote using the datestamp
                System.out.println("current time :"+dateStamp);
                RetrieveStock r = new RetrieveStock();
                String stocknow = r.getstock(i, dateStamp);
                //Setting proper format for the date and time (the last updated value)
                String prevtime = date + "," + time1[0] +":"+time1[1];
                System.out.println("previous time :"+prevtime);
                String stockprev = r.getstock(i, prevtime);
                System.out.println("Current stock :"+stocknow+" Previous stock:"+stockprev);
                // Check if the predicition is correct! :)
                String prediction = Results.getValueAt(i, 4).toString();
                if((Double.parseDouble(stockprev)<Double.parseDouble(stocknow)))
                {
                    if(prediction.equals("Positive"))
                        pp++;
                    else 
                        np++;
                
                 }
                else if((Double.parseDouble(stockprev)>Double.parseDouble(stocknow)))
                {
                    if(prediction.equals("Positive"))
                        pn++;
                    else 
                        nn++;
                
                }
                   
                fb.feedback(i,stockprev,stocknow);
            }
            
            JOptionPane.showMessageDialog(rootPane, "PP ="+pp+"\nPN ="+pn+"\nNP ="+np+"\nNN ="+nn);
            
            }
            
        }
        
        
        
    }//GEN-LAST:event_FeedbackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PredictionOfStockMarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PredictionOfStockMarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PredictionOfStockMarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PredictionOfStockMarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PredictionOfStockMarket().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Feedback;
    private javax.swing.JButton Result;
    private javax.swing.JTable Results;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
