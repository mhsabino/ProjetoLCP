/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetolcp.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import lav.RolDeServicos;
import lav.Servico;

/**
 *
 * @author Qbex
 */
public class PainelDeServico extends JPanel{

    private JList list;
    private LayoutManager layout;
    private RolDeServicos rolDeServicos;
    private PainelDeServico p;
    
    public PainelDeServico() {
        rolDeServicos = new RolDeServicos();
        layout = new BorderLayout();
        this.setLayout(layout);
        inicia_lista_de_servicos();
        p = this;
    }
    
    private void inicia_lista_de_servicos(){
        list = new JList(obtem_array_de_servicos());
        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Color corDeSelecaoDaLista = new Color(0, 0, 1, 0.2f);
        list.setSelectionBackground(corDeSelecaoDaLista);
        list.setSize(new Dimension(600,700));
        this.add(new JScrollPane(list));
        JPanel pb = new JPanel();
        pb.setLayout(new GridLayout());
        pb.add(new Button("Adicionar"));
        pb.add(new Button("Remover"));
        JButton btnAlteraPreco = new JButton("Alterar Preço");

        btnAlteraPreco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new TelaDeAlterarPrecoDeServico();
            }
        });
        
        pb.add(btnAlteraPreco);
        this.add(pb,BorderLayout.SOUTH);
        /*list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //list.setBackground(Color.red);
            }
        });       */ 
    }
    
    private Servico[] obtem_array_de_servicos(){
        rolDeServicos.inicia();
        return rolDeServicos.todosOsServicos();
    }
    
    
}
