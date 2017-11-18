package tigacahaya.gui;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchAdapter {
    private GUI gui;


    SearchAdapter (GUI gui) {
        this.gui = gui;
        initSearchAction();
    }

    private void initSearchAction() {
        JTextField field = gui.SearchField;
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("KeyPressed: "+field.getText());
                super.keyTyped(e);
            }
        });
    }


}
