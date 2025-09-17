import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mainGUI2 extends JFrame implements ActionListener,MouseListener{

	JTextField fieldname;
    JTextField fieldFirstName;
    JTextField fieldLastName;
    JTextField fieldEmail;
    JSpinner ageSpinner;
    JPasswordField fieldpassword;
    JPasswordField fieldConfPass;
    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");
    JButton backButton,registerButton;
    
    Connect con = Connect.getConnection();
    JLabel error, errormail;
	
	public mainGUI2() {

		judulForm();

        tombol();

        //label nama
        JLabel name  = new JLabel("Username");

        //field nama
        fieldname = new JTextField();

        //label FirstName
        JLabel firstName = new JLabel("First Name");

        //field FirstName
        fieldFirstName = new JTextField();

        //label LastName
        JLabel lastName = new JLabel("Last Name");

        //field LastName
        fieldLastName = new JTextField();

        //label email
        JLabel email = new JLabel("Email");

        //field Email
        fieldEmail = new JTextField();

        //radioButton
        ButtonGroup sex = new ButtonGroup();
        sex.add(male);
        sex.add(female);


        //label Age
        JLabel age = new JLabel("Age");

        //Spinner age
        SpinnerModel ageModel = new SpinnerNumberModel(
                0, // Mulai dari berapa
                0, // isi nya ada berapa banyak
                100, // berakhir dimana
                1 // Iterator -> jumlah penamban/pengurangan


        );
        ageSpinner = new JSpinner(ageModel); // Penampung


        //label password
        JLabel password = new JLabel("Password:");

        //field password
        fieldpassword = new JPasswordField();

        //label confPassword
        JLabel confPassword = new JLabel("Confirm Password");

        //field confPassword
        fieldConfPass = new JPasswordField();

        //panelutama
        JPanel panel = new JPanel(new GridLayout(16,1));
        panel.setBackground(Color.cyan);
        panel.setBorder(new EmptyBorder(20,100,20,100));

        //panelSex
        JPanel panelSex = new JPanel(new GridLayout(1,2));
        panelSex.setBackground(Color.cyan);
        //panelSex.setBorder(new EmptyBorder(20,50,20,50));
        panelSex.add(male);
        panelSex.add(female);

        panel.add(name);
        panel.add(fieldname);
        panel.add(firstName);
        panel.add(fieldFirstName);
        panel.add(lastName);
        panel.add(fieldLastName);
        panel.add(email);
        panel.add(fieldEmail);

        male.setBackground(Color.cyan);
        female.setBackground(Color.cyan);
        panel.add(panelSex);

               
        panel.add(age);
        panel.add(ageSpinner);
        panel.add(password);
        panel.add(fieldpassword);
        panel.add(confPassword);
        panel.add(fieldConfPass);

        this.add(panel);

        mainFrame();
    }

    public void mainFrame() {
        //ini main frame
        this.setTitle("PokemoNK");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setResizable(false);


        this.setVisible(true);
    }

    public void judulForm() {
        //label judul form
        JLabel entryform = new JLabel("Register");
        entryform.setForeground(Color.BLACK);

        //panel untuk nampung judul form
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.white);
        panel1.add(entryform);
        this.add(panel1, BorderLayout.NORTH);
    }

    public void tombol() {
        //button submit
        registerButton = new JButton("Register");

        //button register
        backButton = new JButton("Back to Login");
        //backButton.setSize(100,20);

        //label kosong
        JLabel labelkosong = new JLabel();

        //untuk pop up message
        registerButton.addActionListener(this);

        //untuk pop up message
        backButton.addActionListener(this);

        //ini panel untuk nampung button
        JPanel paneltombol = new JPanel(new GridLayout(2,2));
        JLabel kosong = new JLabel();
        paneltombol.setBackground(Color.cyan);
        paneltombol.setBorder(new EmptyBorder(20,100,20,100));
        paneltombol.add(registerButton);
        paneltombol.add(labelkosong);
        paneltombol.add(backButton);
        paneltombol.add(kosong);
        this.add(paneltombol, BorderLayout.SOUTH);
        
        error = new JLabel("Error Format or Input");
		error.setVisible(false);
		error.setForeground(Color.red);
		paneltombol.add(error);
		
	
        		
	}
    

	void clearField() {
		fieldname.setText("");
		fieldFirstName.setText("");
		fieldLastName.setText("");
		fieldEmail.setText("");
		fieldpassword.setText("");
		fieldConfPass.setText("");
	}
	
	void clearfield() {
		fieldname.setText("");
		fieldFirstName.setText("");
		fieldLastName.setText("");
		ageSpinner.setValue(null);
		fieldEmail.setText("");
		fieldpassword.setText("");
	}
    
    void insertData() {
		String username = fieldname.getText();
		String firtsname = fieldFirstName.getText();
		String lastname = fieldLastName.getText();
		String email = fieldEmail.getText();
		String Pass = fieldpassword.getText();
		int age = (Integer.parseInt(ageSpinner.getValue().toString()));
		String gender = " ";
		if(male.isSelected()) {
			gender = "Male";
		}else if(female.isSelected()){
			gender = "Female";
		}
		System.out.println("hello");
		
		if(username.equals("") && firtsname.equals("") && lastname.equals("") && email.equals("") && Pass.equals("")) {
			error.setVisible(true);
		}else if (!(email.contains("@"))) {
			error.setVisible(true);
		}else if (fieldConfPass.equals(Pass)) {
			error.setVisible(true);
		}
//		con.executeUpdate("INSERT INTO product VALUES (null,\""+food+"\","+price+",\""+cuisine+"\")");
//		"SELECT cart.PokemonId,PokemonName,PokemonLevel,PokemonType,Quantity "
//		+ "FROM pokemon JOIN cart ON pokemon.PokemonId = cart.PokemonId"
		
		//versi prepare statement
		PreparedStatement ps = con.preparedStatement("INSERT INTO user VALUES(null,?,?,?,?,?,?)");
		try {
			//ps.setString(tanda tanya ke berapa, valuenya maunya apa);
			ps.setString(1, username);
			ps.setString(2, firtsname + lastname);
			ps.setInt(3, (age));
			ps.setString(4, email);
			ps.setString(5, gender);
			ps.setString(6, Pass);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new mainGUI2();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == backButton) {
			this.dispose();
			mainGUI login = new mainGUI();
		}else if (arg0.getSource() == registerButton){
			insertData();
			System.out.println("Sukses");

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
