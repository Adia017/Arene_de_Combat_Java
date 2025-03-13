package personnage;

/**
 * La classe {@code archer} représente un personnage de type archer.
 * <p>
 * L'archer utilise des flèches pour attaquer ses ennemis. Il possède également
 * une agilité qui lui permet de réduire les dégâts subis.
 * </p>
 */
public class archer extends personnage {

    private int fleches;
    private int agilite;

    /**
     * Constructeur de la classe {@code archer}.
     * 
     * @param nom     le nom de l'archer
     * @param PV      les points de vie de l'archer
     * @param attaque les points d'attaque de l'archer
     * @param agilite la capacité de l'archer à éviter ou réduire les dégâts
     * @param fleches le nombre de flèches dont dispose l'archer
     */
    public archer(String nom, int PV, int attaque, int agilite, int fleches) {
        super(nom, PV, attaque);
        this.fleches = fleches;
        this.agilite = agilite;
    }

    /**
     * Retourne le nombre de flèches restantes.
     * 
     * @return le nombre de flèches
     */
    public int getfleche() {
        return fleches;
    }

    /**
     * Effectue une attaque contre un personnage cible.
     * <p>
     * L'archer peut attaquer uniquement s'il a encore des flèches et s'il peut
     * combattre. Une flèche est utilisée par attaque.
     * </p>
     * 
     * @param perso_cible le personnage cible de l'attaque
     */
    public void attaquer(personnage perso_cible) {
        if (!this.peutCombattre()) {
            System.out.println(this.getNom() + " ne peut pas attaquer car il n'a plus de flèches ou est hors combat !");
            return;
        }
    
        if (!perso_cible.peutCombattre()) {
            System.out.println(perso_cible.getNom() + " est déjà hors combat et ne peut pas être attaqué.");
            return;
        }

        if (fleches > 0) {
            int degats = this.getAttaque(); // Dégâts causés par une flèche
            perso_cible.subirDegats(degats);
            fleches -= 1;
            System.out.println(this.getNom() + " a tiré une flèche et inflige : " + degats + " dégâts à " + perso_cible.getNom() + " . Flèches restantes: " + this.fleches);
        } else {
            System.out.println(this.getNom() + " n'a plus de flèche pour attaquer!");
        }
    }

    /**
     * Permet à l'archer de subir des dégâts.
     * <p>
     * Les dégâts subis sont réduits par la valeur de l'agilité. Si les dégâts
     * sont inférieurs ou égaux à l'agilité, l'attaque est complètement évitée.
     * </p>
     * 
     * @param degats les dégâts infligés à l'archer
     */
    @Override
    public void subirDegats(int degats) {
        int vrai_degats = degats - this.agilite;

        if (vrai_degats > 0) {
            super.subirDegats(vrai_degats);
            System.out.println(this.getNom() + " bloque l'attaque de " + vrai_degats + " dégâts grâce à son agilité !!");
            System.out.println("Dégâts réels : " + vrai_degats);
        } else {
            System.out.println("Attaque complètement évitée !");
        }
    }

    /**
     * Vérifie si l'archer peut encore combattre.
     * <p>
     * Un archer peut combattre s'il a encore des points de vie et au moins une
     * flèche restante.
     * </p>
     * 
     * @return {@code true} si l'archer peut combattre, {@code false} sinon
     */
    public boolean peutCombattre() {
        return this.getPV() > 0 && this.fleches > 0;
    }
}
