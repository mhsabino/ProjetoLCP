package lav;

/**
 * Representa um pedido, com varias pecas de roupa.
 * Mantem referencia do cliente. Possui codigo proprio. 
 * Manifesta estados (constantes publicas). Comeca como SUJA, passa a LIMPA
 * e termina como ENTREGUE. As datas de mudanca de estados sao registradas.
 *
 * @author orlando de andrade figueiredo
 */
public class Cesta implements java.io.Serializable {
    private Cliente cliente;
    private java.util.Date entrada;
    private java.util.Date termino;
    private java.util.Date entrega;
    private java.util.List<Roupa> pecas;
    private double total;
    private int codigo;
    
    private int estado;
    /**
     * Estados de uma cesta:.
     * Os estados devem seguir um padrao binario de valores: 1, 2, 4, 8, 16, 32.
     * Isso permite fazer consultas simultaneas, do tipo "roupas limpas e entregues"
     * atraves de mascaras binarias.
     */
    public static final int SUJA = 1;
    public static final int LIMPA = 2;
    public static final int ENTREGUE = 4;
    
    /**
     * Nova cesta.
     * Registra o horario de entrada do pedido, e o coloca no estado inicial.
     * 
     * @param cliente 
     */
    public Cesta(Cliente cliente) {
        this.cliente = cliente;
        entrada = (new java.util.GregorianCalendar()).getTime();
        termino = entrega = null;
        pecas = new java.util.ArrayList<>();
        total = 0.0;
        estado = SUJA;
    }

    /**
     * Configura codigo.
     * O codigo pode ser um numero inteiro.
     * 
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtem o codigo.
     * O codigo eh um numero inteiro.
     * 
     * @return o codigo como numero inteiro
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtem o estado atual do pedido como numero convencionado.
     * Ver o comentario das constantes publicas.
     * 
     * @return estado como constante inteira
     */
    public int getEstado() {
        return estado;
    }
    
    /**
     * Obtem texto que descreve o estado atual do pedido.
     * 
     * @return texto descrevendo o estado
     */
    public String estado(){
        switch(estado) {
            case SUJA:
                return "Em lavagem";
            case LIMPA:
                return "Pronta";
            case ENTREGUE:
                return "Entregue";
        };
        return null;
    }
    
    /**
     * Muda o estado do pedido e registra o horario.
     */
    public void setPronta() {
        estado = LIMPA;
        termino = (new java.util.GregorianCalendar()).getTime();
    }
    
    /** 
     * Inquire se pedido esta pronto e ainda nao foi entregue.
     * 
     * @return valor verdade se pedido ja foi lavado e ainda nao devolvido
     */
    public boolean pronta() {
        return estado == LIMPA;
    }
    
    /**
     * Muda estado do pedido e registra horario.
     */
    public void setEntregue() {
        estado = ENTREGUE;
        entrega = (new java.util.GregorianCalendar()).getTime();
    }

     /** 
     * Inquire se pedido foi entregue.
     * 
     * @return valor verdade se pedido ja foi devolvido
     */
    public boolean entregue() {
        return estado == ENTREGUE;
    }
    
    /** 
     * Inquire se pedido ainda nao foi lavado.
     * 
     * @return valor verdade se pedido ainda nao foi lavado
     */

    public boolean lavagem() {
        return estado == SUJA;
    }
    
    /**
     * Obtem valor do servico total referente ao pedido.
     * 
     * @return valor cobrado pelo servico
     */
    public double total() {
        return total;
    }

    /**
     * Acrescenta uma peca de roupa ao pedido.
     * 
     * @param r peca de roupa
     */
    public void adicionaPeca(Roupa r) {
        pecas.add(r);
        total += r.getPreco();
    }

    /**
     * Obtem referencia para descritor do cliente.
     * 
     * @return o cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    
    /**
     * Obtem o momento de recebimento do pedido.
     * 
     * @return data e horario do recebimento
     */
    public java.util.Date getDataDeEntrada() {
        return entrada;
    }
    
    /**
     * Obtem o momento de registro que a lavagem foi realizada.
     * Se o pedido ainda nao foi lavado, retorna null.
     * 
     * @return data e horario do registro da lavagem, ou null
     */
    public java.util.Date getDataPronta() {
        return termino;
    }
    
    /**
     * Obtem o momento da devolucao do pedido.
     * Se o pedido ainda nao foi entregue, retorna null.
     * 
     * @return data e horario da devolucao do pedido, ou null
     */
    public java.util.Date getDataDeEntrega() {
        return entrega;
    }

    /**
     * Representacao imprimivel do pedido.
     * O pedido eh descrito por seu codigo, data de entrada, custo total e estado.
     * 
     * @return texto descritivo
     */
    @Override
    public String toString() {
        return "" + getCodigo() + " " +  
                java.text.DateFormat.getDateInstance().format(entrada) + " " +
                java.text.NumberFormat.getCurrencyInstance().format(total) + 
                " " + estado();
    }
    
    /**
     * Obtem pecas de roupa do pedido.
     * 
     * @return colecao de pecas de roupa
     */
    public Roupa[] getRoupas() {
        return pecas.toArray(new Roupa[0]);
    }
    
    /**
     * Usa uma impressora para gerar uma versao fisica do recibo.
     * Essa versao eh conveniente para o momento em que o codigo ainda nao
     * foi configurado.
     * 
     * @param codigo codigo que ainda nao foi configurado no pedido
     */
    public void recibo(int codigo) {
        ui.Impressora imp = new ui.Impressora();
        imp.picote();
        imp.avanco();
        imp.imprime("==================================================");
        imp.imprime("| | | >>>  --      Lav Fio Limpo      -- <<< | | |");
        imp.imprime("==================================================");
        imp.avanco();
        imp.imprime("Cliente:");
        imp.imprime(cliente.getNomeTodo());
        imp.imprime(cliente.getEndereco());
        imp.avanco();
        imp.imprime("Pedido No. " + codigo);
        imp.imprime("Data: " + java.text.DateFormat.getDateTimeInstance().format(entrada));
        imp.avanco();
        imp.imprime("Pecas:");
        for(Roupa p : pecas) {
            imp.imprime("--------------------------------------------------");
            imp.imprime(p.toString());
        }
        imp.imprime("--------------------------------------------------");
        imp.avanco();
        imp.imprime("Total: " + java.text.NumberFormat.getCurrencyInstance().format(total));
        imp.avanco();
        imp.picote();
    }
    
    /**
     * Usa uma impressora para gerar etiquetas a serem presas nas pecas de roupa.
     * Essa versao eh conveniente para o momento em que o codigo ainda nao
     * foi configurado.
     * 
     * @param codigo codigo que ainda nao foi configurado no pedido
     */
    public void etiquetas(int codigo) {
        ui.Impressora imp = new ui.Impressora();
        for(Roupa p : pecas) {
            imp.picote();
            imp.imprime("Cl. " + cliente.getCodigo() +  " " + 
                    java.text.DateFormat.getDateTimeInstance().format(entrada));
            imp.imprime("Pedido No. " + codigo + "     Cod. " + p.getCodigo());
        }
        imp.picote();
    }
}
