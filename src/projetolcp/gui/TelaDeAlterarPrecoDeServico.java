/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolcp.gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 *
 * @author Mateus
 */
public class TelaDeAlterarPrecoDeServico extends JDialog{
    
    public TelaDeAlterarPrecoDeServico(){
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Alterar Preço de Serviço");
        setPreferredSize(new Dimension(400,400));
        setLocationRelativeTo(null);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-400/2, dim.height/2-400/2);   
        pack();
        setVisible(true);
    }
    
}
