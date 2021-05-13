package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ButtonControler;
import modele.Demineur;

public class Fenetre extends JFrame{
	
	private Demineur demineur;
	private ButtonCase[][] mesButton;
	private JLabel drapeau;
	
	public Fenetre(Demineur demineur) {
		
		this.demineur = demineur;
		this.mesButton = new ButtonCase[demineur.getLargeur()][demineur.getLargeur()];
		
		JPanel principal = new JPanel(new BorderLayout());
		
		JPanel jeu = new JPanel(new GridLayout(demineur.getLargeur(),demineur.getLargeur()));
		
		ButtonControler action = new ButtonControler(demineur,this);
		
		for(int i = 0 ; i < demineur.getLargeur() ; i++) {
			for(int c = 0 ; c < demineur.getLargeur() ; c++) {
				mesButton[i][c] = new ButtonCase(i,c);
				mesButton[i][c].addMouseListener(action);
				jeu.add(mesButton[i][c]);
			}
		}
		
		drapeau = new JLabel();
		drapeau.setText("Drapeau Restants : " + demineur.getBombe());
		
		principal.add(drapeau,BorderLayout.NORTH);
		principal.add(jeu,BorderLayout.CENTER);
		
        this.add(principal);
        
        this.repaint();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
        
		
	}
	
	public void setDemineur(Demineur d) {
		this.demineur = d;
	}
	
	public void mettreAJour() {
		for(int i = 0 ; i < demineur.getLargeur() ; i++) {
			for(int c = 0 ; c < demineur.getLargeur() ; c++) {
				mesButton[i][c].miseAJour(demineur);
			}
		}
	}
	
	public void rejouer(int bombe) {
		this.dispose();
		Fenetre f = new Fenetre(new Demineur(bombe));
	}
	
	public void setDrapeau(int drapeau) {
		this.drapeau.setText("Drapeau Restants : " + drapeau);
	}
}
