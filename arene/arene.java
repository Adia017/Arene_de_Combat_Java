package arene;

import personnage.personnage;

/**
 * La classe {@code arene} représente une arène où deux personnages s'affrontent
 * dans un combat.
 * <p>
 * L'arène organise le combat entre les deux personnages, gérant les tours
 * d'attaque jusqu'à ce qu'un des personnages soit vaincu.
 * </p>
 */
public class arene {
    
    private personnage personnage1;
    private personnage personnage2;

    /**
     * Constructeur de la classe {@code arene}.
     * 
     * @param personnage1 le premier personnage participant au combat
     * @param personnage2 le second personnage participant au combat
     */
    public arene(personnage personnage1, personnage personnage2) {
        this.personnage1 = personnage1;
        this.personnage2 = personnage2;
    }

    /**
     * Organise le combat entre les deux personnages.
     * <p>
     * Chaque personnage attaque l'autre à tour de rôle jusqu'à ce qu'un des
     * personnages ait 0 points de vie ou moins. Le combat se termine dès
     * qu'un des personnages ne peut plus combattre.
     * </p>
     * 
     * @return {@code true} si le combat est terminé, {@code false} sinon
     */
    public boolean combat() {
        personnage1.afficherEtat();
        personnage2.afficherEtat();

        while (personnage1.peutCombattre() && personnage2.peutCombattre()) {
            personnage1.attaquer(personnage2);

            // Vérifie si le personnage2 peut encore combattre après avoir subi une attaque
            if (!personnage2.peutCombattre()) {
                return true; // Le combat se termine
            }
            personnage2.attaquer(personnage1);

            if (!personnage1.peutCombattre()) {
                return true;
            }
        }
        return false; // Le combat continue
    }
}
