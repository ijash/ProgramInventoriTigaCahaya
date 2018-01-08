/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author ijash
 */
public class ScreenDimension {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public Dimension getDim() {
        return dim;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }
    
}
