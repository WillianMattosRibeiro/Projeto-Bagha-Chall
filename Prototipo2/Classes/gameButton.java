package Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

public class gameButton extends JToggleButton{
	
	int i, j;
	
	public void getPosition(int l, int c){
		i = l;
		j = c;
	}
	
	public int getL(){
		return i;
	}
	
	public int getC(){
		return j;
	}
	
}
