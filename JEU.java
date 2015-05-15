import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;






public class JEU extends JFrame implements ActionListener{
	
	public static JEU leJeu;
	public static boolean piege=false;
	public static int tailleDuPlateau=4;
	public static Plateau plateau;
	public static int score;
	public static boolean gagne=false;
	public static JLabel labelScore;
	
	public JMenuItem recommencer;
	public JMenuItem quitter;
	public JMenuItem trois;
	public JMenuItem quatre;
	public JMenuItem cinq;
	public JMenuItem six;
	public JMenuItem obstacle;
	public JMenuItem nonObstacle;
	public JMenuItem aide;
	
	public JMenuBar barre;	
	public JMenu menu;
	public JMenu changerTaille;
	public JMenu options;
	
	/**
	*Crée une nouvelle fenetre de jeu en initialisant la taille du plateau et les règles en fonction des variables de classe piege, tailleDuPlateau et gagne
	*/
	public JEU() throws IOException{
		
		setLayout(new BorderLayout());
		addKeyListener(new ClavierListener());
		
		score=0;
		gagne=false;
		
		barre = new JMenuBar();
		menu = new JMenu("Fichier");
		options = new JMenu("Options");
		recommencer = new JMenuItem("Recommencer");
		changerTaille = new JMenu("Changer la taille du plateau");
		quitter = new JMenuItem("Quitter");
		trois = new JMenuItem("3x3"); 
		quatre = new JMenuItem("4x4");
		cinq = new JMenuItem("5x5");
		six = new JMenuItem("6x6");
		aide = new JMenuItem("Aide");
		labelScore = new JLabel("Score : "+score);
		
		if (!piege) { 
		  obstacle = new JMenuItem("Partie avec une case vide");
		  obstacle.addActionListener(this);
		  options.add(obstacle);
		}
		//Si le joueur avait choisit la variante de jeu avec un piège, on le met sur le plateau et on crée un bouton dans les menus pour lui permettre
		// D'enlever le piège (cela aura pour effet de recommencer une partie sans le piège)
		else {
		  nonObstacle = new JMenuItem("Partie normale");
		  nonObstacle.addActionListener(this);
		  options.add(nonObstacle);
		}
		
		//Création de la barre de menu
		this.setJMenuBar(barre);
		barre.add(menu);
		barre.add(options);
		
		menu.add(recommencer);
		menu.add(changerTaille);
		menu.add(quitter);
		
		options.add(changerTaille);
		options.add(aide);
		
		changerTaille.add(trois);
		changerTaille.add(quatre);
		changerTaille.add(cinq);
		changerTaille.add(six);
		
		getContentPane().add(labelScore,BorderLayout.NORTH);
		getContentPane().add(new Plateau(tailleDuPlateau,piege));
		
		
		//Association des boutons et des auditeurs
		trois.addActionListener(this);
		quatre.addActionListener(this);
		cinq.addActionListener(this);
		six.addActionListener(this);
		recommencer.addActionListener(this);
		quitter.addActionListener(this);
		aide.addActionListener(this);
		
		setTitle("2048"); // Titre de la fenetre.
		
		//Modifie la taille de la fenetre en fonction de la taille du plateau
		switch (tailleDuPlateau){
		  case 3:setSize(310,370);break; // Taille de la fenetre.
		  case 4:setSize(410,470);break; 
		  case 5:setSize(510,570);break; 
		  case 6:setSize(610,670);break; 
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sort du programme lorsqu'on clique sur la croix
		setVisible(true);
	}
	
	/**
	*Comme JEU implemente ActionListener , il doit comporter la methode actionPerformed
	*Cette methode permet de reagir suite aux choix effectues par le joueur dans la barre de menu tels que recommencer une partie ou quitter le jeu
	*/
	public void actionPerformed(ActionEvent e){
	  if (e.getSource()==recommencer){
	    leJeu.dispose();
	    try { leJeu = new JEU(); }
	    catch (IOException a) {}
	  }
	  else {
	    if (e.getSource()==quitter) {
	      System.exit(0);
	    }
	    else {
	      if (e.getSource()==trois) {
			leJeu.dispose();
			tailleDuPlateau=3;
			try { leJeu = new JEU(); }
			catch (IOException a) {}
	      }
	      else {
			if (e.getSource()==quatre) {
				leJeu.dispose();
				tailleDuPlateau=4;
				try { leJeu = new JEU(); }
				catch (IOException a) {}
			}
			else {
				if (e.getSource()==cinq) {
					leJeu.dispose();
					tailleDuPlateau=5;
					try { leJeu = new JEU(); }
					catch (IOException a) {}
					}	
				else {
					if (e.getSource()==six) {
						leJeu.dispose();
						tailleDuPlateau=6;
						try { leJeu = new JEU(); }
						catch (IOException a) {}
					}
					else {
					  if (e.getSource()==obstacle) {
							leJeu.dispose();
							piege=true;
							try { leJeu = new JEU(); }
							catch (IOException a) {}
						}
					  else {
						if (e.getSource()==nonObstacle) {
							leJeu.dispose();
							piege=false;
							try { leJeu = new JEU(); }
							catch (IOException a) {}
						}
						else {
							if (e.getSource()==aide) JOptionPane.showMessageDialog(null,"Utiliser les touches fléchées du clavier pour déplacer les tuiles");
						}
					}
				}
			}
	    }
	}
	}
	}
}
	
	
	public static void main(String[] args) throws IOException {
		leJeu = new JEU();
	}
	
	
	
	
	/**
	*Réagit aux touches fléchées du clavier pour effectuer un mouvement sur le plateau
	*/
	class ClavierListener implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			switch (arg0.getKeyCode()) {
	    case 37:
	        //-Move left
			try { Plateau.tourComplet(plateau.table,'g'); }
			catch (IOException e) {}
	    	repaint();
	        break;
	    case 39:
	        //-Move right
	    	try { Plateau.tourComplet(plateau.table,'d'); }
			catch (IOException e) {}	
	    	repaint();
	        break;
	    case 38:
	        //-Move up
	    	try { Plateau.tourComplet(plateau.table,'h'); }
			catch (IOException e) {}
	    	repaint();
	        break;
	    case 40:
	        //-Move down
	    	try { Plateau.tourComplet(plateau.table,'b'); }
			catch (IOException e) {}
	    	repaint();
	        break;
	    default: break;
			}
		}

		public void keyReleased(KeyEvent arg0) {}
		
		public void keyTyped(KeyEvent arg0) {}
		
	}

}
