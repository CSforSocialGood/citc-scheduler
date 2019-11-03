import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JTextField fileField1; 
	JTextField fileField2;
	JTextField fileField3;

	public MadisonHallScheduler() {
		super("MadisonHallScheduler");
		setSize(width, height);
		this.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
			//panel1.setMaximumSize(new Dimension(width, height));
			panel1.setLayout(new GridLayout(3,1));
			JLabel title = new JLabel("Cavs in The Classroom Scheduler");
			title.setFont(new Font("Times New Roman", Font.BOLD, 20));
			title.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(title);
			JLabel message = new JLabel("Please download all of your Excel/Google Drive Sheets as TSV files! "
					+ "\n For each sheet below, please select the path of the files on your computer.");
			message.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			message.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(message);
			JLabel message1 = new JLabel("For each sheet below, please select the path of the files on your computer.");
			message1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			message1.setHorizontalAlignment(JTextField.CENTER);
			panel1.add(message1);
			panel1.setBackground(new Color(255,255,255));
		add(panel1,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3,5, 20, 20));
			panel2.setBackground(Color.WHITE);
			panel2.setMaximumSize(new Dimension(width*1/3, height));
			JLabel messageFile1 = new JLabel("Teachers: ");
			messageFile1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			messageFile1.setHorizontalAlignment(JLabel.CENTER);
			
			JButton b1 = new JButton("Select File 1");
			b1.addActionListener(this);
			
			JLabel messageFile2 = new JLabel("Curry & New Volunteers: ");
			messageFile2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			messageFile2.setHorizontalAlignment(JLabel.CENTER);
			
			JButton b2 = new JButton("Select File 2");
			b2.addActionListener(this);
			
			JLabel messageFile3 = new JLabel("Returning Volunteers: ");
			messageFile3.setFont(new Font("Times New Roman", Font.BOLD, 20));
			messageFile3.setHorizontalAlignment(JLabel.CENTER);
			
			JButton b3 = new JButton("Select File 3");
			b3.addActionListener(this);
			
			fileField1 = new JTextField("");
			fileField1.setBorder(new LineBorder(Color.RED, 2));
			fileField1.setEditable(false);
			fileField2 = new JTextField("");
			fileField2.setBorder(new LineBorder(Color.BLUE, 2));
			fileField2.setEditable(false);
			fileField3 = new JTextField("");
			fileField3.setBorder(new LineBorder(Color.BLACK, 2));
			fileField3.setEditable(false);
			panel2.add(messageFile1);
			panel2.add(fileField1);
			panel2.add(b1);
			panel2.add(messageFile2);
			panel2.add(fileField2);
			panel2.add(b2);
			panel2.add(messageFile3);
			panel2.add(fileField3);
			panel2.add(b3);
		add(panel2,BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
			panel3.setMaximumSize(new Dimension(50, 50));
			panel3.setLayout(new GridLayout(1,2, 10,10));
			panel3.setBackground(Color.WHITE);
			
			JLabel messageSubmit = new JLabel("Hit Submit once ALL file paths have been entered to run the program:");
			messageSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			messageSubmit.setHorizontalAlignment(JLabel.CENTER);
			panel3.add(messageSubmit);
			
			JButton submit = new JButton("Submit"); 
			submit.addActionListener(this);
			panel3.add(submit);
		add(panel3,BorderLayout.SOUTH);
		this.pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand(); 
		if (action.equals("Select File 1")) { 
			// create an object of JFileChooser class 
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

			// invoke the showsSaveDialog function to show the save dialog 
			int r = j.showSaveDialog(null); 

			// if the user selects a file 
			if (r == JFileChooser.APPROVE_OPTION) 

			{ 
				// set the label to the path of the selected file 
				fileField1.setText(j.getSelectedFile().getAbsolutePath()); 
			} 
			// if the user cancelled the operation 
			else
				JOptionPane.showMessageDialog(null,"User cancelled file input");
		}
		else if (action.equals("Select File 2")) { 
			// create an object of JFileChooser class 
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

			// invoke the showsSaveDialog function to show the save dialog 
			int r = j.showSaveDialog(null); 

			// if the user selects a file 
			if (r == JFileChooser.APPROVE_OPTION) 

			{ 
				// set the label to the path of the selected file 
				fileField2.setText(j.getSelectedFile().getAbsolutePath()); 
			} 
			// if the user cancelled the operation 
			else
				JOptionPane.showMessageDialog(null,"User cancelled file input");
		}
		else if (action.equals("Select File 3")) { 
			// create an object of JFileChooser class 
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

			// invoke the showsSaveDialog function to show the save dialog 
			int r = j.showSaveDialog(null); 

			// if the user selects a file 
			if (r == JFileChooser.APPROVE_OPTION) 

			{ 
				// set the label to the path of the selected file 
				fileField1.setText(j.getSelectedFile().getAbsolutePath()); 
			} 
			// if the user cancelled the operation 
			else
				JOptionPane.showMessageDialog(null,"User cancelled file input");
		}
	}
	public static void main(String[] args) throws IOException
	{
		MadisonHallScheduler obj = new MadisonHallScheduler();
		obj.setVisible(true);
	}
}
