package lav;

/**
 * Simples registro de dados de um cliente.
 * Inclui nome, sobrenome e endereco, e codigo.
 * 
 * @author orlando de andrade figueiredo
 */
public class Cliente implements java.io.Serializable {
    private int codigo;
    private String nome;
    private String sobrenome;
    private String endereco;
    
    /**
     * Novo cliente.
     * Eh preciso dispor de todos os dados do cliente para iniciar uma nova
     * representacao: nome, sobrenome e endereco.
     * 
     * @param nome uma unica palavra
     * @param sobrenome uma unica palavra
     * @param endereco varias palavras
     */
    public Cliente(String nome, String sobrenome, String endereco){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
    }
    
    /**
     * Configura o codigo do cliente.
     * O codigo deve ser gerado por uma classe que agrega diversos clientes,
     * e seu valor deve ser configurado atraves deste metodo.
     * 
     * @param cod o codigo, um numero inteiro simples
     */
    public void setCodigo(int cod) {
        codigo = cod;
    }
    
    /**
     * Obtem o codigo.
     * 
     * @return o codigo, um numero inteiro simples
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Obtem o nome como uma combinacao de nome e sobrenome.
     * 
     * @return texto com nome e sobrenome
     */
    public String getNomeTodo() {
            return nome + " " + sobrenome;
    }
    
    /**
     * Obtem o endereco.
     * 
     * @return texto com endereco
     */
    public String getEndereco() {
            return endereco;
    }
    
    /**
     * Representa o cliente como texto.
     * Usa codigo e nome completo.
     * 
     * @return o cliente como texto, isto eh, codigo e nome completo
     */
    @Override
    public String toString() {
        return "" + getCodigo() + " " + getNomeTodo();
    }
}
