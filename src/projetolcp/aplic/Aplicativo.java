package aplic;

import java.io.*;
import projetolcp.gui.TelaInicial;

/**
 * Executa sistema de lavanderia.
 * Digite <code>$ java aplic.Aplicativo</code> para executar o sistema.
 * Carrega base de dados, inicia grandes objetos coletivos e ativa a interface
 * com usuário.
 * Nao trata excecoes de E/S.
 * 
 * @author orlando de andrade figueiredo
 */
public class Aplicativo {
    
    /** 
     * Metodo executavel padrao Java.
     * Acessa a base de dados, inicia os objetos coletivos e a interface com
     * usuario. No caso de nao conseguir encontrar a base, chama os iniciadores
     * originais e o povoamento minimo de dados. Na saida, salva os valores
     * inseridos na base.
     * 
     * @param args nao sao usados
     * @throws Exception nao trata excecoes relativas ao armazenamento em arquivos
     */
    public static void main(String[] args) throws Exception {
        
        lav.Lavanderia l;
        lav.Clientela c;
        lav.RolDeServicos s;
        
        if ((new File("./base.dat")).exists()) {
            ObjectInputStream i = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    "./base.dat")));
            l = (lav.Lavanderia) i.readObject();
            c = (lav.Clientela) i.readObject();
            s = (lav.RolDeServicos) i.readObject();
        } else {
            l = new lav.Lavanderia();
            l.inicia();
            c = new lav.Clientela();
            c.inicia();
            s = new lav.RolDeServicos();
            s.inicia();
        }
        
        new TelaInicial();
        //(new ui.LinhaDeComando(l,c,s)).executa();
        
        try (ObjectOutputStream o = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("./base.dat")))) {
            o.writeObject(l);
            o.writeObject(c);
            o.writeObject(s);
            o.flush();
        }
    }
}