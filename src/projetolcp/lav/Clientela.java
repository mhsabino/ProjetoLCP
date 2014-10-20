
package lav;

/**
 * Coletivo de clientes.
 * 
 * @author orlando de andrade figueiredo
 */
public class Clientela implements java.io.Serializable {

    private java.util.List<Cliente> clientes;
    private int codigoNovoCliente;

    /**
     * Construtor sem parametros.
     * Requerido pelo sistema de serializacao em arquivos do Java.
     */
    public Clientela() {
        
    }
    
    /**
     * No caso de perda dos dados, inicia o sistema e o povoa com alguns clientes.
     */
    public void inicia() {
        clientes = new java.util.ArrayList<>();
        codigoNovoCliente = 31;
        adicionaCliente(new Cliente("Venceslau", "Abade", "R. Vinte, n.123"));
        adicionaCliente(new Cliente("Vilobaldo", "Antunes", "Av. Doze, n.2000"));
    }
    
    /**
     * Insere um novo cliente na base e lhe confere um codigo.
     * 
     * @param cliente o cliente
     */
    public void adicionaCliente(Cliente cliente) {
        cliente.setCodigo(codigoNovoCliente++);
        clientes.add(cliente);
    }
    
    /**
     * Remove cliente mediante o codigo.
     * Se nao encontrar o codigo, nao informa.
     * 
     * @param codigo o codigo do cliente
     */
    public void removeCliente(int codigo) {
        for (Cliente c : clientes) {
            if (c.getCodigo() == codigo) {
                clientes.remove(c);
            }
        }
    }
    
    /**
     * Obtem uma colecao de todos os clientes.
     * 
     * @return os clientes como uma colecao
     */
    public Cliente[] getTodosClientes() {
        return clientes.toArray(new Cliente[0]);
    }
    
    /**
     * Obtem dados do cliente mediante seu codigo.
     * 
     * @param cod o codigo do cliente
     * @return dados estruturados representando o cliente
     */
    public Cliente getCliente(int cod){
        for(Cliente p : clientes) {
            if (p.getCodigo() == cod) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Obtem todos os clientes que possuem texto no nome completo.
     * Diferencia maiusculas de minusculas.
     * 
     * @param pedaco pedaco de nome
     * @return clientes, como colecao, que possuem pedaco em seu nome completo
     */
    public Cliente[] buscarPorNome(String pedaco) {
        java.util.List<Cliente> result = new java.util.ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getNomeTodo().contains(pedaco)) {
                result.add(c);
            }
        }
        return result.toArray(new Cliente[0]);
    }

    /**
     * Obtem todos os clientes que possuem texto no endereco.
     * Diferencia maiusculas de minusculas.
     * 
     * @param pedaco pedaco do endereco
     * @return clientes, como colecao, que possuem pedaco em seu endereco
     */
    public Cliente[] buscarPorEndereco(String pedaco) {
        java.util.List<Cliente> result = new java.util.ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getEndereco().contains(pedaco)) {
                result.add(c);
            }
        }
        return result.toArray(new Cliente[0]);
    }

}
