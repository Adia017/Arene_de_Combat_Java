package personnage;

/**
 * La classe {@code mage} représente un personnage de type mage dans le jeu.
 * <p>
 * Le mage dispose de points de mana et de la capacité de lancer des sorts
 * qui infligent des dégâts à ses adversaires. La puissance des sorts est
 * affectée par la quantité de mana disponible.
 * </p>
 */
public class mage extends personnage {

    private int mana;
    private int puissanceSort;

    /**
     * Constructeur de la classe {@code mage}.
     * 
     * @param nom          le nom du mage
     * @param PV           les points de vie du mage
     * @param attaque      les points d'attaque du mage
     * @param mana         les points de mana du mage
     * @param puissanceSort la puissance du sort du mage
     */
    public mage(String nom, int PV, int attaque, int mana, int puissanceSort) {
        super(nom, PV, attaque);
        this.mana = mana;
        this.puissanceSort = puissanceSort;
    }

    /**
     * Retourne les points de mana du mage.
     * 
     * @return les points de mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * Retourne la puissance du sort du mage.
     * 
     * @return la puissance du sort
     */
    public int getpuissanceSort() {
        return puissanceSort;
    }

    /**
     * Lance un sort sur un personnage cible, infligeant des dégâts.
     * <p>
     * Si le mage a du mana disponible, les dégâts sont réduits par la valeur
     * du mana, et 5 points de mana sont consommés à chaque utilisation.
     * </p>
     * 
     * @param perso_cible le personnage ciblé par le sort
     */
    public void attaquer(personnage perso_cible) {
        int degats = this.getAttaque();
        if (mana > 0) {
            degats -= mana; // Réduit les dégâts en utilisant le mana
            perso_cible.subirDegats(degats);
            mana -= 5; // Consomme 5 points de mana
            if (this.mana < 0) {
                this.mana = 0; // Empêche les points de mana de devenir négatifs
            }
            System.out.println(this.getNom() + " a lancé un sort qui inflige " + degats + " dégâts à " + perso_cible.getNom());
            perso_cible.afficherEtat();
        } else {
            System.out.println(perso_cible.getNom() + " est déjà vaincu !");
        }
    }

    /**
     * Permet au mage de subir des dégâts d'un adversaire.
     * <p>
     * Les dégâts subis sont réduits par la valeur du mana du mage. Si les dégâts
     * sont supérieurs à zéro après réduction, les points de vie du mage sont
     * diminués en conséquence.
     * </p>
     * 
     * @param degats les dégâts infligés au mage
     */
    public void subirDegats(int degats) {
        int vrai_degats = degats - this.mana;

        if (vrai_degats > 0) {
            super.subirDegats(vrai_degats);
            System.out.println(this.getNom() + " neutralise l'attaque de " + (degats - vrai_degats) + " dégâts grâce à son mana !!");
            System.out.println("Dégâts réels : " + vrai_degats);
            afficherEtat();
        } else {
            System.out.println(this.getNom() + " attaque complètement bloquée !");
        }
    }
}
