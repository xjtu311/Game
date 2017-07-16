package game2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
/**
 * @author: sly
 * @date:2017年2月24日上午8:48:02
 */
	
	private static final long serialVersionUID = 1L;
	
	private int Numbers[][] = new int[4][4];
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game UI = new Game();
		UI.IntUI();

	}

	private void IntUI() {
		// TODO Auto-generated method stub
		this.setTitle("2048");
		this.setLocation(450,500);
		this.setSize(400, 500);
		this.setLayout(null);
		
		//start button
		ImageIcon starticon = new ImageIcon("res/start.png");
		JButton start = new JButton(starticon);
		start.setFocusable(false);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setBounds(5,10,120,30);
		this.add(start);
		
		//back button
		ImageIcon backicon = new ImageIcon("res/back.png");
		JButton back = new JButton(backicon);
		back.setFocusable(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(270,10,120,30);
		this.add(back);
		
		//about button
		ImageIcon abouticon = new ImageIcon("res/about.png");
		JButton about = new JButton(abouticon);
		about.setFocusable(false);
		about.setBorderPainted(false);
		about.setContentAreaFilled(false);
		about.setBounds(160,10,70,30);
		this.add(about);
		
		//show score
		
		JLabel score = new JLabel("score: 0");
		score.setBounds(40,45,120,30);
		score.setFont(new Font("幼圆",Font.CENTER_BASELINE,18));
		score.setForeground(new Color(0x000000));
		this.add(score);
		
		//show ui
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setVisible(true);  
		
		//create handle class
		
		Listener lsn = new Listener(this,Numbers,start,about,back,score);
		start.addActionListener(lsn);
		about.addActionListener(lsn);
		back.addActionListener(lsn);
		this.addKeyListener(lsn);
			
	}
	
	//rewrite frame
	
	@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.setColor(new Color(0xBBADA0));
			g.fillRoundRect(15, 110, 370, 370, 15, 15);
			
			//external frame
			g.setColor(new Color(0xCDC1B4));
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					//inside frame
					g.fillRoundRect(25+i*90, 120+j*90, 80, 80,15,15);					
				}				
			}
			
			
			//adjust number's position and coloring
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(Numbers[j][i] != 0){
						int FontSize = 30;
						int MoveX = 0, MoveY = 0;
						switch(Numbers[j][i]){
						case 2:
							g.setColor(new Color(0xeee4da));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
							
						case 4:
							g.setColor(new Color(0xede0c8));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
							
						case 8:
							g.setColor(new Color(0xf2b179));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
							
						case 16:
							g.setColor(new Color(0xf59563));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
							
						case 32:
							g.setColor(new Color(0xf67c53));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
							
						case 64:
							g.setColor(new Color(0xf5e3b1));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
							
						case 128:
							g.setColor(new Color(0xedcf72));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;	
							
						case 256:
							g.setColor(new Color(0xedcc61));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;
							
						case 512:
							g.setColor(new Color(0xedc850));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;
							
						case 1024:
							g.setColor(new Color(0xedc53f));
							FontSize = 27;
							MoveX = -15;
							MoveY = 0;
							break;
							
						case 2048:
							g.setColor(new Color(0xedc22e));
							FontSize = 27;
							MoveX = -15;
							MoveY = 0;
							break;
							
						default:
							g.setColor(new Color(0x000000));
							break;
						
						}
						
						//inside frame coloring
						
						g.fillRoundRect(25+i*90, 120+j*90, 80, 80, 15, 15);
						g.setColor(new Color(0x000000));
						g.setFont(new Font("Kristen ITC",Font.PLAIN,FontSize));
						g.drawString(Numbers[j][i] + "" ,
								25 + i * 90 + 30 + MoveX ,
								120 + j * 90 + 50 + MoveY );
						
						
					}
				}
			}
			
		}
	
}
