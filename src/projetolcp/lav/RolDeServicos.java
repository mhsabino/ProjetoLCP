package lav;

/**
 * Coletivo de todos os servicos oferecidos pela lavanderia.
 * 
 * @author orlando de andrade figueiredo
 */
public class RolDeServicos implements java.io.Serializable {
    private java.util.List<Servico> servicos;
    
    private java.util.List<String> roupas;
    private java.util.List<String> estilos;
    private java.util.List<String> cores;
    private java.util.List<String> modificadores;
    private java.util.List<String> tipos;
    
    /**
     * Construtor sem parametros.
     * Requerido pelo sistema de serializacao do Java.
     */
    public RolDeServicos() {
        
    }
    
    /**
     * No caso de perda dos dados, povoa o sistema e o povoa com alguns servicos.
     */
    public void inicia() {
        roupas = new java.util.ArrayList<>();
        estilos = new java.util.ArrayList<>();
        cores = new java.util.ArrayList<>();
        modificadores = new java.util.ArrayList<>();
        tipos = new java.util.ArrayList<>();

        servicos = new java.util.ArrayList<>();

        roupas.add("camisa");
        roupas.add("camisa polo");
        roupas.add("camiseta");
        roupas.add("regata");
        roupas.add("vestido");
        roupas.add("blaser");
        roupas.add("casaco");
        roupas.add("calca");
        roupas.add("saia");
        roupas.add("maio");
        roupas.add("biquini");
        roupas.add("sunga");
        roupas.add("terno");
        roupas.add("bermuda");
        roupas.add("calcinha");
        roupas.add("sutian");
        roupas.add("cueca");
        roupas.add("meia");
        roupas.add("bone");
        roupas.add("lencol");
        roupas.add("colcha");
        roupas.add("toalha");
        roupas.add("conjunto");
        
        estilos.add("liso");
        estilos.add("listrado");
        estilos.add("xadrez");
        estilos.add("bicolor");
        estilos.add("estampado");
        
        cores.add("branco");
        cores.add("preto");
        cores.add("colorido");
        cores.add("amarelo");
        cores.add("vermelho");
        cores.add("verde");
        cores.add("azul");
        cores.add("laranja");
        cores.add("roxo");
        cores.add("violeta");
        cores.add("vinho");
        cores.add("bege");
        cores.add("marrom");
        
        modificadores.add("claro");
        modificadores.add("escuro");
        
        tipos.add("completo");
        tipos.add("soh passar");
        tipos.add("soh secar e passar");

        servicos.add(new Servico("camisa", "preto", 4.0));
        servicos.add(new Servico("camisa", "branco", 5.0));
        servicos.add(new Servico("camisa", "colorido", 4.5));
        servicos.add(new Servico("camisa polo", "branco", 5.0));
        servicos.add(new Servico("camisa polo", "preto", 6.0));
        servicos.add(new Servico("camisa polo", "colorido" , 10.0));
        servicos.add(new Servico("camiseta", 3.5));
        servicos.add(new Servico("regata", 3.0));
        servicos.add(new Servico("vestido", "preto", 7.0));
        servicos.add(new Servico("vestido", "branco", 8.0));
        servicos.add(new Servico("vestido", "colorido" , 7.5));
        servicos.add(new Servico("blaser", 20.0));
        servicos.add(new Servico("casaco", 14.0));
        servicos.add(new Servico("saia", 6.0));
        servicos.add(new Servico("maio", 9.0));
        servicos.add(new Servico("biquini", 8.0));
        servicos.add(new Servico("sunga", 8.0));
        servicos.add(new Servico("terno", 30.0));
        servicos.add(new Servico("bermuda", 5.0));
        servicos.add(new Servico("calcinha", 2.5));
        servicos.add(new Servico("sutien", 2.5));
        servicos.add(new Servico("cueca", 2.5));
        servicos.add(new Servico("meia", 2.0));
        servicos.add(new Servico("bone", 11.0));
        servicos.add(new Servico("colcha", 20.0));
        servicos.add(new Servico("toalha", 6.5));
        servicos.add(new Servico("conjunto", 10.0));
    }
    
    /**
     * Adiciona um novo tipo de roupa.
     * Camisa, calca, vestido, bermuda, saia, etc.
     * Todas as letras minúsculas, inclusive a inicial.
     * 
     * @param r tipo de roupa como texto
     */
    public void adicionaRoupa(String r) {
        roupas.add(r);
    }
    
    /**
     * Remove um tipo de roupa.
     * Camisa, calca, vestido, bermuda, saia, etc.
     * 
     * @param r tipo de roupa como texto
     */
    public void removeRoupa(String r) {
        if (roupas.contains(r))
            roupas.remove(r);
    }

