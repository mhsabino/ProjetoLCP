package lav;

/**
 * Coletivo de todos os pedidos de uma lavanderia.
 * @author orlando
 */
public class Lavanderia implements java.io.Serializable {
    private java.util.List<Cesta> cestas;
    public int codigoNovaCesta;
    public int codigoNovaPeca;
 
    /**
     * Construtor sem parametros.
     * Requerido pelo sistema de serializacao em arquivos do Java.
     */

    public Lavanderia() {
        
    }

    /**
     * No caso de perda dos dados, inicia o sistema.
     */

    public void inicia() {
        cestas = new java.util.ArrayList<>();
        codigoNovaCesta = 401;
        codigoNovaPeca = 5001;
    }

    /**
     * Obtem novo valor do gerador de numeros de pecas de roupa.
     * 
     * @return o codigo, como numero inteiro simples
     */
    public int getCodigoPeca() {
        return codigoNovaPeca++;
    }
 
    /**
     * Insere um novo pedido no sistema e gera seu codigo.
     * 
     * @param c um pedido
     */
    public void adicionaCesta(Cesta c) {
        c.setCodigo(codigoNovaCesta++);
        cestas.add(c);
    }

    /**
     * Obtem todas os pedidos com estado entre o conjunto de estados especificados 
     * e dentro do periodo especificado.
     * O periodo eh dado em dias a partir de hoje. 0 signfica hoje a partir da
     * meia-noite.
     * Se o estado eh de lavagem (Cesta.SUJA), retorna os pedidos que ficaram
     * prontos no periodo.
     * Se o estado eh de lavado (Cesta.LIMPA), retorna os pedidos que foram
     * limpos no periodo.
     * Se o estado eh de entregue (Cesta.ENTREGUE), retorna os pedidos que
     * foram entregues dentro do periodo especificado.
     * 
     * @param estados combinacao binaria de estados (v. Cesta)
     * @param periodoEmDias 0 = hoje, 1 = ontem e hoje, 2 = anteontem, ontem e hoje, etc.
     * @return colecao de pedidos que atendem os criterios
     */
    public Cesta[] getCestas(int estados, int periodoEmDias) {

        if (periodoEmDias < 0)
            periodoEmDias = 0;
        java.util.Calendar preparaPrazo = new java.util.GregorianCalendar();
        preparaPrazo.setTime(new java.util.Date());
        // Recua para meia-noite
        preparaPrazo.set(java.util.Calendar.HOUR_OF_DAY, 0);
        preparaPrazo.set(java.util.Calendar.MINUTE, 0);
        preparaPrazo.set(java.util.Calendar.SECOND, 0);
        preparaPrazo.set(java.util.Calendar.MILLISECOND, 0);
        // Recua n dias
        preparaPrazo.add(java.util.Calendar.DAY_OF_YEAR, -1 * periodoEmDias);
        java.util.Date prazo  = preparaPrazo.getTime();
        
        java.util.List<Cesta> result = new java.util.ArrayList<>();
        for(Cesta c : cestas) {
            if ( ( (estados & Cesta.SUJA) != 0 && c.lavagem() && c.getDataDeEntrada().after(prazo) ) ||
                 ( (estados & Cesta.LIMPA) != 0 && c.pronta() && c.getDataPronta().after(prazo) ) ||
                 ( (estados & Cesta.ENTREGUE) != 0 && c.entregue() && c.getDataDeEntrega().after(prazo) ) )
                        result.add(c);
        }
        return result.toArray(new Cesta[0]);
    }

    /**
     * Registra pedido como lavado mediante codigo.
     * 
     * @param codigo
     * @return verdade se encontrou o pedido
     */
    public boolean disponibiliza(int codigo) {
        for(Cesta c : cestas) {
            if (c.getCodigo() == codigo) {
                c.setPronta();
                return true;
            }
        }
        return false;
    }

    /**
     * Registra pedido como entregue mediante codigo.
     * 
     * @param codigo
     * @return verdade se encontrou o pedido
     */
    public boolean devolve(int codigo) {
        for(Cesta c : cestas) {
            if (c.getCodigo() == codigo) {
                c.setEntregue();
                return true;
            }
        }
        return false;
    }
}
