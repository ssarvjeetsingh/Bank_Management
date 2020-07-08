package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class bank4 implements ActionListener
	{
	 JFrame f4;
	 JButton b5,b6,b7,b8,b9;
	 JLabel back;
	 bank4()
		{
		 f4=new JFrame("option"); 

		 b5=new JButton("Depo");
		 b6=new JButton("with");
		 b7=new JButton("Trans");
		 b8=new JButton("check bal");
		 b9=new JButton("<");


		 ImageIcon im=new ImageIcon("canada3.jpg");
		  Image newim=im.getImage();
	                Image modif=newim.getScaledInstance(1366,768,java.awt.Image.SCALE_SMOOTH);
		        ImageIcon ic=new ImageIcon(modif);
        
		 back=new JLabel("",ic,JLabel.CENTER);


		 f4.setLayout(null);
		/////////////frame 4////////////

		 b5.setBounds(570,340,100,30);
		 b6.setBounds(570,380,100,30);
		 b7.setBounds(570,420,100,30);
		 b8.setBounds(570,460,100,30);
		 b9.setBounds(480,340,60,20);
 

		 back.setBounds(0,0,1366,768);
		 f4.add(b5);
		 f4.add(b6);
		 f4.add(b7);
		 f4.add(b8);
		 f4.add(b9);


		 f4.add(back);

		 f4.setVisible(true);
		 f4.setSize(1366,768);
		 b5.addActionListener(this);
		 b6.addActionListener(this);
		 b7.addActionListener(this);
		 b8.addActionListener(this);
		 b9.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==b5)
			{
			 f4.setVisible(false);
			new bank5();
			}
			else
			if(e.getSource()==b6)
			{
			 f4.setVisible(false);
			 new bank6();
			}
			else
			if(e.getSource()==b7)
			{
			 f4.setVisible(false);
			 new bank7();
			}
			else
			if(e.getSource()==b8)
			{
			 f4.setVisible(false);
			 new bank8();
			}

			else
			 if(e.getSource()==b9)
			{
			 f4.setVisible(false);
			 new bank3();
			}

		}
		public static void main(String ar[])
		{
		 bank4 k4=new bank4();
		}
	}
