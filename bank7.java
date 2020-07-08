package bankproject;
 
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class bank7 implements ActionListener
{
JFrame f7;
JTextField t9,t10;
JButton b11,b12;
JLabel l9,l10,back;
Connection co;
PreparedStatement ps;
ResultSet rs;
int temp1;

bank7()
{
f7=new JFrame("transfer");

b11=new JButton("Transfer");
b12=new JButton("<");

l9=new JLabel("To");
l10=new JLabel("Amount");

t9=new JTextField();
t10=new JTextField();
ImageIcon im=new ImageIcon("canada3.jpg");
		 Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
         
		 back=new JLabel("",ic,JLabel.CENTER);
l9.setForeground(Color.BLACK);
l10.setForeground(Color.BLACK);
f7.setLayout(null);

l9.setBounds(570,340,40,20);
t9.setBounds(650,340,50,20);
l10.setBounds(570,380,70,20);
t10.setBounds(650,380,50,20);

b11.setBounds(500,450,80,30);
b12.setBounds(750,450,60,30);
back.setBounds(250,0,800,800);
f7.add(l9);
f7.add(l10);
f7.add(t9);
f7.add(t10);
f7.add(b11);
f7.add(b12);
f7.add(back);
f7.setVisible(true);
f7.setSize(1366,768);
b12.addActionListener(this);
b11.addActionListener(this);
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
	


if(e.getSource()==b12)
	{

		f7.setVisible(false);
		new bank4();
	}

else
	if(e.getSource()==b11)
	{
	
		if(t9.getText().equals("")||t10.getText().equals(""))
		{
			JOptionPane.showMessageDialog(f7,"fill all entries","info box",JOptionPane.ERROR_MESSAGE);
		}

  else
	{
	 if(bankproject.bank3.temp==Integer.parseInt(t9.getText()))
	 {
		JOptionPane.showMessageDialog(f7,"wrong opration","info box",JOptionPane.ERROR_MESSAGE);
	 }	
	 else
	 {
	  if(bankproject.bank3.bal>Integer.parseInt(t10.getText()))
	  {
		    int start=0;
		    try
			{

				ps=co.prepareStatement("select * from reco where account=?");
				ps.setInt(1,Integer.parseInt(t9.getText()));
				rs=ps.executeQuery();

				    if(rs.next())
					{
	
						    if((rs.getString(7)).equals("deactivate"))
							{
								JOptionPane.showMessageDialog(f7,"Entered acco is not activate","info box",JOptionPane.ERROR_MESSAGE);
							}
							
						    else
							{	
								    ps.close();
								    rs.close();
								    int v=JOptionPane.showConfirmDialog(f7,"Do you want to transfer","confirm",JOptionPane.OK_CANCEL_OPTION);
								    if(v==JOptionPane.OK_OPTION)
									{
										ps=co.prepareStatement("update reco set amount=(amount-?) where account=?");

										bankproject.bank3.bal=bankproject.bank3.bal-Integer.parseInt(t10.getText());

										String temp2=String.valueOf(bankproject.bank3.bal);
										temp1=bankproject.bank3.temp;
										ps.setInt(1,Integer.parseInt(t10.getText()));
										ps.setInt(2,temp1);

										ps.executeUpdate();
										ps.close();

										ps=co.prepareStatement("update reco set amount=(amount+?) where account=?");

										ps.setInt(1,Integer.parseInt(t10.getText()));
										ps.setInt(2,Integer.parseInt(t9.getText()));

										ps.executeUpdate();
										ps.close();

										ps=co.prepareStatement("insert into trans(frome,ttype,too,amount) values(?,?,?,?)");

										ps.setInt(1,temp1);
										ps.setString(2,"Transfer To");
										ps.setInt(3,Integer.parseInt(t9.getText()));
										ps.setInt(4,Integer.parseInt(t10.getText()));

										ps.executeUpdate();

										ps.setInt(1,Integer.parseInt(t9.getText()));
										ps.setString(2,"Recieve from");
										ps.setInt(3,temp1);
										ps.setInt(4,Integer.parseInt(t10.getText()));
										ps.executeUpdate();
										ps.close();
										JOptionPane.showMessageDialog(f7,"successfully Transfer" +"\n your updated balance is "+temp2,"info box",JOptionPane.INFORMATION_MESSAGE);

									}
							}
					}
				    else
					{
						JOptionPane.showMessageDialog(f7,"Invalid acco no.","info box",JOptionPane.ERROR_MESSAGE);
					}
			}
			catch(SQLException ee)
			{
				System.out.print(ee);
			}
	  }

	else
	{
		JOptionPane.showMessageDialog(f7,"insufficient balance","your balance",JOptionPane.ERROR_MESSAGE);
		t10.setText("");

	}
  }
 }//else end///

}///button b11 end///




}

public static void main(String ar[])
{
bank7 k7=new bank7();
}
}