package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import modele.Demineur;
import vue.ButtonCase;
import vue.Fenetre;

public class ButtonControler implements MouseListener {
	
	private Demineur demineur;
	private Fenetre fenetre;
	
	public ButtonControler(Demineur d, Fenetre f) {
		this.demineur = d;
		this.fenetre = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ButtonCase c = (ButtonCase) e.getSource();
		if(SwingUtilities.isRightMouseButton(e)) {
			demineur.clic(c.getX(), c.getY(), true);
			fenetre.setDemineur(demineur);
			fenetre.mettreAJour();
			fenetre.setDrapeau(demineur.getDrapeau());
			if(demineur.gagner() == true) {
				JOptionPane.showMessageDialog(fenetre, "Gagné", "Dialog",JOptionPane.ERROR_MESSAGE);
				fenetre.rejouer(demineur.getBombe());
			}
		} else {
			if(demineur.getCase(c.getX(), c.getY()).estDrapeau() == true) {
				demineur.clic(c.getX(), c.getY(), true);
				fenetre.setDrapeau(demineur.getDrapeau());
			}
			if(demineur.clic(c.getX(), c.getY(), false) == false) {
				demineur.afficherToutesLesBombes();
				fenetre.setDemineur(demineur);
				fenetre.mettreAJour();
				JOptionPane.showMessageDialog(fenetre, "Perdu", "Dialog",JOptionPane.ERROR_MESSAGE);
				fenetre.rejouer(demineur.getBombe());
			} else {
				fenetre.setDemineur(demineur);
				fenetre.mettreAJour();
			}
			
		}
		
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
