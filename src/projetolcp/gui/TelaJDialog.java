/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetolcp.gui;

import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.JDialog;

/**
 *
 * @author aluno
 */
public class TelaJDialog extends JDialog {
    
    public TelaJDialog(Dimension dimensao, String tituloJanela){
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle(tituloJanela);
        setPreferredSize(dimensao);
        setResizable(false);
        setVisible(true);           
        pack();
        setLocationRelativeTo(null);
     
    }
    
}
