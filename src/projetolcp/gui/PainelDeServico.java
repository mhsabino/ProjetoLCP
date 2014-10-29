/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetolcp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private Servico servico;
    private String valores[];
    private DefaultListModel model;
    
    public PainelDeServico() {
        rolDeServicos = new RolDeServicos();
        layout = new BorderLayout();
        this.setLayout(layout);
        inicia_lista_de_servicos();
    }
    
    private void inicia_lista_de_servicos(){
        model = new DefaultListModel();
        rolDeServicos.inicia();
        for (Servico s : rolDeServicos.todosOsServicos()){
            model.addElement(s);
        }
        list = new JList(model);
        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Color corDeSelecaoDaLista = new Color(0, 0, 1, 0.2f);
        list.setSelectionBackground(corDeSelecaoDaLista);
        list.setSize(new Dimension(600,700));
        this.add(new JScrollPane(list));
        
        JPanel pb = new JPanel();
        pb.setLayout(new GridLayout());
        
        /*list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                //list.setBackground(Color.red);
                
                //System.out.println(valores[0] + "/ " + valores[1] + "/ " + valores[2] + "/ " + valores[3]);
                
            }
        });   */

        /*************************************************************/        
        
        JButton btnAdicionaServico = new JButton("Adicionar Serviço");

        btnAdicionaServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new TelaDeAdicionarServico(new Dimension(400,400),"Adicionar Serviço");
            }
        });             
        
        pb.add(btnAdicionaServico);
        
        /*************************************************************/
        
        JButton btnRemoveServico = new JButton("Remover Serviço");

        btnRemoveServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (list.getModel().getSize() !=  0){
                
                    valores = list.getSelectedValue().toString().split(" ");

                    if (valores.length == 3){
                        servico = new Servico(valores[1],Double.parseDouble(valores[2]));
                    }else if (valores.length == 4){
                        servico = new Servico(valores[1],valores[2], Double.parseDouble(valores[3]));
                    }else {
                        servico = new Servico(valores[1],valores[2], valores[3], Double.parseDouble(valores[4]));
                    }
               
                    rolDeServicos.removeServico(servico);
                    model.removeElementAt(list.getSelectedIndex());
                    list.setSelectedIndex(0);                    
                }
            }
        });        
        
        pb.add(btnRemoveServico);
        
        /*************************************************************/
        
        JButton btnAlteraPreco = new JButton("Alterar Preço");

        btnAlteraPreco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new TelaDeAlterarPrecoDeServico(new Dimension(400,400),"Alterar Preço de Serviço");
            }
        });
        
        pb.add(btnAlteraPreco);
        
        /*************************************************************/        
        this.add(pb,BorderLayout.SOUTH);
        
    
    }

    
}
