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
import java.io.Console;
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
public class PainelDeServico extends JPanel {

    private JList lista;
    private RolDeServicos rolDeServicos;
    private Servico servico;
    private String valores[];
    private DefaultListModel model;

    public PainelDeServico() {
        rolDeServicos = new RolDeServicos();
        this.setLayout(new BorderLayout());
        inicia_lista_de_servicos();
    }

    private void inicia_lista_de_servicos() {
        model = new DefaultListModel();
        rolDeServicos.inicia();
        for (Servico s : rolDeServicos.todosOsServicos()) {
            model.addElement(s);
        }
        lista = new JList(model);
        lista.setSelectedIndex(0);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Color corDeSelecaoDaLista = new Color(0, 0, 1, 0.2f);
        lista.setSelectionBackground(corDeSelecaoDaLista);
        lista.setSize(new Dimension(600, 700));
        this.add(new JScrollPane(lista));
        //pegaServicoSelecionado();        
        JPanel pb = new JPanel();
        pb.setLayout(new GridLayout());

        lista.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
             //pegaServicoSelecionado();
         }
         }); 

        /**
         * **********************************************************
         */
        JButton btnAdicionaServico = new JButton("Adicionar Serviço");

        btnAdicionaServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaDeAdicionarServico(new Dimension(400, 400), "Adicionar Serviço");
            }
        });

        pb.add(btnAdicionaServico);

        /**
         * **********************************************************
         */
        JButton btnRemoveServico = new JButton("Remover Serviço");

        btnRemoveServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lista.getModel().getSize() != 0) {

                    pegaServicoSelecionado();
                    model.removeElementAt(lista.getSelectedIndex());
                    rolDeServicos.removeServico(servico);
                    lista.setSelectedIndex(0);
                }
            }
        });

        pb.add(btnRemoveServico);

        /**
         * **********************************************************
         */
        JButton btnAlteraPreco = new JButton("Alterar Preço");

        btnAlteraPreco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (servico == null){
                    pegaServicoSelecionado();
                }
                
                System.out.println("Servico " + servico);
                //new TelaDeAlterarPrecoDeServico(new Dimension(400, 100), servico, "Alterar Preço de Serviço");
                
            }
        });

        pb.add(btnAlteraPreco);

        /**
         * **********************************************************
         */
        this.add(pb, BorderLayout.SOUTH);


    }

    private void pegaServicoSelecionado() {
        System.out.println("item selecionado: " + lista.getSelectedValue());
        valores = lista.getSelectedValue().toString().split(" ");

        if (valores.length == 3) {
            servico = new Servico(valores[1], Double.parseDouble(valores[2]));
        } else if (valores.length == 4) {
            servico = new Servico(valores[1], valores[2], Double.parseDouble(valores[3]));
        } else {
            servico = new Servico(valores[1], valores[2], valores[3], Double.parseDouble(valores[4]));
        }
    }
}
