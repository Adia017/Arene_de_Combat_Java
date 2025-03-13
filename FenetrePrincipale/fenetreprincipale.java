package FenetrePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import personnage.*;
import arene.arene;

/**
 * La classe {@code fenetreprincipale} représente l'interface graphique principale
 * pour la sélection des personnages et la gestion du combat.
 * <p>
 * Cette fenêtre permet à l'utilisateur de sélectionner deux personnages, puis
 * de les faire combattre dans une arène. Chaque personnage peut être un
 * Guerrier, un Mage ou un Archer, avec des caractéristiques spécifiques.
 * </p>
 */
public class fenetreprincipale {

    private JFrame frame;
    private JButton btnGuerrier;
    private JButton btnMage;
    private JButton btnArcher;
    private JButton btnCommencerCombat;
    private JButton btnRetour;
    private personnage personnage1;
    private personnage personnage2;
    private boolean choixPerso1 = true; 
    private JLabel labelSelection;

    /**
     * Constructeur de la classe {@code fenetreprincipale}.
     * <p>
     * Crée la fenêtre principale avec les boutons de sélection des personnages
     * et l'interface utilisateur nécessaire pour débuter le combat.
     * </p>
     */
    public fenetreprincipale() {
        frame = new JFrame("Sélection des personnages");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        frame.add(panel);
        
        btnGuerrier = new JButton("Guerrier");
        btnMage = new JButton("Mage");
        btnArcher = new JButton("Archer");
        
        panel.add(btnGuerrier);
        panel.add(btnMage);
        panel.add(btnArcher);
        
        labelSelection = new JLabel("Sélectionnez le premier personnage.");
        panel.add(labelSelection);

        btnCommencerCombat = new JButton("Commencer le combat");
        btnCommencerCombat.setEnabled(false);
        btnCommencerCombat.addActionListener(e -> lancerCombat());
        panel.add(btnCommencerCombat);

        btnGuerrier.addActionListener(e -> choisirPerso(new guerrier("Guerrier", 100, 10, 5)));
        btnMage.addActionListener(e -> choisirPerso(new mage("Mage", 100, 10, 5, 3)));
        btnArcher.addActionListener(e -> choisirPerso(new archer("Archer", 100, 6, 4, 10)));

        frame.setVisible(true);
    }

    /**
     * Permet de sélectionner un personnage.
     * <p>
     * Cette méthode est appelée lors du clic sur un bouton de personnage.
     * Le personnage sélectionné est attribué soit à {@code personnage1} soit
     * à {@code personnage2}, selon l'état de {@code choixPerso1}.
     * </p>
     * 
     * @param perso le personnage sélectionné
     */
    private void choisirPerso(personnage perso) {
        if (choixPerso1) {
            personnage1 = perso;
            labelSelection.setText("Premier personnage sélectionné: " + perso.getNom() + ". Choisissez le second personnage !");
            choixPerso1 = false;
        } else {
            personnage2 = perso;
            labelSelection.setText("Second personnage sélectionné: " + perso.getNom() + ". Prêt à commencer !");
            btnCommencerCombat.setEnabled(true);
        }
    }

    /**
     * Lance le combat entre les deux personnages sélectionnés.
     * <p>
     * Cette méthode crée une nouvelle fenêtre de combat, affiche les points de vie
     * des personnages et permet de lancer des tours de combat jusqu'à la fin.
     * </p>
     */
    private void lancerCombat() {
        JFrame combatFrame = new JFrame("Combat");
        combatFrame.setSize(600, 400);
        combatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel combatPanel = new JPanel();
        combatPanel.setLayout(new FlowLayout());
        combatFrame.add(combatPanel);

        JLabel labelPV1 = new JLabel(personnage1.getNom() + " PV: " + personnage1.getPV());
        JLabel labelPV2 = new JLabel(personnage2.getNom() + " PV: " + personnage2.getPV());
        combatPanel.add(labelPV1);
        combatPanel.add(labelPV2);

        arene CombatArene = new arene(personnage1, personnage2);

        JButton btnLancerTour = new JButton("Lancer le tour");
        combatPanel.add(btnLancerTour);

        btnLancerTour.addActionListener(e -> {
            boolean combatTermine = CombatArene.combat();
            labelPV1.setText(personnage1.getNom() + " PV: " + personnage1.getPV());
            labelPV2.setText(personnage2.getNom() + " PV: " + personnage2.getPV());

            if (combatTermine) {
                String result;
                if (personnage1.getPV() > 0) {
                    result = personnage1.getNom() + " a gagné ! " + personnage2.getNom() + " a perdu !";
                } else {
                    result = personnage2.getNom() + " a gagné ! " + personnage1.getNom() + " a perdu !";
                }

                JOptionPane.showMessageDialog(combatFrame, result, "Fin du combat", JOptionPane.INFORMATION_MESSAGE);
                btnLancerTour.setEnabled(false);
            }
        });

        btnRetour = new JButton("Retour à l'écran principal");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                combatFrame.dispose();
                new fenetreprincipale();
            }
        });
        combatPanel.add(btnRetour);

        frame.setVisible(false);
        combatFrame.setVisible(true);
    }

    /**
     * Méthode principale qui lance l'application.
     * 
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        new fenetreprincipale();
    }
}
