package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class bank9 implements ActionListener
{

	JFrame f1;
	JLabel l1,l2,l3,back;
	JTextField t1,t2;
	JPasswordField t3;
	JButton b1,b2,b3;
	JCheckBox cb;
	PreparedStatement ps;
	Connection co;
	ResultSet rs;
	ImageIcon im;

bank9()
{

	f1=new JFrame("status");

	l1=new JLabel("holder name");
	l2=new JLabel("acc no.");
	l3=new JLabel("password");
	
	t1=new JTextField();
	t2=new JTextField();
	t3=new JPasswordField();
	cb=new JCheckBox("show");
	b1=new JButton("activate");
	b2=new JButton("Deactivate");
	b3=new JButton("<");
	im=new ImageIcon("canada3.jpg");
	 Image newim=im.getImage();
	 Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
	 ImageIcon ic=new ImageIcon(modif);
         
	back=new JLabel("",ic,JLabel.CENTER);
    l1.setFont(new Font("Bold",Font.PLAIN,15));
    l2.setFont(new Font("Bold",Font.PLAIN,15));
    l3.setFont(new Font("Bold",Font.PLAIN,15));
         

	l1.setForeground(Color.BLACK);
	l2.setForeground(Color.BLACK);
	l3.setForeground(Color.BLACK);
Container c=f1.getContentPane();
c.setLayout(null);
	
l1.setBounds(570,350,100,20);
l2.setBounds(570,390,70,20);
l3.setBounds(570,430,70,20);

t1.setBounds(650,350,50,20);
t2.setBounds(650,390,50,20);
t3.setBounds(650,430,50,20);
cb.setBounds(720,430,50,20);

b1.setBounds(550,470,100,30);
b2.setBounds(750,470,100,30);
b3.setBounds(680,510,50,30);

back.setBounds(0,0,1366,768);
c.add(l1);
c.add(l2);
c.add(l3);

c.add(t1);
c.add(t2);
c.add(t3);
c.add(cb);

c.add(b1);
c.add(b2);
c.add(b3);
c.add(back);
f1.setVisible(true);
f1.setSize(1366,768);

try
{
Class.forName("com.mysql.cj.jdbc.Driver");

co=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root",null);

}
catch(Exception e)
{
System.out.print(e);
}

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
cb.addActionListener(this);

}

public void actionPerformed(ActionEvent e)
{


if(cb.isSelected())
{
t3.setEchoChar((char)0);

}
else
{
t3.setEchoChar('*');
}

	
if(e.getSource()==b1)
{
	
	try
	{
		String s1=t2.getText();
		ps=co.prepareStatement("select * from reco where account=?");

		ps.setInt(1,Integer.parseInt(s1));
		rs=ps.executeQuery();
		if(rs.next())
		{
	
		

	
			if((rs.getString(1)).equals(t2.getText())&&(rs.getString(2)).equals(t1.getText())&&(rs.getString(5)).equals(String.valueOf(t3.getPassword())))
			{
				if((rs.getString(7)).equals("activate"))
				{
					JOptionPane.showMessageDialog(f1,"already activated","info box",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					ps.close();	
					int v=JOptionPane.showConfirmDialog(f1,"Do you want to activate your acc","confirm",JOptionPane.OK_CANCEL_OPTION);
					if(v==JOptionPane.OK_OPTION)
					{

						ps=co.prepareStatement("update reco set status=? where account=?");	
	
						ps.setString(1,"activate");
							
						ps.setInt(2,Integer.parseInt(t2.getText()));		
							
						ps.executeUpdate();	
						JOptionPane.showMessageDialog(f1,"successfully activate ","cofirm",JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
			else
			{
				JOptionPane.showMessageDialog(f1,"something wrong","info",JOptionPane.ERROR_MESSAGE);
			}

		}

		else
		{
			JOptionPane.showMessageDialog(f1,"invalid entry","cofirm",JOptionPane.INFORMATION_MESSAGE);
		}
			ps.close();
	}
	catch(SQLException ee)
	{
		System.out.print(ee);
	}

}


else
if(e.getSource()==b2)
{
	
try
{
String s1=t2.getText();
ps=co.prepareStatement("select * from reco where account=?");

ps.setInt(1,Integer.parseInt(s1));
rs=ps.executeQuery();
	if(rs.next())
	{
	
	
		if((rs.getString(1)).equals(t2.getText())&&(rs.getString(2)).equals(t1.getText())&&(rs.getString(5)).equals(String.valueOf(t3.getPassword())))
		{

			if((rs.getString(7)).equals("deactivate"))
			{
				JOptionPane.showMessageDialog(f1,"already deactivated","info box",JOptionPane.INFORMATION_MESSAGE);
			}

			else
			{
				ps.close();	
				int v=JOptionPane.showConfirmDialog(f1,"Do you want to deactivate your acc","confirm",JOptionPane.OK_CANCEL_OPTION);
				if(v==JOptionPane.OK_OPTION)
				{

					ps=co.prepareStatement("update reco set status=? where account=?");	
	
					ps.setString(1,"deactivate");
	
					ps.setInt(2,Integer.parseInt(t2.getText()));		
	
					ps.executeUpdate();	
					JOptionPane.showMessageDialog(f1,"successfully deactivate ","cofirm",JOptionPane.INFORMATION_MESSAGE);
			
		       }
		
			}    
		}
		else
		{
			JOptionPane.showMessageDialog(f1,"something wrong","info",JOptionPane.ERROR_MESSAGE);
		}

	}

	else
	{
		JOptionPane.showMessageDialog(f1,"invalid entry","cofirm",JOptionPane.INFORMATION_MESSAGE);
	}
		ps.close();
}
catch(SQLException ee)
{
System.out.print(ee);
}

}

else
if(e.getSource()==b3)
{
f1.setVisible(false);
new bank1();
f1.revalidate();
}
	
	
	
}


public static void main(String ar[])
{

new bank9();
}	
	




}


