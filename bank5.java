package bankproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class bank5 implements ActionListener
	{
	JFrame f5;
	JTextField t7,t8;
	JButton b9,b10;
	JLabel l7,l8,back;
	PreparedStatement ps;
	Connection co;
	int temp1;

		bank5()
		{
		 f5=new JFrame("Deposite");
		 b9=new JButton("Deposite");
		 b10=new JButton("<");
 
  		 l7=new JLabel("Amount");

		 t7=new JTextField();
		 ImageIcon im=new ImageIcon("canada3.jpg");
		 Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
         
		 back=new JLabel("",ic,JLabel.CENTER);
        l7.setForeground(Color.BLACK);

		 f5.setLayout(null);


		/////Frame 5///////////
		 l7.setBounds(570,380,50,20);
		 t7.setBounds(650,380,50,20);
		 b9.setBounds(500,450,100,30);
		 b10.setBounds(750,450,60,30);
		 back.setBounds(0,0,1366,768);

		 f5.add(l7);
		 f5.add(t7);
		 f5.add(b9);
		 f5.add(b10);
		 f5.add(back);
		 f5.setVisible(true);
		 f5.setSize(1366,768);
		 b10.addActionListener(this);
		 b9.addActionListener(this);
			try
			{
		     Class.forName("com.mysql.cj.jdbc.Driver"); 

			 co=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root",null);

			}
			catch(Exception e)
			{
		     System.out.print(e);
			}

		}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==b10)
			{
			 f5.setVisible(false);
			 new bank4();
			}
			else
			if(e.getSource()==b9)
			{

				if(t7.getText().equals(""))
				{
				 JOptionPane.showMessageDialog(f5,"fill all etries","info",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
				
					try
					{

					 ps=co.prepareStatement("update reco set amount=(amount+?) where account=?");
					 bankproject.bank3.bal=bankproject.bank3.bal+Integer.parseInt(t7.getText());
					 temp1=bankproject.bank3.temp;
					 ps.setInt(1,Integer.parseInt(t7.getText()));
					 ps.setInt(2,temp1);

					 ps.execute();
					 ps.close(); 

					 ps=co.prepareStatement("insert into trans(frome,ttype,too,amount) values(?,?,?,?)");

					 ps.setInt(1,temp1);
					 ps.setString(2,"deposite");
					 ps.setString(3," ");
					 ps.setInt(4,Integer.parseInt(t7.getText()));

					 ps.executeUpdate();

					 ps.close();
					 JOptionPane.showMessageDialog(f5,"successfully deposite","your balance",JOptionPane.INFORMATION_MESSAGE);
					 t7.setText("");
					}
					catch(SQLException ee)
					{
					 System.out.print(ee);
					}

			    }
			}
		}
		public static void main(String ar[])
		{
		 bank5 k5=new bank5();
		}
	}