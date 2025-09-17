import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class welcomeAdmin extends JFrame implements ActionListener,MouseListener{

	static int useradmin;
	
	public welcomeAdmin(int admin2) {
		
	this.useradmin = admin2;
	System.out.println("ini adalah admin");
	
	//label pokemmonk
	JLabel admin = new JLabel("PokemoNK");
	admin.setFont(new Font("MV Boli", Font.BOLD, 35));
	
	JPanel panel = new JPanel();
	
	panel.setBorder(new EmptyBorder(110,260,145,250));
	panel.setBackground(Color.cyan);
	panel.add(admin);
	
	//penampung menu bar
	JMenuBar menubar = new JMenuBar();
	
	//menu bar user
	JMenu userp = new JMenu("User");
	JMenuItem  signout = new JMenuItem("Sign Out");
	userp.add(signout);
	signout.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			mainGUI login = new mainGUI();
			actionPerformed(null);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});

	
	//menu bar manage
	JMenu manage = new JMenu("Manage");
	JMenuItem  managepokemon= new JMenuItem("Manage Pokemon");
	manage.add(managepokemon);
	managepokemon.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			managepokemon manage = new managepokemon(useradmin);
			actionPerformed(null);
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	menubar.add(userp);
	menubar.add(manage);
	
	setJMenuBar(menubar);
	
	this.add(panel);
	
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
		
	public static void main(String[] args) {
		new welcomeAdmin(useradmin);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
		
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
