package personnage;

/**
 * La classe abstraite {@code personnage} représente un personnage générique
 * avec des points de vie (PV), une force d'attaque et un nom.
 * <p>
 * Cette classe sert de base pour les différents types de personnages
 * spécifiques, comme le guerrier, le mage ou l'archer.
 * </p>
 */
public abstract class personnage {
    
    private int PV;
    private int attaque;
    private String nom_perso;

    /**
     * Constructeur de la classe {@code personnage}.
     * 
     * @param nom     le nom du personnage
     * @param PV      les points de vie du personnage
     * @param attaque la force d'attaque du personnage
     */
    public personnage(String nom, int PV, int attaque) {
        this.nom_perso = nom;
        this.PV = PV; // Les PV sont initialisés à 100 par défaut
        this.attaque = attaque;
    }

    /**
     * Retourne le nom du personnage.
     * 
     * @return le nom du personnage
     */
    public String getNom() {
        return nom_perso;
    }

    /**
     * Retourne les points de vie du personnage.
     * 
     * @return les points de vie
     */
    public int getPV() {
        return PV;
    }

    /**
     * Retourne la force d'attaque du personnage.
     * 
     * @return la force d'attaque
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Méthode abstraite pour attaquer un personnage cible.
     * <p>
     * Chaque sous-classe doit implémenter cette méthode pour définir le
     * comportement spécifique de l'attaque.
     * </p>
     * 
     * @param perso_cible le personnage cible de l'attaque
     */
    public abstract void attaquer(personnage perso_cible);

    /**
     * Réduit les points de vie du personnage en fonction des dégâts subis.
     * <p>
     * Si les dégâts sont supérieurs à zéro, ils sont soustraits des points de vie
     * du personnage. Les points de vie sont ensuite ajustés pour rester dans les
     * limites de 0 à 100.
     * </p>
     * 
     * @param degats les dégâts subis
     */
    public void subirDegats(int degats) {
        if (degats > 0) {
            PV -= degats;
        }
        if (this.PV < 0) {
            this.PV = 0;
        }
        if (this.PV > 100) {
            this.PV = 100;
        }
    }

    /**
     * Affiche l'état actuel du personnage, y compris son nom, ses points de vie
     * et sa force d'attaque.
     * <p>
     * Si les points de vie sont à zéro, le personnage est déclaré hors combat.
     * </p>
     */
    public void afficherEtat() {
        if (this.PV == 0) {
            System.out.println("Personnage : " + this.nom_perso + " est hors combat.");
        } else {
            System.out.println("Personnage : " + this.nom_perso + " points de vie: " + this.PV + " force d'attaque: " + this.attaque);
        }
    }

    /**
     * Vérifie si le personnage peut encore combattre.
     * 
     * @return {@code true} si les points de vie sont supérieurs à zéro, {@code false} sinon
     */
    public boolean peutCombattre() {
        return PV > 0;
    }
}
