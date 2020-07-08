package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.*;
public class bank2 implements ActionListener
	{
	 JFrame f2;
	 JTextField t1,t2,t3,t5;
	 JPasswordField t4;
	 JButton b3,b4,l6;
	 JLabel l1,l2,l3,l4,l5,back;
	 JCheckBox c1;
	FileInputStream fi;
	 Connection cn;
	 PreparedStatement ps;
	 ResultSet rs;
	 int i;
		bank2()
		{	
		 f2=new JFrame("New User");

		 b3=new JButton("Proceed");
		 b4=new JButton("back");

		 l1=new JLabel("name");
		 l2=new JLabel("Address");
		 l3=new JLabel("phone No.");
		 l4=new JLabel("Password");
		 l5=new JLabel("Amount");
		 l6=new JButton("Set profile");
		 c1=new JCheckBox("show pass");
		 t1=new JTextField();
		 t2=new JTextField();
		 t3=new JTextField();
		 t4=new JPasswordField();
		 t5=new JTextField();
		 
		 l1.setFont(new Font("Bold",Font.PLAIN,15));
         l2.setFont(new Font("Bold",Font.PLAIN,15));
         l3.setFont(new Font("Bold",Font.PLAIN,15));
         l4.setFont(new Font("Bold",Font.PLAIN,15));
         l5.setFont(new Font("Bold",Font.PLAIN,15));
         
         l1.setForeground(Color.BLACK);
         l2.setForeground(Color.BLACK);
         l3.setForeground(Color.BLACK);
         l4.setForeground(Color.BLACK);
         l5.setForeground(Color.BLACK);
			ImageIcon im=new ImageIcon("canada3.jpg");
                    Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		            ImageIcon ic=new ImageIcon(modif);
			back=new JLabel("",ic,JLabel.CENTER);
			f2.setLayout(null);
			/////frame 2////
			l1.setBounds(570,340,50,20);
			l2.setBounds(570,380,70,20);
			l3.setBounds(570,420,70,20);
			l4.setBounds(570,460,70,20);
			l5.setBounds(570,500,70,20);
			l6.setBounds(640,540,100,20);

			t1.setBounds(650,340,50,20);
			t2.setBounds(650,380,50,20);
			t3.setBounds(650,420,50,20);
			t4.setBounds(650,460,50,20);
			t5.setBounds(650,500,50,20);
	
			c1.setBounds(720,460,50,20);
		
            b3.setBounds(500,550,100,30);
			b4.setBounds(780,550,100,30);
			back.setBounds(0,0,1366,768);
			f2.add(l1);
			f2.add(l2);
			f2.add(l3);
			f2.add(l4);
			f2.add(l5);
			f2.add(l6);
			f2.add(c1);
			f2.add(t1);
			f2.add(t2);
			f2.add(t3);
			f2.add(t4);
			f2.add(t5);
		
			f2.add(b3);
			f2.add(b4);
			f2.add(back);
			f2.setVisible(true);
			f2.setSize(1366,768);
			c1.addActionListener(this);
			b4.addActionListener(this);
			b3.addActionListener(this);
			l6.addActionListener(this);
			try
			{
			 Class.forName("com.mysql.cj.jdbc.Driver");

			 cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root",null);

			}
			catch(Exception e)
			{
			 System.out.print(e);
			}

		}

        public void actionPerformed(ActionEvent e)
        {
		

		 if(c1.isSelected())
		    {
		     t4.setEchoChar((char)0);

		    }
		 else
		    {
		     t4.setEchoChar('*');
		    }	
			
			
		  if(e.getSource()==b4)
		    {
		     f2.setVisible(false);
			 new bank1();
			}

		  else
		  if(e.getSource()==b3)
			{
				if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getPassword().equals(""))	
				{
				JOptionPane.showMessageDialog(f2,"fill all entries","info Box",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{	
				 String s1=t1.getText();
				 String s2=t2.getText();
				 String s3=t3.getText();
				 String s4=String.valueOf(t4.getPassword());
				 String s5=t5.getText();
				 int coun=Integer.parseInt(s5);
				 int start=0;
					if(coun>500)
					{
						try
						{
							
							ps=cn.prepareStatement("select * from reco where phone=?");
							ps.setInt(1,Integer.parseInt(s3));
							rs=ps.executeQuery();
							while(rs.next())
							{

							 start=1;
							}
							rs.close();
							ps.close();

							if(start==0)
							{
								
							 String s="";
							 ps=cn.prepareStatement("insert into reco(name,addr,phone,pass,amount,status,profile) values(?,?,?,?,?,?,?)");
			
							 ps.setString(1,s1);
							 ps.setString(2,s2);
							 ps.setInt(3,Integer.parseInt(s3));
							 ps.setString(4,s4);
							 ps.setInt(5,Integer.parseInt(s5));
							 ps.setString(6,"activate");
							 ps.setBinaryStream(7,fi);
			
							 ps.executeUpdate();
							 ps.close();
							 ps=cn.prepareStatement("select count(*) from reco");

							 rs=ps.executeQuery();
							 
								while(rs.next())
								{

								 s=rs.getString(1);
								}
								 JOptionPane.showMessageDialog(f2,"your account no. "+s+"created"+" \n name "+s1,"info Box",JOptionPane.INFORMATION_MESSAGE);
								 rs.close();
								 ps.close();
							}
							else
							{
							 JOptionPane.showMessageDialog(f2,"this no. is already used ","info Box",JOptionPane.INFORMATION_MESSAGE);

							}


						}

						catch(Exception ee)
						{
						 System.out.print(ee);
						}
					}
					else
					{
					 JOptionPane.showMessageDialog(f2,"amount will be greater then 500","info Box",JOptionPane.ERROR_MESSAGE);
					}

				}
            }
			else
			if(e.getSource()==l6)
			{
				try{
			JFileChooser jc=new JFileChooser();
            jc.showOpenDialog(f2);
			fi=new FileInputStream(jc.getSelectedFile());
				}
				catch(Exception ee)
				{
				}
			}
		}


		public static void main(String ar[])
		{

		 bank2 k2=new bank2();
		}
	}