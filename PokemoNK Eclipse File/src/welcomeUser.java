import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class welcomeUser extends JFrame implements ActionListener,MouseListener{
	
	JMenuItem signout;
	static int userapalah;

	public welcomeUser(int user) {
		System.out.println("ini adalah user");

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
		JMenu user1 = new JMenu("User");
		signout = new JMenuItem("Sign Out");
		user1.add(signout);
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
		
		
		//menu bar adventure
		JMenu adventure = new JMenu("Adventure");
		JMenuItem pokemonmarket = new JMenuItem("Pokemon Market");
		JMenuItem  adventurepokemon= new JMenuItem("Bag");
		adventure.add(pokemonmarket);
		adventure.add(adventurepokemon);
		pokemonmarket.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				buypokemonform buypoke = new buypokemonform(user);
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

		adventurepokemon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				bagform bag = new bagform(userapalah);
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
		
		
		//menu bar Transaction
		JMenu transaction = new JMenu("Transaction");
		JMenuItem  transactionhistory = new JMenuItem("View Transaction History");
		transaction.add(transactionhistory);
		
		transactionhistory.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				history histo = new history(user);
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
				
		menubar.add(user1);
		menubar.add(adventure);
		menubar.add(transaction);
		
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
		new welcomeUser(userapalah);
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
