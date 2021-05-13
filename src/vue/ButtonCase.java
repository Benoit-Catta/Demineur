package vue;

import javax.swing.JButton;

import modele.Demineur;

public class ButtonCase extends JButton{
	
	private int x;
	private int y;
	
	public ButtonCase(int x, int y) {
		super("");
		this.x = x;
		this.y = y;
		
	}
	
	public void miseAJour(Demineur d) {
		if(d.getCase(this.x,this.y).isClicee() == true) {
			this.setEnabled(false);
			if(d.getCase(this.x,this.y).isBombe()) {
				this.setText("@");
			} else {
				if(d.getCase(this.x,this.y).getNb_bombes_adjacentes() == 0) {
					this.setText("");
				} else {
					this.setText("" + d.getCase(this.x,this.y).getNb_bombes_adjacentes());
				}
			}
			
		} else {
			if(d.getCase(this.x,this.y).estDrapeau()) {
				this.setText("F");
			}else {
				this.setText("");
			}
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
