import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.swing.table.DefaultTableModel;

public class bagform extends JFrame implements ActionListener,MouseListener{
	
		
	JPanel mainpanel,contentpanel,titlepanel,editpanel;
	JLabel title,pokeid,kosong;
	JTextField poketxt;
	JButton chekout,back,delete;
	
	Connect con = Connect.getConnection();
	JTable table;
	DefaultTableModel dtm;
	
	static int userbag;
	
	public bagform(int user) {
		this.userbag = user;
		// TODO Auto-generated constructor stub
		con = con.getConnection();
		
        mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        mainpanel.setBackground(Color.cyan);
        contentpanel = new JPanel((new BorderLayout()));
        contentpanel.setBackground(Color.cyan);
        titlepanel = new JPanel(new BorderLayout());
        titlepanel.setBackground(Color.cyan);
        editpanel = new JPanel(new GridLayout(3,2,20,10));
        editpanel.setBorder(new EmptyBorder(0,200,5,200));
        editpanel.setBackground(Color.cyan);
        
        //title
        title = new JLabel("Manage Cart");
        title.setFont(new Font("MV Boli",Font.BOLD,16));
        titlepanel.add(title, BorderLayout.CENTER);
        titlepanel.setBorder(new EmptyBorder(10,320,10,300));
        
        contentpanel.add(titlepanel, BorderLayout.NORTH);
        mainpanel.add(contentpanel);
        
        //editpanel
        pokeid = new JLabel("PokemonId");
        poketxt = new JTextField();
        chekout = new JButton("Checkout");
        delete = new JButton("Delete");
        back = new JButton("Back to Main");
        kosong = new JLabel("");
        
        editpanel.add(pokeid);
        editpanel.add(poketxt);
        editpanel.add(kosong);
        editpanel.add(delete);
        editpanel.add(chekout);
        editpanel.add(back);
        
        back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				welcomeUser user = new welcomeUser(userbag);
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
        
        delete.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				deleteData();
				
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
        
        
        contentpanel.add(editpanel);
		
		String[] header = {"PokemonId", "PokemonName", "PokemonLevel","PokemonType","Quantity"};
        
        dtm = new DefaultTableModel(header, 0);
        
		String query = "SELECT cart.PokemonId,PokemonName,PokemonLevel,PokemonType,Quantity "
				+ "FROM pokemon JOIN cart ON pokemon.PokemonId = cart.PokemonId"; //Query untuk ambil data
	    ResultSet rs = con.executeQuery(query); 
	    
	    ResultSetMetaData rms = null;
	    
	    try {
            rms = (ResultSetMetaData) rs.getMetaData();
            while (rs.next()){
                Vector<Object> data = new Vector<>(); // ambil semua datanya (looping)

                for(int i = 1; i<= rms.getColumnCount(); i++){
                    data.add(rs.getObject(i)); //ambil semua data di columnnya
                }
                dtm.addRow(data); //tambahkan data ke table (per row)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    
	    table = new JTable(dtm);
	    table.setBackground(Color.cyan);
        JScrollPane pane = new JScrollPane(table);
        pane.setBackground(Color.cyan);
        
        mainpanel.add(pane, BorderLayout.NORTH);
        this.add(mainpanel);
		
		frame();

	}
	
	void clearField() {
		poketxt.setText("");
	}
	
	
	void deleteData() {
		String id =  poketxt.getText();
			
			//versi biasa
			//DELETE FROM product where id = id
//			con.executeUpdate("DELETE FROM product WHERE id =" + id);
			
			//vesi prepared statement 
			PreparedStatement ps = con.preparedStatement("DELETE FROM pokemon WHERE PokemonId = ?");
			try {
				ps.setInt(1, Integer.parseInt(id));
				ps.execute();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			clearField();
		}

	

	void frame() {
		this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new bagform(userbag);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}

}
