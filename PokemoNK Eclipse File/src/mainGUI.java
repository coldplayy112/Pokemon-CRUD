import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class mainGUI extends JFrame implements ActionListener,MouseListener{

	JTextField fieldname;
	JPasswordField fieldpassword;
	private JPanel panel;
	JButton registbutton,Loginbutton;
	JLabel name,password,error;
	Connect con = new Connect();
	
	
	public mainGUI() {
		
		
		judulForm();
		
		tombol();
		
		//label nama
		 name  = new JLabel("Username");
		
		//field nama
		fieldname = new JTextField();
		
		//label password
		 password = new JLabel("Password:");
		
		//field password
		fieldpassword = new JPasswordField();
		
		//panelutama
		JPanel panel = new JPanel(new GridLayout(4,1));
		panel.setBackground(Color.cyan);
		panel.setBorder(new EmptyBorder(50,110,50,110));
		
		panel.add(name);
		panel.add(fieldname);
		panel.add(password);
		panel.add(fieldpassword);
		
		this.add(panel,BorderLayout.CENTER);

		mainFrame();

	}

	public void mainFrame() {
		//ini main frame
		this.setTitle("PokemoNK");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setResizable(false);
		//this.getContentPane().setBackground(Color.pink);	
		
		this.setVisible(true);
	}
	
	public void judulForm() {
		//label judul form
		JLabel entryform = new JLabel("Login");
		entryform.setForeground(Color.BLACK);
		
		//panel untuk nampung judul form
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.add(entryform);
		this.add(panel1, BorderLayout.NORTH);
	}	
	
	public void tombol() {
		//button submit
		 Loginbutton = new JButton("Login");
		
		//button register
		 registbutton = new JButton("Register");
		 
		//label error massage
			error = new JLabel("Username or Password incorrect");
			error.setVisible(false);
			error.setForeground(Color.red);
		
		//label kosong
		JLabel labelkosong = new JLabel();
		
		//untuk pop up message
		Loginbutton.addActionListener(this);
		
		//untuk pop up message
		registbutton.addActionListener(this);
		
		//ini panel untuk nampung button
		JPanel paneltombol = new JPanel(new GridLayout(4,1));
		paneltombol.setBackground(Color.cyan);
		paneltombol.setBorder(new EmptyBorder(20,145,20,145));
		paneltombol.add(Loginbutton);
		paneltombol.add(labelkosong);
		paneltombol.add(registbutton);
		paneltombol.add(error);
		this.add(paneltombol, BorderLayout.SOUTH);
	}
	
	
	
	
	void validateuser() {
		ResultSet rs = con.validate("SELECT * FROM user");
		try {
			while(rs.next()) {
//				String username = rs.getString(2);
//				String pass = rs.getString(7);
				String fieldcatch;
				String pwcatch;
				fieldcatch = fieldname.getText();
				pwcatch = fieldpassword.getText();
				
				if(fieldcatch.equals(rs.getString("Username"))) {
					if(pwcatch.equals(rs.getString("Password"))) {
						if(fieldcatch.contains("admin")) {
							int admin2 = rs.getInt("UserId");
							System.out.println("admin");
							this.dispose();
							welcomeAdmin admin = new welcomeAdmin(admin2);
							
						}else{
							int user = rs.getInt("UserId");
							welcomeUser userpage = new welcomeUser(user);
							this.dispose();
					}}else {
							error.setVisible(true);
					}}else {
						error.setVisible(true);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	public static void main(String[] args) {
		new mainGUI();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == registbutton ) {
			this.dispose();
			mainGUI2 registpage = new mainGUI2();
		}else if(arg0.getSource() == Loginbutton) {
 			validateuser();
 		
		}	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
