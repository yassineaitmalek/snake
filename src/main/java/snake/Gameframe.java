package snake;


import javax.swing.*;

public class Gameframe extends JFrame{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	Gameframe(){
		
		this.add(new Gamepanel());
		this.setTitle("snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.show();
		
		
		
		
		
	}
}
