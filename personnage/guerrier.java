package personnage;

/**
 * La classe {@code guerrier} représente un personnage de type guerrier.
 * <p>
 * Le guerrier dispose d'une armure qui réduit les dégâts subis lors des
 * attaques. Il utilise principalement des attaques physiques avec son épée.
 * </p>
 */
public class guerrier extends personnage {

    private int armure;

    /**
     * Constructeur de la classe {@code guerrier}.
     * 
     * @param nom    le nom du guerrier
     * @param PV     les points de vie du guerrier
     * @param attaque les points d'attaque du guerrier
     * @param armure  les points d'armure du guerrier
     */
    public guerrier(String nom, int PV, int attaque, int armure) {
        super(nom, PV, attaque);
        this.armure = armure;
    }

    /**
     * Retourne les points d'armure du guerrier.
     * 
     * @return les points d'armure
     */
    public int getarmure() {
        return armure;
    }

    /**
     * Permet au guerrier de subir des dégâts en tenant compte de son armure.
     * <p>
     * Les dégâts subis sont réduits par la valeur de l'armure. Si les dégâts
     * sont inférieurs à l'armure, aucun dégât n'est infligé.
     * </p>
     * 
     * @param degats les dégâts infligés au guerrier
     */
    public void subirDegats(int degats) {
        int vrai_degats = degats - armure;

        if (vrai_degats < 0) {
            vrai_degats = 0; // Empêche les dégâts d'être négatifs
        }
        super.subirDegats(vrai_degats);
        System.out.println(this.getNom() + " bloque l'attaque de " + vrai_degats + " dégâts grâce à son armure !!");
    }

    /**
     * Le guerrier attaque un personnage cible.
     * <p>
     * L'attaque est effectuée uniquement si le guerrier et la cible peuvent
     * encore combattre. Si la cible est déjà hors combat, l'attaque n'est pas
     * effectuée.
     * </p>
     * 
     * @param perso_cible le personnage cible de l'attaque
     */
    public void attaquer(personnage perso_cible) {
        if (!this.peutCombattre()) {
            System.out.println(this.getNom() + " ne peut pas attaquer car il est hors combat !");
            return;
        }
    
        if (!perso_cible.peutCombattre()) {
            System.out.println(perso_cible.getNom() + " est déjà hors combat et ne peut pas être attaqué.");
            return;
        }
        int degats = this.getAttaque();
        perso_cible.subirDegats(degats);
        System.out.println(this.getNom() + " attaque avec son épée " + perso_cible.getNom() + " et lui inflige " + degats + " dégâts.");
    }
}
