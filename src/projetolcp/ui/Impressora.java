package ui;

/**
 * Emula impressora de balcao, exibindo saida visualmente diferenciada.
 * Eh possivel ajustar parametros de impressao editando constantes privativas
 * no codigo fonte.
 * 
 * @author orlando de andrade figueiredo
 */
public class Impressora {

    private static final int LARGURA_BOBINA = 51;
    private static final int MARGEM_ESQ = 21;
    private static final int MARGEM_DIR = 8;
    
    private void margemEsq(){
        for (int i = 0; i < MARGEM_ESQ - 1; i++) {
            System.out.print(">");
        }
        System.out.print(" ");
    }
    
    private void margemDir(){
        System.out.print(" ");
        for (int i = 0; i < MARGEM_DIR - 1; i++) {
            System.out.print("<");
        }
        System.out.println("");
    }
    
    private void preenche(String padrao){
        int padraoL = padrao.length();
        int i = 0;
        while(true) {
            if (padraoL >= LARGURA_BOBINA - i) {
                System.out.print(padrao.substring(0, LARGURA_BOBINA - i));
                break;
            } else {
                System.out.print(padrao);
                i += padraoL;
            }
        }
    }
    
    /**
     * Mostra uma linha picotada.
     */
    public void picote(){
        margemEsq();
        preenche("- ");
        margemDir();
    }

    /**
     * Avanca uma linha.
     */
    public void avanco(){
        margemEsq();
        preenche(" ");
        margemDir();
    }
    
    /**
     * Imprime uma string.
     * Se a string exceder a largura da bobina da impressora, ela eh quebrada
     * em varias linhas.
     * 
     * @param texto 
     */
    public void imprime(String texto){
        margemEsq();
        if (texto.length() <= LARGURA_BOBINA) {
            System.out.print(texto);
            for (int i = 0; i < LARGURA_BOBINA - texto.length(); i++) {
                System.out.print(" ");
            }
            margemDir();
        } else {
            System.out.print(texto.substring(0, LARGURA_BOBINA - 1) + " ");
            margemDir();
            imprime("     " + texto.substring(LARGURA_BOBINA - 1));
        }
    }
}
