package Model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Carte {
    public String nom;
    public int width = 100;
    public int heigth = 150;

    // Constructeur de la carte
    public Carte(String nom) {
        this.nom = nom;
    }

    // Dessiner la carte à une position (x, y) avec un facteur d'échelle
    public void dessiner(Graphics g, int xPosition, int yPosition, float scale) {
        // Calculer la taille redimensionnée en fonction de l'échelle
        int scaledWidth = (int) (width * scale);
        int scaledHeight = (int) (heigth * scale);

        // Dessiner la carte avec les coordonnées et la taille redimensionnée
        g.setColor(Color.BLACK);
        g.drawRect(xPosition, yPosition, scaledWidth, scaledHeight); // Dessiner le contour de la carte
        g.setColor(Color.WHITE); // Par exemple, le fond blanc pour la carte
        g.fillRect(xPosition + 1, yPosition + 1, scaledWidth - 1, scaledHeight - 1); // Remplissage de la carte
        g.setColor(Color.BLACK);
        g.drawString(nom, xPosition + 10, yPosition + 20); // Dessiner le nom de la carte
    }
}