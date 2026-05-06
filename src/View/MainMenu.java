package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    public MenuManager menuManager;
    LabelTitle title;
    PanelAgesOfClash panelDesButtons;
    ButtonAgesOfClash buttonJouer;
    ButtonAgesOfClash buttonCreer;
    ButtonAgesOfClash buttonInfo;
    ButtonAgesOfClash buttonOption;
    ButtonAgesOfClash buttonQuitter;
    PanelAgesOfClash leftWrapper;
    PanelAgesOfClash topPanel;
    
    // GIF de fond
    private Image imageFond;

    public MainMenu(MenuManager menuManager) {
        this.menuManager = menuManager;
        this.setLayout(new BorderLayout());

        // Charger le gif — mets ton fichier dans src/images/menu_bg.gif
        imageFond = new ImageIcon(getClass().getResource("/images/jeu/menu_bg.gif")).getImage();

        title = new LabelTitle("AGES OF CLASH");
        title.setHorizontalAlignment(JLabel.CENTER);

        topPanel = new PanelAgesOfClash(new BorderLayout());
        topPanel.setOpaque(false); // transparent pour voir le gif
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        topPanel.add(title, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        panelDesButtons = new PanelAgesOfClash();
        panelDesButtons.setOpaque(false); // transparent
        panelDesButtons.setLayout(new BoxLayout(panelDesButtons, BoxLayout.Y_AXIS));
        panelDesButtons.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        buttonJouer   = new ButtonAgesOfClash("Jouer");
        buttonCreer   = new ButtonAgesOfClash("Creer un deck");
        buttonInfo    = new ButtonAgesOfClash("Infos");
        buttonOption  = new ButtonAgesOfClash("Option");
        buttonQuitter = new ButtonAgesOfClash("Quitter");

        buttonJouer.addActionListener(menuManager.menuController);
        buttonCreer.addActionListener(menuManager.menuController);
        buttonInfo.addActionListener(menuManager.menuController);
        buttonOption.addActionListener(menuManager.menuController);
        buttonQuitter.addActionListener(menuManager.menuController);

        buttonJouer.setActionCommand("JOUER");
        buttonCreer.setActionCommand("CHOISIS");
        buttonInfo.setActionCommand("INFO");
        buttonOption.setActionCommand("OPTION");
        buttonQuitter.setActionCommand("QUITTER");

        panelDesButtons.add(buttonJouer);
        panelDesButtons.add(Box.createVerticalStrut(10));
        panelDesButtons.add(buttonCreer);
        panelDesButtons.add(Box.createVerticalStrut(10));
        panelDesButtons.add(buttonInfo);
        panelDesButtons.add(Box.createVerticalStrut(10));
        panelDesButtons.add(buttonOption);
        panelDesButtons.add(Box.createVerticalStrut(10));
        panelDesButtons.add(buttonQuitter);

        leftWrapper = new PanelAgesOfClash();
        leftWrapper.setOpaque(false); // transparent
        leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
        leftWrapper.add(Box.createVerticalGlue());
        leftWrapper.add(panelDesButtons);
        leftWrapper.add(Box.createVerticalGlue());

        this.add(leftWrapper, BorderLayout.LINE_START);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageFond != null) {
            g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), this);
        }
    }
}