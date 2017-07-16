package game2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Listener extends KeyAdapter implements ActionListener{

	/**
	 * @author: sly
	 * @date:2017年2月24日上午9:30:08
	 */
	
	private Game UI;
	private int Numbers[][];
	private Random rand = new Random();
	private int BackUp[][] = new int[4][4];
	private int BackUp2[][] = new int[4][4];
	public JLabel jscore;
	public JButton start,back,about;
	private boolean isWin = false ,alreadyback = false , relive = false;
	private int score = 0;
	private int tempscore;
	private int tempscore2;
	
	
	
	public Listener(Game UI,int Numbers[][], JButton start, JButton back, JButton about, JLabel jscore) {
		// TODO Auto-generated constructor stub
		this.UI = UI;
		this.Numbers = Numbers;
		this.about = about;
		this.back = back;
		this.jscore = jscore;
		this.start = start;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start){
			isWin = false;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Numbers[j][i] = 0;
				}
			}
		
			//reset score 0
			score  = 0;
			jscore.setText("score："+score);
			int r1 = rand.nextInt(4);
			int r2 = rand.nextInt(4);
			int c1 = rand.nextInt(4);
			int c2 = rand.nextInt(4);
			
			while(r1 == r2 && c1 ==c2 ){
				r2 = rand.nextInt(4);
				c2 = rand.nextInt(4);
			}
			
			//create number 2 or 4
			int value1 = rand.nextInt(2)*2 + 2 ;
			int value2 = rand.nextInt(2)*2 + 2 ;
			
			//store number into rigth position
			Numbers[r1][c1] = value1 ;
			Numbers[r2][c2] = value2 ;
			UI.paint(UI.getGraphics());				
		}
		else if(e.getSource() == about ){
			JOptionPane.showMessageDialog(UI, "游戏规则：\n"
					+
					"开始时棋盘内随机出现两个数字，出现的数字仅可能为2或4\n"
					+ 
					"玩家选择的方向上若有相同的数字则合并，每次有效移动可以同时合并，但不可以连续合并\n"
					+ 
					"合并所得的所有新生成数字想加即为该步的有效得分\n"
					+
					"玩家选择的方向行或列前方有空格则出现位移\n"
					+ 
					"每有效移动一步，棋盘的空位(无数字处)随机出现一个数字(依然可能为2或4)\n"
					+
					"棋盘被数字填满，无法进行有效移动，游戏失败，游戏结束\n"
					+ 
					"棋盘上出现2048，游戏胜利，游戏结束。\n");
		}
		else if(e.getSource() == back && alreadyback ==false){
			alreadyback = true ;
			if(relive ==true){
				score = tempscore;
				jscore.setText("分数："+score);
				for (int i = 0; i < BackUp.length; i++) {
					Numbers[i] = Arrays.copyOf(BackUp[i], BackUp[i].length);					
				}
			}
			else{
				score = tempscore2;
				jscore.setText("分数："+score);
				for (int i = 0; i < BackUp2.length; i++) {
					Numbers[i] =Arrays.copyOf(BackUp2[i], BackUp2[i].length);			
				}
				relive = true ;
			}
			UI.paint(UI.getGraphics());
		}
		
	}
	
	//keyboard monitoring
	public void keyPressed(KeyEvent event) {
		
		// move 
		int Counter = 0 ;
		//whether cell full 
		int NumCounter = 0;
		//the number of near cell with same number
		int NumNearCounter = 0;
		/*
		 * value about key
		 * left:37   rigth:39
		 *   up:38    down:40
		 */
		alreadyback = false;
		
		if(BackUp != null  || BackUp.length != 0){
			
			//backup the score
			tempscore2 = tempscore;
			//call the method java.util.Arrays.copyOf() to copy the array in the for loop
			
			for (int i = 0; i < BackUp.length; i++) {
				BackUp2[i] = Arrays.copyOf(BackUp[i], BackUp[i].length);
			}
			
		}
		
		//backup the array
		tempscore = score;
		for (int i = 0; i < Numbers.length; i++) {
			BackUp[i] = Arrays.copyOf(Numbers[i], Numbers[i].length);
		}
		
		if(isWin == false ){
			switch(event.getKeyCode()){
			
			//move left
			case 37:
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if(Numbers[i][j] != 0){
							int temp = Numbers[i][j];
							int pre = j - 1 ;
							while(pre >= 0 && Numbers[i][pre] == 0){
								Numbers[i][pre] = temp;
								Numbers[i][pre+1] = 0;
								pre--;
								Counter++;
							}
						}
					}
				}
				
				for (int i = 0; i < 4; i++) 
					for (int j = 0; j < 4; j++) 
						if( j + 1 < 4 && Numbers[i][j] == Numbers[i][j+1] && 
								(Numbers[i][j] != 0 || Numbers[i][j+1] != 0)){
							Numbers[i][j] = Numbers[i][j] + Numbers[i][j+1];
							Numbers[i][j+1] = 0;
							Counter++;
							score += Numbers[i][j];
							if(Numbers[i][j] ==2048 ){
								isWin = true;
							}					
				}
				
				for (int i = 0; i < 4; i++) 
					for (int j = 0; j < 4; j++) 
						if(Numbers[i][j] != 0){
							int temp = Numbers[i][j];
							int pre = j - 1;
							while(pre >= 0 && Numbers[i][pre] == 0){
								Numbers[i][pre] = temp;
								Numbers[i][pre+1] = 0;
								pre--;
								Counter++;
							}
							
						}
				break;
				
				//move right
			case 39:
				for(int i = 3; i >= 0; i--)
					for(int j = 3; j >= 0 ; j--)
						if(Numbers[i][j] != 0){
							int temp = Numbers[i][j];
							int pre = j + 1;
							while(pre <= 3 && Numbers[i][pre] == 0){
								Numbers[i][pre] = temp;
								Numbers[i][pre-1] = 0;
								pre++;
								Counter++;
							}
						}
				for(int i = 3; i >= 0; i--)
					for(int j = 3; j >= 0 ; j--)
						if(j + 1 < 4 && Numbers[i][j] == Numbers[i][j+1] && 
						(Numbers[i][j] !=0 || Numbers[i][j+1] != 0)){
							Numbers[i][j+1] = Numbers[i][j] + Numbers[i][j+1];
							Numbers[i][j] = 0;
							Counter++;
							score = score + Numbers[i][j+1];
							if(Numbers[i][j+1] ==2048)
								isWin = true ;						
						}
				for (int i = 3; i >= 0; i--)
					for (int j = 3; j >= 0; j--)
						if (Numbers[i][j] != 0) {
							int temp = Numbers[i][j];
							int pre = j + 1;
							while (pre <= 3 && Numbers[i][pre] == 0) {
								Numbers[i][pre] = temp;
								Numbers[i][pre - 1] = 0;
								pre++;
								Counter++;
							}
						}
				break;

			case 38:
				// move up
				for (int j = 0; j < 4; j++)
					for (int i = 0; i < 4; i++)
						if (Numbers[i][j] != 0) {
							int temp = Numbers[i][j];
							int pre = i - 1;
							while (pre >= 0 && Numbers[pre][j] == 0) {
								Numbers[pre][j] = temp;
								Numbers[pre + 1][j] = 0;
								pre--;
								Counter++;
							}
						}
				for (int j = 0; j < 4; j++)
					for (int i = 0; i < 4; i++)
						if (i + 1 < 4
								&& (Numbers[i][j] == Numbers[i + 1][j])
								&& (Numbers[i][j] != 0 || Numbers[i + 1][j] != 0)) {
							Numbers[i][j] = Numbers[i][j] + Numbers[i + 1][j];
							Numbers[i + 1][j] = 0;
							Counter++;
							score += Numbers[i][j];
							if (Numbers[i][j] == 2048) {
								isWin = true;
							}
						}

				for (int j = 0; j < 4; j++)
					for (int i = 0; i < 4; i++)
						if (Numbers[i][j] != 0) {
							int temp = Numbers[i][j];
							int pre = i - 1;
							while (pre >= 0 && Numbers[pre][j] == 0) {
								Numbers[pre][j] = temp;
								Numbers[pre + 1][j] = 0;
								pre--;
								Counter++;
							}
						}
				break;

			case 40:
				// move down
				for (int j = 3; j >= 0; j--)
					for (int i = 3; i >= 0; i--)
						if (Numbers[i][j] != 0) {
							int temp = Numbers[i][j];
							int pre = i + 1;
							while (pre <= 3 && Numbers[pre][j] == 0) {
								Numbers[pre][j] = temp;
								Numbers[pre - 1][j] = 0;
								pre++;
								Counter++;
							}
						}
				for (int j = 3; j >= 0; j--)
					for (int i = 3; i >= 0; i--)
						if (i + 1 < 4
								&& (Numbers[i][j] == Numbers[i + 1][j])
								&& (Numbers[i][j] != 0 || Numbers[i + 1][j] != 0)) {
							Numbers[i + 1][j] = Numbers[i][j]
									+ Numbers[i + 1][j];
							Numbers[i][j] = 0;
							Counter++;
							score += Numbers[i + 1][j];
							if (Numbers[i + 1][j] == 2048) {
								isWin = true;
							}
						}

				for (int j = 3; j >= 0; j--)
					for (int i = 3; i >= 0; i--)
						if (Numbers[i][j] != 0) {
							int temp = Numbers[i][j];
							int pre = i + 1;
							while (pre <= 3 && Numbers[pre][j] == 0) {
								Numbers[pre][j] = temp;
								Numbers[pre - 1][j] = 0;
								pre++;
								Counter++;
							}
						}
				break;
			}
			

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (Numbers[i][j] == Numbers[i][j + 1]
							&& Numbers[i][j] != 0) {
						NumNearCounter ++;
					}
					if (Numbers[i][j] == Numbers[i + 1][j]
							&& Numbers[i][j] != 0) {
						NumNearCounter++;
					}
					if (Numbers[3][j] == Numbers[3][j + 1]
							&& Numbers[3][j] != 0) {
						NumNearCounter++;
					}
					if (Numbers[i][3] == Numbers[i + 1][3]
							&& Numbers[i][3] != 0) {
						NumNearCounter++;
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (Numbers[i][j] != 0) {
						NumCounter ++;
					}
				}
			}
			if (Counter > 0) {

				jscore.setText("分数：" + score);
				int r1 = rand.nextInt(4);
				int c1 = rand.nextInt(4);
				while (Numbers[r1][c1] != 0) {
					r1 = rand.nextInt(4);
					c1 = rand.nextInt(4);
				}
				int value1 = rand.nextInt(2) * 2 + 2;
				Numbers[r1][c1] = value1;
			}

			if (isWin == true){
				UI.paint(UI.getGraphics());
				JOptionPane.showMessageDialog(UI, "** WIN ** !\n"
						+ "您的最终得分为：" + score);
			}
				
			if (NumCounter == 16 && NumNearCounter == 0) {
				relive = true;
				JOptionPane.showMessageDialog(UI, "没地方可以合并了\n"
						+ "重新开始或者后退一步");
			}
			UI.paint(UI.getGraphics());
		}
				
			
		}
	}
		

