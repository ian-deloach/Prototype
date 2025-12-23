package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent event) {
        System.out.println("clicked test die");
    }
}
