import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class buypokemonform extends JFrame implements ActionListener,MouseListener{
	
	JLabel insertpoke,qtypoke;
	JButton insert,back;
	JTextField poketxt,qtytxt;
	
	JPanel mainpanel,editpanel,btnpanel,btnpos;
	JTable table;
	DefaultTableModel dtm;
	
	Connect con = Connect.getInstance();
	static int usergoks;

	public buypokemonform(int user) {
		this.usergoks = user;
		// TODO Auto-generated constructor stub
		con = con.getConnection();
		
        mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        mainpanel.setBackground(Color.cyan);
        editpanel = new JPanel(new GridLayout(3,2,20,10));
        editpanel.setBorder(new EmptyBorder(10,100,0,100));
        btnpanel = new JPanel(new GridLayout(1,2,20,10));;
        btnpanel.setBackground(Color.cyan);
        btnpos = new JPanel(new BorderLayout());
        btnpos.setBorder(new EmptyBorder(0,200,5,200));
        btnpos.setBackground(Color.cyan);
       
        
        //button
        insert = new JButton("Insert");
        back = new JButton("Back to Main");
        btnpanel.add(insert);
        btnpanel.add(back);
        
        back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				welcomeUser userpage = new welcomeUser(usergoks);
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
        
        insert.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				insertData();
				System.out.println("berhasil");
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
        
        //content
        insertpoke = new JLabel("Insert Pokemon Id");
        qtypoke = new JLabel("Quantity");
        poketxt = new JTextField();
        qtytxt = new JTextField();
        
        editpanel.add(insertpoke);
        editpanel.add(poketxt);
        editpanel.add(qtypoke);
        editpanel.add(qtytxt);
        
        mainpanel.add(editpanel,BorderLayout.CENTER);
        editpanel.setBackground(Color.cyan);
        btnpos.add(btnpanel, BorderLayout.CENTER);
        mainpanel.add(btnpos, BorderLayout.SOUTH);
        
        Vector<String> header = new Vector<>();
		header.add("PokemonId");		
		header.add("PokemonName");
		header.add("PokemonLevel");
		header.add("PokemonType");
		Vector<String> data = new Vector<>();
		
		dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		selectData(); 
	    
	    table = new JTable(dtm);
	    table.setBackground(Color.cyan);
        JScrollPane pane = new JScrollPane(table);
        pane.setBackground(Color.cyan);
        mainpanel.add(pane, BorderLayout.NORTH);
        this.add(mainpanel);
        
        frame();
		
	}
	
	void selectData() {
		//untuk manggil executor querry
				ResultSet res = con.executeQuery("SELECT * FROM pokemon");
				try {
					while(res.next()) {
						Object[] o = new Object[] {
								res.getInt("PokemonId"),
								res.getString("PokemonName"),
								res.getInt("PokemonLevel"),
								res.getString("PokemonType")
						};
						dtm.addRow(o);
//						System.out.println(res.getString("name"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	
	void clearField() {
		poketxt.setText("");
		qtytxt.setText("");
	}
	
	void insertData() {
		String id = poketxt.getText();
		String qty = qtytxt.getText();
		clearField();
		
//		con.executeUpdate("INSERT INTO product VALUES (null,\""+food+"\","+price+",\""+cuisine+"\")");
//		"SELECT cart.PokemonId,PokemonName,PokemonLevel,PokemonType,Quantity "
//		+ "FROM pokemon JOIN cart ON pokemon.PokemonId = cart.PokemonId"
		
		//versi prepare statement
		PreparedStatement ps = con.preparedStatement("INSERT INTO cart VALUES(?,?,?)");
		try {
			//ps.setString(tanda tanya ke berapa, valuenya maunya apa);
			ps.setInt(1, Integer.parseInt(id));
			ps.setInt(2, usergoks);
			ps.setInt(3, Integer.parseInt(qty));
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	void frame() {
		this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
	}
	
	public  static void main(String []args) {
		new buypokemonform(usergoks);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource() == insert) {
//			insertData();
//		}else if(arg0.getSource() == back) {
//			welcomeUser userpage = new welcomeUser(usergoks);
//			this.dispose();
//		}
		
		this.dispose();
		
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
