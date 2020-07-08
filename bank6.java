package bankproject;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bankproject.bank4;
import java.sql.*;
class bank6 implements ActionListener
	{
		JFrame f6;
		JTextField t8;
		JButton b10,b11;
		JLabel l8,back;
		Connection co;
		PreparedStatement ps;
		int temp1;
		bank6()
		{

			f6=new JFrame("Withdraw");

			b10=new JButton("withdraw");
			b11=new JButton("<");

			l8=new JLabel("Amount");

			t8=new JTextField();
			ImageIcon im=new ImageIcon("canada3.jpg");
		 Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
         
		 back=new JLabel("",ic,JLabel.CENTER);
         l8.setForeground(Color.BLACK);

			f6.setLayout(null);

			///frame 6////////
			l8.setBounds(570,380,50,20);
			t8.setBounds(650,380,50,20);
			b10.setBounds(500,450,100,20);
			b11.setBounds(750,450,60,20);
			back.setBounds(0,0,1366,768);
			f6.add(l8);
			f6.add(t8);
			f6.add(b10);
			f6.add(b11);
			f6.add(back);

			f6.setVisible(true);
			f6.setSize(1366,768);

			b11.addActionListener(this);
			b10.addActionListener(this);
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
			if(e.getSource()==b11)
			{
				f6.setVisible(false);
				new bank4();
			}

			else
			if(e.getSource()==b10)
			{
	
				if(t8.getText().equals(""))
				{
					JOptionPane.showMessageDialog(f6,"fill all entries","info",JOptionPane.ERROR_MESSAGE);
				}

				else
				{
					if(bankproject.bank3.bal>Integer.parseInt(t8.getText()))
					{
						try
						{

							ps=co.prepareStatement("update reco set amount=(amount-?) where account=?");

							temp1=bankproject.bank3.temp;
							ps.setInt(1,Integer.parseInt(t8.getText()));
							ps.setInt(2,temp1);

							ps.execute();
							ps.close();
							bankproject.bank3.bal=bankproject.bank3.bal-Integer.parseInt(t8.getText());
							ps=co.prepareStatement("insert into trans(frome,ttype,too,amount) values(?,?,?,?)");

							ps.setInt(1,temp1);
							ps.setString(2,"withdraw");
							ps.setString(3," ");
							ps.setInt(4,Integer.parseInt(t8.getText()));
							ps.executeUpdate();

							ps.close();

							JOptionPane.showMessageDialog(f6,"successfully withdwal","your balance",JOptionPane.INFORMATION_MESSAGE);
							t8.setText("");

						}
						catch(SQLException ee)
						{
							System.out.print(ee);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f6,"insufficient balance","your balance",JOptionPane.ERROR_MESSAGE);
						t8.setText("");
					}

				}

			}			




		}
		public static void main(String ar[])
		{		
			bank6 k6=new bank6();
		}
	}