    /**
     * Obtem uma colecao com todos os tipos de roupas cadastradas.
     * 
     * @return todos os tipos de roupas como uma colecao de textos
     */
    public String[] todasAsRoupas() {
        return roupas.toArray(new String[0]);
    }
    
    /**
     * Adiciona um novo estilo de combinacao de cores.
     * Liso, listrado, xadrez, estampado, bicolor, etc.
     * Todas as letras minusculas, inclusive a inicial.
     * Palavra na forma masculina, quando nao for comum de dois generos.
     * 
     * @param s estilo
     */
    public void adicionaEstilo(String s) {
        estilos.add(s);
    }
    
    /**
     * Remove um estilo de combinacao de cores.
     * Liso, listrado, xadrez, estampado, bicolor, etc.
     * 
     * @param s estilo
     */
    public void removeEstilo(String s) {
        if (estilos.contains(s))
            estilos.remove(s);
    }

    /**
     * Obtem uma colecao com todos os estilos de combinacao de cores.
     * 
     * @return todos os estilos de combinacao de cores como um colecao de textos
     */
    public String[] todosOsEstilos() {
        return estilos.toArray(new String[0]);
    }
    
    /**
     * Adiciona uma nova cor.
     * Todas as letras minusculas, inclusive a inicial.
     * Palavra na forma masculina, quando nao for comum de dois generos.
     * 
     * @param c cor
     */
    public void adicionaCor(String c) {
        cores.add(c);
    }
    
    /**
     * Remove uma cor.
     * 
     * @param c cor
     */
    public void removeCor(String c) {
        if (cores.contains(c))
            cores.remove(c);
    }

    /**
     * Obtem uma colecao com todas as cores.
     * 
     * @return todas as cores, como uma colecao de textos
     */
    public String[] todasAsCores() {
        return cores.toArray(new String[0]);
    }

    /**
     * Adiciona um modificador de cor.
     * Claro, escuro, musgo, pastel, etc.
     * Todas as letras minusculas, inclusive a inicial.
     * A palavra deve estar na forma masculina, quando nao for comum de dois generos.
     * 
     * @param m modificador de cor
     */
    public void adicionaModificador(String m) {
        modificadores.add(m);
    }
    
    /**
     * Remove um modificador de cor.
     * 
     * @param m modificador de cor
     */
    public void removeModificador(String m) {
        if (modificadores.contains(m))
            modificadores.remove(m);
    }
    
    /**
     * Obtem uma colecao com todos os modificadores
     * 
     * @return todos os modificadores, como uma colecao de textos
     */
    public String[] todosOsModificadores() {
        return modificadores.toArray(new String[0]);
    }

    /**
     * Adiciona um tipo de servico.
     * Completo, secar e passar, soh passar, etc.
     * Todas as letras minúsculas, inclusive a inicial.
     * 
     * @param m tipo de servico
     */
    public void adicionaTipo(String m) {
        tipos.add(m);
    }
    
    /**
     * Remove um tipo de servico.
     * 
     * @param m tipo de servico
     */
    public void removeTipo(String m) {
        if (tipos.contains(m))
            tipos.remove(m);
    }
    
    /**
     * Obtem colecao com todos os tipos de servico.
     * 
     * @return todos os tipos de servico, como uma colecao de textos
     */
    public String[] todosOsTipos() {
        return tipos.toArray(new String[0]);
    }
    
    /**
     * Adiciona um servico.
     * 
     * @param v repsesentacao estruturada com dados do servico
     */
    public void adicionaServico(Servico v) {
        servicos.add(v);
    }
    
    /**
     * Remove um servico.
     * Nao indica caso nao encontre o servico.
     * Usa mecanismo de igualdade de servicos. (Servico.equals)
     * 
     * @param v representacao estruturada com das
     */
    public void removeServico(Servico v) {
        if (servicos.contains(v))
            servicos.remove(v);
    }
    
    /**
     * Obtem colecao com todos os servicos oferecidos.
     * 
     * @return todos os servicos
     */
    public Servico[] todosOsServicos() {
        return servicos.toArray(new Servico[0]);
    }
    
    /**
     * Atualiza preco de servico.
     * 
     * @param s servico
     * @param preco novo preco
     */
    public void atualizaPreco(Servico s, double preco) {
        for(Servico v : servicos) {
            if (v.equals(s)) {
                v.setPreco(preco);
                return;
            }
        }
    }
    
    /**
     * Obtem preco atual de um servico.
     * 
     * @param s servico
     * @return preco atual do servico
     */
    public double consultaPreco(Servico s) {
        for(Servico v : servicos) {
            if (v.equals(s)) {
                return v.getPreco();
            }
        }
        return -1.0;
    }

}
