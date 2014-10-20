package lav;

/**
 * Peca de roupa de um pedido.
 * Eh descrita atraves do tipo de roupa, da presenca de defeitos,
 * das cores presentes (com modificadores) e estilo de combinacao das cores,
 * tipo do servico contratado e preco no momento da contratacao.
 * Além disso, ha um codigo registrado na etiqueta pregada na peca.
 * 
 * @author orlando de andrade figueiredo
 */
public class Roupa implements java.io.Serializable {
    protected int codigo;
    protected boolean defeituoso;
    private String tipoDeRoupa;
    private String cor1;
    private String mod1;
    private String cor2;
    private String mod2;
    private String estilo;
    private String tipoDeServico;
    private double preco;
    
    /**
     * Nova representacao de peca de roupa de pedido.
     * 
     * @param tr tipo de roupa (camisa, vestido, etc.)
     * @param c1 cor basica
     * @param m1 modificador da cor basica (opcional)
     * @param c2 segunda cor (opcional)
     * @param m2 modificador da segunda cor (opcional)
     * @param e estilo de combinacao das cores (listras, xadrez, etc.) (opcional)
     * @param ts tipo do servico (lavar, secar, passar, etc.)
     * @param p preco
     * @param d verdade se tem defeito
     */
    public Roupa(String tr, String c1, String m1, String c2, String m2,
            String e, String ts, double p, boolean d)
    {
        tipoDeRoupa = tr;
        cor1 = c1;
        mod1 = m1;
        cor2 = c2;
        mod2 = m2;
        estilo = e;
        tipoDeServico = ts;
        defeituoso = d;
        preco = p;
    }
    
    /**
     * Configura o codigo da peca.
     * 
     * @param cod o codigo como inteiro simples
     */
    public void setCodigo(int cod) {
        codigo = cod;
    }

    /**
     * Obtem o codigo da peca de roupa.
     * 
     * @return o codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtem o tipo de roupa.
     * Camisa, vestido, etc.
     * 
     * @return o tipo de roupa
     */
    public String getTipoDeRoupa() {
        return tipoDeRoupa;
    }
    
    /**
     * Obtem o tipo de servico contratado para a peca.
     * Lavar, secar, passar, completo, etc.
     * 
     * @return o tipo de servico
     */
    public String getTipoDeServico() {
        return tipoDeServico;
    }

    /**
     * Obtem a cor da peca como uma combinacao das cores.
     * Exemplo: bicolor azul escuro branco
     * 
     * @return combinacao de estilo, cores e modificadores
     */
    public String getCor() {
        return ( (estilo == "liso") ? "" : (estilo + " ") ) +
                cor1 + ((mod1 != null) ? (" " + mod1) : "") + 
               ( (cor2 != null) ? " " + cor2 + ((mod2 != null) ? (" " + mod2) : "" ) : "");
    }
    
    /**
     * Obtem o preco do servico contratado para a peca.
     * 
     * @return preco
     */
    public double getPreco() {
        return preco;
    }
    
    /**
     * Gera representacao textual da peca.
     * Que contempla o tipo, a combinacao de cores, o estado defeituoso ou nao
     * e o preco contratado do servico
     * 
     * @return representacao textual
     */
    @Override
    public String toString() {
        String defeito = " s/ defeito";
        if (defeituoso) 
            defeito = " c/ defeito";
        return tipoDeRoupa + " " + getCor() + defeito + 
                " a " + java.text.NumberFormat.getCurrencyInstance().format(preco);
    }
}
