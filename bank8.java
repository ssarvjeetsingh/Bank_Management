package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class bank8 implements ActionListener
{
JFrame f8;
JButton b3,b4,pic;
JLabel l1,l2,l3,l4,l5,l6,l7,back;
Connection cn;
PreparedStatement ps;
ResultSet rs;
JTextArea ta;
int temp1,count=1;
bank8()
{
f8=new JFrame("passbook");

b3=new JButton("Logout");
b4=new JButton("Back");
pic=new JButton(bankproject.bank3.img2);

temp1=bankproject.bank3.temp;
l1=new JLabel("Account no.");
l2=new JLabel("Available Balance");
l3=new JLabel(" "+temp1);
l5=new JLabel("History");
l6=new JLabel("last AMT");
l7=new JLabel(String.valueOf(bankproject.bank3.temp2));
ta=new JTextArea();

ImageIcon im=new ImageIcon("canada1.jpeg");
		 Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
         
		 back=new JLabel("",ic,JLabel.CENTER);

         l1.setFont(new Font("Bold",Font.PLAIN,15));
         l2.setFont(new Font("Bold",Font.PLAIN,15));
         l3.setFont(new Font("Bold",Font.PLAIN,15));
         
         l5.setFont(new Font("Bold",Font.PLAIN,15));
         l6.setFont(new Font("Bold",Font.PLAIN,15));
         l7.setFont(new Font("Bold",Font.PLAIN,15));


l1.setForeground(Color.BLACK);
l2.setForeground(Color.BLACK);
l3.setForeground(Color.BLACK);

l5.setForeground(Color.BLACK);
l6.setForeground(Color.BLACK);
l7.setForeground(Color.BLACK);
f8.setLayout(null);
/////frame 2////
l1.setBounds(40,40,100,20);
l2.setBounds(40,70,100,20);
l3.setBounds(150,40,40,20);
l5.setBounds(40,150,80,40);
l6.setBounds(40,130,80,40);
l7.setBounds(140,130,80,40);

b3.setBounds(50,100,100,20);
b4.setBounds(170,100,100,20);
pic.setBounds(300,10,100,100);
ta.setBounds(30,180,300,180);
back.setBounds(0,0,1366,768);
f8.add(l1);
f8.add(l2);
f8.add(l3);
f8.add(l5);
f8.add(l6);
f8.add(l7);
f8.add(b3);
f8.add(b4);
f8.add(ta);

f8.add(pic);

try
{
String s="";
int start=0;
Class.forName("com.mysql.cj.jdbc.Driver");

cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root",null);

temp1=bankproject.bank3.temp;
ps=cn.prepareStatement("select amount from reco where account=?");
ps.setInt(1,temp1);
rs=ps.executeQuery();
if(rs.next())
{
l4=new JLabel(" "+rs.getString(1));
l4.setBounds(150,70,40,20);
}
rs.close();
ps.close();
ps=cn.prepareStatement("select * from trans where frome=?");

ps.setInt(1,temp1);
rs=ps.executeQuery();

while(rs.next())
{
start=1;
Timestamp ts=rs.getTimestamp(5);

s="\n"+rs.getString(2)+" "+rs.getString(3)+"   "+"Rs"+rs.getString(4)+"/-"+"  "+ts+s;
}
if(start==1)
{
ta.setText(s);

}
else
{
ta.setText("no history");
}
rs.close();
ps.close();
}
catch(Exception e)
{
System.out.print(e);
}
f8.add(l4);
l4.setForeground(Color.BLACK);
l4.setFont(new Font("Bold",Font.PLAIN,15));
f8.add(back);
f8.setVisible(true);
f8.setSize(1366,768);
b4.addActionListener(this);
b3.addActionListener(this);
pic.addActionListener(this);
}


public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b4)
{
f8.setVisible(false);
new bank4();
}
else
if(e.getSource()==b3)
{
int v=JOptionPane.showConfirmDialog(f8,"Do you want to logout","confirm",JOptionPane.OK_CANCEL_OPTION);
if(v==JOptionPane.OK_OPTION)
{

f8.setVisible(false);
new bank3();

}

}
else
if(e.getSource()==pic)
	{
		
		Image newim=bankproject.bank3.img.getImage();
	    Image modi=newim.getScaledInstance(500,500,java.awt.Image.SCALE_SMOOTH);
		ImageIcon ic=new ImageIcon(modi);
		JOptionPane.showMessageDialog(f8,"","profileBox",JOptionPane.INFORMATION_MESSAGE,ic);
		f8.revalidate();
		
	}

}
public static void main(String ar[])
{

new bank8();

}

}