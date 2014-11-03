/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolcp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lav.Servico;

/**
 *
 * @author Mateus
 */
public class TelaDeAlterarPrecoDeServico extends JDialog{
    
    private Container painelDeConteudo;
    private Servico servico;
    
    public TelaDeAlterarPrecoDeServico(Dimension dimensaoJanela, Servico servico, String tituloJanela){
        //super(dimensaoJanela, tituloJanela);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle(tituloJanela);
        setPreferredSize(dimensaoJanela);
        setResizable(false);
        painelDeConteudo = this.getContentPane();
        painelDeConteudo.setSize(new Dimension(400,100));
        painelDeConteudo.setBounds(new Rectangle(400, 100));
        painelDeConteudo.setLayout(new BoxLayout(painelDeConteudo, WIDTH));
        //painelDeConteudo.setBackground(Color.red);
        criaFormulario();
        pack();
        setLocationRelativeTo(null);       
        setVisible(true);
        this.servico = servico;

    }
    
    private void criaFormulario(){
        JLabel labelPreco = new JLabel("Digite o novo preço: ");
        painelDeConteudo.add(labelPreco);
        double d = servico.getPreco();
        JTextField preco = new JTextField();
        painelDeConteudo.add(preco);
        JButton botaoAlterar = new JButton("Alterar");
        painelDeConteudo.add(botaoAlterar);   
    }
}
