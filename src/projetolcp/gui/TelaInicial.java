/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetolcp.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author aluno
 */
public class TelaInicial {
    
    private JFrame janela;
    private Container painelDeConteudo;
    private static final int LARGURA_JANELA = 800;
    private static final int ALTURA_JANELA  = 600;
    
    public TelaInicial(){
        constroiJanela("Lavanderia");
        criaTelaInicial();
    }
    
    private void centralizaTela() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        janela.setLocation(dim.width/2-LARGURA_JANELA/2, dim.height/2-ALTURA_JANELA/2);         
    }
    
    private void constroiJanela(String nomeJanela){
        janela = new JFrame(nomeJanela);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setPreferredSize(new Dimension(LARGURA_JANELA,ALTURA_JANELA));
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        painelDeConteudo = janela.getContentPane();
        centralizaTela();
        constroiBarraDeMenu();
    }
    
    private void criaTelaInicial(){
        // desenha a imagem inicial
        ImageIcon icone = new ImageIcon("C:\\Users\\Qbex\\Desktop\\LCP\\ProjetoLCP\\src\\images\\lavanderia_img.jpg");
        JLabel label = new JLabel(icone,SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(LARGURA_JANELA,ALTURA_JANELA));
        painelDeConteudo.add(label);
        janela.pack();
        janela.setVisible(true);
    }
    
    private void mudaTela(JPanel novoPainel){
        painelDeConteudo.removeAll();
        painelDeConteudo.add(novoPainel);
        janela.pack(); 
    }
    
    private void constroiBarraDeMenu(){
        JMenuBar barraMenu = new JMenuBar();
        janela.setJMenuBar(barraMenu);
        
        JMenu menuServico = new JMenu("Serviços");
        barraMenu.add(menuServico);
        JMenuItem menuItemServicoGerais = new JMenuItem("Serviços gerais");
        menuServico.add(menuItemServicoGerais);
        
        JMenuItem menuItemTiposServicos = new JMenuItem("Tipos de serviços");
        menuServico.add(menuItemTiposServicos);
        menuItemTiposServicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mudaTela(new PainelDeServico());
            }
        });
        
        JMenuItem menuItemTiposRoupa = new JMenuItem("Tipos de Roupa");
        menuServico.add(menuItemTiposRoupa);
        menuItemTiposRoupa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p = new JPanel();
                JButton btn = new JButton("LALALA");
                p.add(btn);
                mudaTela(p);
            }
        });
        
        JMenuItem menuItemCores = new JMenuItem("Cores");
        menuServico.add(menuItemCores);
        menuItemCores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p = new JPanel();
                JButton btn = new JButton("HEHEHE");
                p.add(btn);
                mudaTela(p);
            }
        });
        
        JMenu menuRelatorios = new JMenu("Relatórios");
        barraMenu.add(menuRelatorios);
        JMenuItem menuItemRelRecebidosPeriodo = new JMenuItem("Pedidos recebidos por periodo");
        menuRelatorios.add(menuItemRelRecebidosPeriodo);
        JMenuItem menuItemRelProntosPeriodo = new JMenuItem("Pedidos prontos por periodo");
        menuRelatorios.add(menuItemRelProntosPeriodo);
        JMenuItem menuItemRelrEntreguesPeriodo = new JMenuItem("Pedidos entregues por periodo");
        menuRelatorios.add(menuItemRelrEntreguesPeriodo);
        JMenuItem menuItemRelPedidos = new JMenuItem("Relacao completa de pedidos");
        menuRelatorios.add(menuItemRelPedidos);          
        
        JMenu menuRoupas = new JMenu("Roupas");
        barraMenu.add(menuRoupas);  
        JMenu menuClientes = new JMenu("Clientes");
        barraMenu.add(menuClientes);
    }
   
}
