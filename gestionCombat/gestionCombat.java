package gestionCombat;
import personnage.personnage;

public class gestionCombat{
    private personnage joueur1;
    private personnage joueur2;
    private int tour;

    public gestionCombat(personnage joueur1, personnage joueur2){
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.tour=1;
    }

     /**
     * Effectue un tour de combat.
     * Chaque personnage attaque à son tour.
     * Affiche les actions et vérifie les PV pour déclarer un gagnant si nécessaire.
     *
     * @return true si le combat continue, false si un des personnages a perdu.
     */

    public boolean lancerTour(){
        System.out.println("Tour: " + tour);
        
        //joueur1 attaque joueur2
        if(joueur1.getPV()>0){
            joueur1.attaquer(joueur2);
        }

        // vérifiez si joueur2 KO
        if(joueur2.getPV()<=0){
            System.out.println(joueur1.getNom()+ " a gagné le combat ! ");
            System.out.println(joueur2.getNom()+ " a perdu. une prochaine fois peut-être ;) ");
            return false; // fin du combat 
        }

        if(joueur2.getPV()>0){
            joueur2.attaquer(joueur1);
        }
        // vérifiez si joueur1 KO

        if(joueur1.getPV()<=0){
            System.out.println(joueur2.getNom()+ " a gagné le combat ! ");
            System.out.println(joueur1.getNom()+ " a perdu. une prochaine fois peut-être ;)");
            return false; // fin du combat 
        }
        // Afficher l'état des personnages à la fin du tour
        joueur1.afficherEtat();
        joueur2.afficherEtat();

        // Tour suivant
        tour++;
        return true;

    }
}

