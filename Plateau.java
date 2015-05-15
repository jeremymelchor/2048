import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Plateau extends JPanel {
	
	
public static int[][] table ;
public static int taille;
public final int COTECASE=100;
BufferedImage imageDeux,imageQuatre,imageHuit,imageSeize,image32,image64,image128,image256,image512,image1024,image2048,image4096,image8192,image16384,image32768;


/**
    Constructeur de Plateau: crée un tableau de taille variable et l'initialise selon les règles choisies et charge les images des nombres
    @param longueur Longueur d'une ligne du plateau.
    @param piege Boolean qui définie si on fait une partie avec un obstacle ou non.
    @return le plateau de jeu
*/
public Plateau(int longueur,boolean piege) throws IOException{
	this.taille=longueur;
	table = new int [longueur][longueur];
	if (piege) this.ajouterUnObstacleAuHasardSurLePlateau();
	mettreUnNombreAuHasarsdDansLeTableau(table,true);
	
	imageDeux = ImageIO.read(getClass().getRessource("./Images/2.png"));
	imageQuatre = ImageIO.read(getClass().getRessource("./Images/4.png"));
	imageHuit = ImageIO.read(getClass().getRessource("./Images/8.png"));
	imageSeize = ImageIO.read(getClass().getRessource("./Images/16.png"));
	image32 = ImageIO.read(getClass().getRessource("./Images/32.png"));
	image64 = ImageIO.read(getClass().getRessource("./Images/64.png"));
	image128 = ImageIO.read(getClass().getRessource("./Images/128.png"));
	image256 = ImageIO.read(getClass().getRessource("./Images/256.png"));
	image512 = ImageIO.read(getClass().getRessource("./Images/512.png"));
	image1024 = ImageIO.read(getClass().getRessource("./Images/1024.png"));
	image2048 = ImageIO.read(getClass().getRessource("./Images/2048.png"));
	image4096 = ImageIO.read(getClass().getRessource("./Images/4096.png"));
	image8192 = ImageIO.read(getClass().getRessource("./Images/8192.png"));
	image16384 = ImageIO.read(getClass().getRessource("./Images/16384.png"));
	image32768 = ImageIO.read(getClass().getRessource("./Images/32768.png"));
}
	



/**
    Additionne une colonne vers le haut ou le bas, en supposant que celle ci ne contienne pas de zéros
    @param tab Tableau du jeu.
    @param colonne Numéro de la colonne.
	@param haut Si vrai, additionne vers le haut, sinon vers le bas.
*/
public static void additionnerColonne(int [][] tab ,int colonne,boolean haut){  
	if (haut) {   //Vers le haut
		for(int i=0;i<taille-1;i++){
				if (tab[i][colonne]==tab[i+1][colonne]) {
					tab[i][colonne]=tab[i][colonne]*2;
					JEU.leJeu.score+=tab[i][colonne];
					tab[i+1][colonne]=0;
				}
		}
	}
	else{           //Vers le bas
		for(int i=taille-1;i>0;i--){
			if (tab[i][colonne]==tab[i-1][colonne]) {
				tab[i][colonne]=tab[i][colonne]*2;
				JEU.leJeu.score+=tab[i][colonne];
				tab[i-1][colonne]=0;
			}
		}
	}
}



/**
	Additionne une ligne vers la gauche ou la droite, en supposant que celle ci ne contienne pas de zéros
    @param tab Tableau du jeu.
    @param ligne Numéro de la ligne.
	@param gauche Si vrai, additionne vers la gauche, sinon vers la droite.
*/
public static void additionnerLigne(int [][] tab ,int ligne,boolean gauche){  
	if (gauche) {   //Vers la gauche
		for(int i=0;i<taille-1;i++){
				if (tab[ligne][i]==tab[ligne][i+1]) {
					tab[ligne][i]=tab[ligne][i]*2;
					JEU.leJeu.score+=tab[ligne][i];
					tab[ligne][i+1]=0;
				}
		}
	}
	else{           //Vers la droite
		for(int i=taille-1;i>0;i--){
			if (tab[ligne][i]==tab[ligne][i-1]) {
				tab[ligne][i]=tab[ligne][i]*2;
				JEU.leJeu.score+=tab[ligne][i];
				tab[ligne][i-1]=0;
			}
		}
	}
}




/**
	Décale tous les éléments d'une ligne vers la gauche ou la droite
    @param tab Tableau du jeu.
    @param ligne Numéro de la ligne.
	@param gauche Si vrai, décale vers la gauche, sinon vers la droite.
*/
public static void decalerLigne(int [][] tab ,int ligne,boolean gauche){  
	if (gauche) {     //Vers la gauche
		for(int i=0;i<taille;i++){
			if (tab[ligne][i]==0)  {
				for (int j=i+1;j<taille;j++) {
					if (tab[ligne][j]!=0) {
						tab[ligne][i]=tab[ligne][j];
						tab[ligne][j]=0;
						break;
						
					}
				}
			}
		}
	}
	else{           //Vers la droite
		for(int i=taille-1;i>-1;i--){
			if (tab[ligne][i]==0)  {
				for (int j=i-1;j>-1;j--) {
					if (tab[ligne][j]!=0) {
						tab[ligne][i]=tab[ligne][j];
						tab[ligne][j]=0;
						break;
						
					}
				}
			}
		}
	}
}



/**
	Décale tous les éléments d'une colonne vers la gauche ou la droite 
    @param tab Tableau du jeu.
    @param colonne Numéro de la colonne.
	@param haut Si vrai, décale vers le haut, sinon vers le bas.
*/
public static void decalerColonne(int [][] tab ,int colonne,boolean haut){  
	if (haut) {     //Vers le haut
		for(int i=0;i<taille;i++){
			if (tab[i][colonne]==0){
				for (int j=i+1;j<taille;j++) {
					if (tab[j][colonne]!=0) {
						tab[i][colonne]=tab[j][colonne];
						tab[j][colonne]=0;
						break;
						
					}
				}
			}
		}
	}
	else{           //Vers le bas
		for(int i=taille-1;i>-1;i--){
			if (tab[i][colonne]==0) {
				for (int j=i-1;j>-1;j--) {
					if (tab[j][colonne]!=0) {
						tab[i][colonne]=tab[j][colonne];
						tab[j][colonne]=0;
						break;
						
					}
				}
				
			}
		}
	}
}



/**
	Met 2 ou 4 dans le plateau dans une case vide.
    @param tab Tableau du jeu.
  	@param debut Si début est vrai, la méthode met deux nombres au hasard dans le tableau 
*/
public static void mettreUnNombreAuHasarsdDansLeTableau(int [][] tab,boolean debut){
	Random rand = new Random(); 
	int[] nombres = {2,4};
	
	int leNombre=nombres[rand.nextInt(2)];
	
	ArrayList<Coordonees> tableauDeZeros=trouverCasesDontLaValeurVautZero(tab);  //Récupération du tableau des Coordonnes ou il y a des zeros
	
	int index = rand.nextInt(tableauDeZeros.size());
	Coordonees coordonees = tableauDeZeros.get(index);                            //Choix de coordonées au hasard
	
	tab[coordonees.x][coordonees.y]=leNombre;
	
	if (debut) mettreUnNombreAuHasarsdDansLeTableau(tab,false);
	
}




/**
    Renvoie une ArrayList de Coordonees ou chaque Coordonnee est un couple x,y pour lequel tab[x][y]=0
    @param tab Tableau du jeu
    @return Arraylist des coordonnees de zeros
*/
public static ArrayList<Coordonees> trouverCasesDontLaValeurVautZero(int [][] tab){
	ArrayList<Coordonees> tableauDeZeros = new ArrayList<Coordonees>();
	for(int i=0;i<tab.length;i++){
		for(int j=0;j<tab.length;j++){
			if (tab[i][j]==0) {
				tableauDeZeros.add(new Coordonees(i,j));
			}
		}
	}
	
	return tableauDeZeros;
}


/**
	Compare deux tableaux et renvoie true en cas d'égalité
    @param tableauPrecedent Tableau du jeu avant deplacement 
	@param tableauActuel Tableau du jeu apres deplacement
    @return vrai si le deplacement n'a pas change le tableau, sinon faux
*/
public static boolean lesDeuxTableauxSontEgaux(int[][] tableauPrecedent,int[][] tableauActuel){
	for(int i=0;i<taille;i++){
		for(int j=0;j<taille;j++){
			if (tableauPrecedent[i][j]!=tableauActuel[i][j]) return false;
		}
	}
	return true;
}



/**
	Méthodee inutilisée actuellemnent mais elle permettait d'afficher un tableau dans la console de maniere simple pour tester différentes méthodes telles que additionnerLigne etc... 
    @param tab Tableau du jeu
*/
public static void afficher(int [][] tab ){
	String txt="";
	for(int i=0;i<tab.length;i++){
		for(int j=0;j<tab.length;j++){
			txt+=tab[i][j]+" ";
		}
	txt+="\n";
	}
	System.out.println(txt);
}


/**
	Renvoie la copie d'un tableau int [][]
    @param tab Tableau du jeu
    @return un tableau
*/
public static int [][] copierTableau(int [][] tab){
	int[][] t = new int [taille][taille];
	for(int i=0;i<taille;i++){
		for(int j=0;j<taille;j++){
			t[i][j]=tab[i][j];
		}
	}
	return t;
	
}


/**
	Renvoie true si le nombre passé en paramètre est présent sur le plateau. Utilise notamment pour les nombres 2048 et 0
    @param nombre Nombre a tester dans le jeu
	@param tab Tableau du jeu
    @return vrai si le nombre est present dans le jeu, faux sinon
*/
public static boolean verifierPresenceNombreSurLePlateau(int nombre,int [][] tab){
	for(int i=0;i<taille;i++){
		for(int j=0;j<taille;j++){
			if (tab[i][j]==nombre) return true;
		}
	}
	return false;
}


/**
	Affiche une boite de dialogue proposant de quitter ou de recommencer une partie
*/
public static void afficherGameOver() throws IOException {
	
		int reponse = JOptionPane.showConfirmDialog(JEU.leJeu,"Vous avez perdu! \n Votre score est de "+JEU.score+"\n Voulez-vous recommencer une partie?","Fin de partie", JOptionPane.YES_NO_OPTION);
		if (reponse == JOptionPane.YES_OPTION){
		JEU.leJeu.dispose();
		JEU.leJeu= new JEU(); 
	}
	else System.exit(0);
	
	
}


/**
	Renvoie true si un mouvement est possible dans le tableau,renvoie false sinon. Utilise pour detecter lorsqu'on a perdu une partie
	@param tab Tableau du jeu
    @return vrai ou faux
*/
public static boolean detecterSiMouvementPossible(int[][] tab){
	for(int i=0;i<taille;i++){
		for(int j=0;j<taille-1;j++){
			if (tab[i][j]==tab[i][j+1]) return true;
			if (tab[j][i]==tab[j+1][i]) return true;
		}
	}
	return false;
}


/**
	Realise un tour complet selon le sens choisi en ayant recours aux  methodes precedentes
	@param tab Tableau du jeu
	@param direction Direction selon laquelle on veut jouer
*/
public static void tourComplet(int [][] tab,char direction) throws IOException{
	
	//Si la tuile 2048 est présente, on signale au joueur qu'il a gagné et on lui signale qu'il peut continuer
	if ( !JEU.gagne && verifierPresenceNombreSurLePlateau(2048,tab)) {
	  JOptionPane.showMessageDialog(null,"Vous avez gagné ! \n\n Vous pouvez continuer de jouer en cliquant sur ok!");
	  JEU.gagne=true;
	}
	
	
	int [][] tableauPrecedent = copierTableau(tab);
	if (direction=='g'){
		for(int i=0;i<taille;i++) {
			decalerLigne(tab ,i,true);
			additionnerLigne(tab ,i,true);
			decalerLigne(tab ,i,true);
		}
	}
	else {
		if (direction=='d'){
			for(int i=0;i<taille;i++) {
				decalerLigne(tab ,i,false);
				additionnerLigne(tab ,i,false);
				decalerLigne(tab ,i,false);
			}
		}
		else {
			if (direction=='h'){
				for(int i=0;i<taille;i++) {
					decalerColonne(tab ,i,true);
					additionnerColonne(tab ,i,true);
					decalerColonne(tab ,i,true);
				}
			}
			else {
				if (direction=='b'){
						for(int i=0;i<taille;i++) {
							decalerColonne(tab ,i,false);
							additionnerColonne(tab ,i,false);
							decalerColonne(tab ,i,false);
						}
				}	
			}
		}
	}
	
	
	JEU.labelScore.setText("Score : "+JEU.score);
	
	//Il ne faut rajouter un nombre que si le tableau a changé après un coup
	if (!lesDeuxTableauxSontEgaux(tab,tableauPrecedent)) mettreUnNombreAuHasarsdDansLeTableau(tab,false);
	else {
		//Les deux tableaux sont les memes avant et apres un coup: soit il n'y a pas de zero sur le plateau et on a peut etre perdu si aucun mouvement n'est possible
		//Soit il y a des zeros et la partie continue 
		if (!verifierPresenceNombreSurLePlateau(0,tab)) {
			if (!detecterSiMouvementPossible(tab)) afficherGameOver();
		}
	}
}
	
/**
*Place un 1 dans le plateau au hasard (est toujours appelée en début de partie)
*Le 1 ainsi placé permet de faire une tuile "morte" qui ne peut pas s'additionner avec les autres pour gener le joueur
*/
public void ajouterUnObstacleAuHasardSurLePlateau(){
	Random rand = new Random();
	this.table[rand.nextInt(JEU.tailleDuPlateau)][rand.nextInt(JEU.tailleDuPlateau)]=1;
}



/** 
 * Affichage du jeu 2048
 */
public void paintComponent(Graphics g) {
	// Dessine la grille du jeu
	super.paintComponent(g);
	g.setColor(Color.white);
	for(int i = 0; i <= taille; i++) 
    		g.drawLine(i*COTECASE, 0, i*COTECASE, taille*COTECASE);
	for(int j = 0; j <= taille; j++) 
    		g.drawLine(0, j*COTECASE, taille*COTECASE, j*COTECASE);


	// Dessine les eventuelles images en fonction du tableau
    for(int i = 0; i < taille; i++) {
	  for(int j = 0; j < taille; j++) {
	    switch(Plateau.table[i][j]) {
		    case 1: g.fillRect(j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1); break;
		    case 2: g.drawImage(imageDeux,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null); break;
		    case 4 :g.drawImage(imageQuatre,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 8: g.drawImage(imageHuit,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 16: g.drawImage(imageSeize,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 32: g.drawImage(image32,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 64: g.drawImage(image64,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 128: g.drawImage(image128,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 256: g.drawImage(image256,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 512: g.drawImage(image512,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 1024: g.drawImage(image1024,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 2048: g.drawImage(image2048,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 4096: g.drawImage(image4096,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 8192: g.drawImage(image8192,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 16384: g.drawImage(image16384,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    case 32768: g.drawImage(image32768,j*COTECASE+1,i*COTECASE+1,COTECASE-1,COTECASE-1,null);break;
		    
		    default: break;
	    }	
	  }
    }	
}




} //Fin classe Plateau
