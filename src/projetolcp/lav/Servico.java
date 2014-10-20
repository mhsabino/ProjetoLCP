package lav;

/**
 * Descritor de um servico oferecido pela lavanderia.
 * Eh descrito a partir do tipo de peca de roupa (camisa, vestido, etc.), 
 * cor basica (se for o caso), tipo de servico (lavar, secar, passar, etc.) 
 * e preco.
 * Nem todos os servicos diferenciam precos pela cor. Nesse caso, a cor deve
 * ser preenchida pela string "qualquer". Ha um construtor que omite cor e
 * atua conforme essa diretiva.
 * O tipo de servico default eh o "completo". Construtores que omitem tipo de
 * servico usam essa opcao.
 * 
 * A cor "colorido" eh diferenciada de "preto" e "branco" nesta classe.
 * 
 * @author orlando de andrade figueiredo
 */
public class Servico implements java.io.Serializable {
    private String roupa;
    private String cor;
    private String tipo;
    private double preco;

    /**
     * Construtor completo.
     * 
     * @param r tipo de roupa (camisa, vestido, etc.)
     * @param c cor basica dominante, sem modificador
     * @param t tipo de servico (completo, soh passar, etc.)
     * @param p preco atual do servico
     */
    public Servico(String r, String c, String t, double p) 
    {
        roupa = r;
        cor = c;
        tipo = t;
        preco = p;
    }
        
    /**
     * Construtor que omite o tipo de servico.
     * O valor default usado para o tipo de servico eh "completo".
     * 
     * @param r tipo de roupa (camisa, vestido, etc.)
     * @param c (cor basica dominante, sem modificador
     * @param p preco atual do servico
     */
    public Servico(String r, String c, double p)
    {
        this(r, c, "completo", p);
    }
    
    /**
     * Construtor que omite cor e tipo de servico.
     * O valor default para o tipo de servico eh "completo".
     * A cor eh "qualquer".
     * 
     * @param r tipo de roupa (camisa, vestido, etc.)
     * @param p preco
     */
    public Servico(String r, double p) {
        this(r, "qualquer", "completo", p);
    }

    /**
     * Obtem o tipo de roupa do servico.
     * 
     * @return tipo de roupa
     */
    public String getRoupa() {
        return roupa;
    }

    /**
     * Obtem a cor basica do pedido.
     * 
     * @return cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * Obtem o tipo de servico.
     * Completo, secar e passar, soh passar, etc.
     * 
     * @return o tipo do servico
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Configura o tipo do servico.
     * Completo, secar e passar, soh passar etc.
     * Eh um texto.
     * 
     * @param tipo tipo do servico como texto
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtem o valor atual do servico.
     * 
     * @return preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Configura o valor atual do servico.
     * 
     * @param preco novo preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    /**
     * Representacao textual do servico.
     * Contem tipo do servico, tipo de roupa, cor (caso seja relevante) e preco
     * 
     * @return representacao textual
     */
    @Override
    public String toString() {
        String txtCor = "";
        if (cor != "qualquer"){
            txtCor = cor + " ";
        }
        return tipo + " " + roupa + " " + txtCor + preco;
    }
    
    /**
     * Compara dois servicos.
     * Eh aqui que a cor "qualquer" aceita outras cores e a cor "colorido" eh
     * igualada a tudo que nao seja "preto" ou "branco".
     * @param o servico sendo comparado
     * @return verdade se os servico se equiparam segundo os criterios
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Servico) {
            Servico s = (Servico) o;
            if ( (this.cor == "qualquer" || s.cor == "qualquer") ||
                 ( this.cor != "preto" && this.cor != "branco" && s.cor == "colorido" ) ||
                 ( s.cor != "preto" && s.cor != "branco" && this.cor == "colorido") ||
                 ( this.cor.equals(s.cor)) ) // e se houvesse a cor cinza? desenho ruim

                return (this.roupa.equals(s.roupa) && 
                        this.tipo.equals(s.tipo));
            else
                return false;
                
        } else 
            return false;
    }

}
