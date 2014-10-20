package ui;

/**
 * Representacao interna de uma opcao de menu.
 * 
 * @author orlando de andrade figueiredo
 */
class Opcao {
    
    private String resumo;
    private String descricao;
    
    public Opcao(String curta, String longa) {
        resumo = curta;
        descricao = longa;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
    
    public String opcao() {
        return resumo;
    }
}
