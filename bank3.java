package bankproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;


///import bankproject.bank5;
public class bank3 implements ActionListener
	{
	 static int temp,temp2,bal;
	 static String status;
	 JFrame f3;
	 JTextField t5;
	 JPasswordField t6;
	 JCheckBox cb;
	 JButton b4,b5;
	 JLabel l5,l6,back;
	 Connection co;
	 PreparedStatement ps;
	 ResultSet rs;
	 Blob bo;
	 static ImageIcon img,img2;

		bank3()
		{

		 f3=new JFrame("Login");



		 b4=new JButton("signin");
		 b5=new JButton("back");

		 l5=new JLabel("Account no.");
		 l6=new JLabel("Password");
		 cb=new JCheckBox("show pass");
		 ImageIcon im=new ImageIcon("canada3.jpg");
		 Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
                back=new JLabel("",ic,JLabel.CENTER);

		 t5=new JTextField();
		 t6=new JPasswordField();
		l5.setForeground(Color.BLACK);
        l6.setForeground(Color.BLACK);

		 f3.setLayout(null);

		//frame 3///
		 l5.setBounds(570,360,70,20);
		 l6.setBounds(570,400,60,20);

		 t5.setBounds(650,360,60,20);
		 t6.setBounds(650,400,60,20);
		 cb.setBounds(730,400,80,20);
		 b4.setBounds(550,470,80,30);
		 b5.setBounds(750,470,80,30);
		 back.setBounds(0,0,1366,768); 
	

		 f3.add(l5);
		 f3.add(l6);
		 f3.add(t5);
		 f3.add(t6);
		 f3.add(cb);
		 f3.add(b4);
		 f3.add(b5);
		 f3.add(back);
		 
		 f3.setVisible(true);
		 f3.setSize(1366,768);
		 b4.addActionListener(this);
		 b5.addActionListener(this);
		 cb.addActionListener(this);
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
	
			if(cb.isSelected())
			{
			 t6.setEchoChar((char)0);

			}
			else
			{
			 t6.setEchoChar('*');
			}	
	
			if(e.getSource()==b4)
			{
			 int start=0;

				if(t5.getText().equals("")||t6.getPassword().equals(""))
				{
				 JOptionPane.showMessageDialog(f3,"fill all entries","info box",JOptionPane.INFORMATION_MESSAGE);
				}

				else
				{
					try
					{
					 String s1=t5.getText();
					 ps=co.prepareStatement("select * from reco where account=?");

					 ps.setInt(1,Integer.parseInt(s1));
					 rs=ps.executeQuery();
						if(rs.next())
						{
						 start=1;
						 temp=Integer.parseInt(s1);
						 bal=rs.getInt(6);
						 status=rs.getString(7);
						 //bo=rs.getBlob(8);
							byte b[]=rs.getBytes("profile");
							img=new ImageIcon(b);
		                    Image modi=img.getImage();
                            Image newim=modi.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);							
	                        img2=new ImageIcon(newim);	
							
							


						}

							if(start==1)	
							{
	
	
							    if((rs.getString(5)).equals(String.valueOf(t6.getPassword())))
								{
									if(status.equals("deactivate"))
									{
										JOptionPane.showMessageDialog(f3,"this acc is deactivated","info box",JOptionPane.INFORMATION_MESSAGE);
									}
									else
									{
										JOptionPane.showMessageDialog(f3,"successfully login","cofirm",JOptionPane.INFORMATION_MESSAGE);
										temp2=rs.getInt(6);
										f3.setVisible(false);
										new bank4();

									}
								}
								else
								{
								 JOptionPane.showMessageDialog(f3,"wrongpass","info",JOptionPane.ERROR_MESSAGE);
								}

							}
							else
							{
							 JOptionPane.showMessageDialog(f3,"invalid accountno.","cofirm",JOptionPane.INFORMATION_MESSAGE);
							}
						
							ps.close();
					}
					catch(Exception ee)
					{
					 System.out.print(ee);
					}

				}
			}
			else
			if(e.getSource()==b5)
			{
			 f3.setVisible(false);
			 new bank1();
			 f3.revalidate();
			}


		}

		public static void main(String ar[])
		{
		 bank3 k3=new bank3();
		}
	}