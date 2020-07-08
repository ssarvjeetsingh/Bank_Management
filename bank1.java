package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class bank1 implements ActionListener
{
	JFrame f1;
	JButton b1,b2,b3;
 	JLabel back;
 	int i;
	 
     
 	bank1()
        {
     		f1=new JFrame("YES BANK");
      		b1=new JButton("Sign up");
       		b2=new JButton("Sign in");
	  	b3=new JButton("Activate/Deactivate Acc");
       
       		ImageIcon im=new ImageIcon("canada3.jpg");

        	Image newim=im.getImage();
	    	Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		ImageIcon ic=new ImageIcon(modif);
        	back=new JLabel("",ic,JLabel.CENTER);
       		f1.setLayout(null);
         
       		b1.setBounds(570,400,120,30);
	  	b2.setBounds(900,400,120,30);
	  	b3.setBounds(720,400,150,30);

		 
	  	back.setBounds(0,0,1366,768);
      		f1.add(b1);
        	f1.add(b2);
        	f1.add(b3);
	       
       		f1.add(back);

        	f1.setVisible(true);
        	f1.setSize(1366,768);
        	b1.addActionListener(this);
		b2.addActionListener(this);
        	b3.addActionListener(this);
    	}
  	public void actionPerformed(ActionEvent e)
    	{
		if(e.getSource()==b1)
        	{
           		f1.setVisible(false);
			new bank2();
	    	}
	    	else 
		if(e.getSource()==b2)
        	{
	     		f1.setVisible(false);
		 	new bank3();
		}
		else 
		if(e.getSource()==b3)
        	{
          		f1.setVisible(false);
          		new bank9();
        	}
	}

    	public static void main(String ar[])
	{
		bank1 k=new bank1();
	}
}