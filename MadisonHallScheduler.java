import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class MadisonHallScheduler extends JFrame implements ActionListener {
	private int width = 1000;
	private int height = 400;
	JTextField[] fileFields; 
	JButton[] b;
	Color background;

	public MadisonHallScheduler() {
		super("MadisonHallScheduler");
		setSize(width, height);
		background = new Color(255, 250, 245); // 230, 230, 250 <- off white //255, 250, 245 <- slight lavender 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
			//panel1.setMaximumSize(new Dimension(width, height));
			panel1.setLayout(new GridLayout(3,1));
			JLabel title = new JLabel("Cavs in The Classroom Scheduler");
			title.setFont(new Font("Times New Roman", Font.BOLD, 20));
			title.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(title);
			JLabel message = new JLabel("<HTML><center>Please download all of your Excel/Google Drive Sheets as TSV files! "
					+ "<br> For each sheet below, please select the path of the files on your computer. </center></HTML>");
			message.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			message.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(message);
			panel1.setBackground(background);
		add(panel1,BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3,5, 20, 20));
			panel2.setMaximumSize(new Dimension(width*1/3, height));
	
			JLabel[] messageFile = new JLabel[3];
			fileFields = new JTextField[3];
			b = new JButton[3];
	
			messageFile[0] = new JLabel("Teachers: ");
			messageFile[1] = new JLabel("Curry & New Volunteers: ");
			messageFile[2] = new JLabel("Returning Volunteers: ");
	
			for(int i=0; i<3; i++) {
				fileFields[i] = new JTextField("");
				fileFields[i].setBorder(new LineBorder(Color.BLACK, 2));
				fileFields[i].setEditable(false);
				b[i] = new JButton("Select File " + (i+1));
				b[i].addActionListener(this);
				b[i].setBackground(new Color(66,165, 245));
				b[i].setFont(new Font("Century Gothic", Font.PLAIN, 18));
				b[i].setForeground(Color.WHITE);
				messageFile[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
				messageFile[i].setHorizontalAlignment(JLabel.CENTER);
				panel2.add(messageFile[i]);
				panel2.add(fileFields[i]);
				panel2.add(b[i]);
			}
			panel2.setBackground(background);
		add(panel2,BorderLayout.CENTER);

		JPanel panel3 = new JPanel();
			panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel3.setMaximumSize(new Dimension(50, 50));
			panel3.setLayout(new GridLayout(1,2, 10,10));
	
			JLabel messageSubmit = new JLabel("<HTML><U>Hit Submit once ALL file paths have been entered to run the program:</U><HTML>"); //"<HTML><U>YOUR TEXT HERE</U></HTML>"
			messageSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			messageSubmit.setHorizontalAlignment(JLabel.CENTER);
			panel3.add(messageSubmit);
	
			JButton submit = new JButton("Submit"); 
			submit.addActionListener(this);
			submit.setBackground(new Color(38, 166, 91));  // Light pink: 244, 121, 131
			submit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			submit.setForeground(Color.WHITE);
			panel3.add(submit);
			panel3.setBackground(background);
		add(panel3,BorderLayout.SOUTH);
		this.pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand(); 
		if (action.equals("Select File 1")) { 
			setSelectedField(0);
		}
		else if (action.equals("Select File 2")) { 
			setSelectedField(1);
		}
		else if (action.equals("Select File 3")) { 
			setSelectedField(2);
		}
	}
	public void setSelectedField(int i) {
		// create an object of JFileChooser class 
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

		// invoke the showsSaveDialog function to show the save dialog 
		int r = j.showSaveDialog(null); 

		// if the user selects a file 
		if (r == JFileChooser.APPROVE_OPTION) 

		{ 
			// set the label to the path of the selected file 
			fileFields[i].setText(j.getSelectedFile().getAbsolutePath()); 
		} 
		// if the user cancelled the operation 
		else
			JOptionPane.showMessageDialog(null,"User cancelled file input");
	}
	public static void main(String[] args) throws IOException
	{
		MadisonHallScheduler obj = new MadisonHallScheduler();
		obj.setVisible(true);
	}
}
