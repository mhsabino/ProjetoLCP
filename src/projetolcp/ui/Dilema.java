package ui;

/**
 * Menu especializado em escolhas sim ou nao.
 * 
 * @author orlando de andrade figueiredo
 */
class Dilema extends Menu {
    
    /**
     * Construtor completo.
     * 
     * @param enunciado
     * @param inicio 
     */
    public Dilema(String enunciado, int inicio) {
        super(null, enunciado, inicio);
        super.inclui("sim", "Sim");
        super.inclui("nao", "Nao");
    }
    
    /**
     * Construtor padrao.
     * Opcoes iniciam em 1.
     * 
     * @param enunciado 
     */
    public Dilema(String enunciado) {
        this(enunciado, 1);
    }
    
    /**
     * Obtem a resposta do usuario.
     * 
     * @return verdade caso o usuario tenha escolhido "sim"
     */
    public boolean decida() {
        return super.opcao().equals("sim");
    }
}
