package ui;

/**
 * Menu para interface baseada em texto.
 * Pode ter ou nao enunciado (confira construtores).
 * Opcoes sao inseridas com texto resumo e texto descritivo.
 * Apresenta para o usuario os textos descritivos acompanhados de numeros
 * de escolha gerados. Ao digitar o numero, reconhece a opcao e retorna texto resumo.
 * 
 * 
 * @author orlando de andrade figueiredo
 */
class Menu {
    protected String titulo;
    protected String enunciado;
    protected java.util.Map<Integer, Opcao> opcoes;
    protected final int inicio;
    protected int contador;

    /**
     * Construtor completo.
     * Permite escolher o numero inicial das opcoes mostradas.
     * 
     * @param titulo
     * @param enunciado
     * @param inicio 
     */
    public Menu(String titulo, String enunciado, int inicio) {
        this.titulo = titulo;
        this.enunciado = enunciado;
        this.inicio = inicio;
        contador = inicio;
        opcoes = new java.util.HashMap<>();
    }
    
    /**
     * Construtor padrao.
     * O valor da opcao inicial mostrado eh 1.
     * 
     * @param titulo
     * @param enunciado 
     */
    public Menu(String titulo, String enunciado) {
        this(titulo, enunciado, 1);
    }
    
    /**
     * Construtor sem enunciado.
     * 
     * @param titulo 
     */
    public Menu(String titulo) {
        this(titulo, null, 1);
    }
 
    /**
     * Construtor com carga inicial de opcoes.
     * O texto descritivo eh igual ao texto resumo. Serve para cores, etc. 
     *
     * @param titulo
     * @param opts 
     */
    public Menu(String titulo, String[] opts){
        this.titulo = titulo;
        enunciado = "Escolha uma das opcoes abaixo:";
        inicio = 1;
        contador = 1;
        this.opcoes = new java.util.HashMap<>();
        for(String s : opts)
            inclui(s,s);
    }
    
    /**
     * Insere uma opcao no menu.
     * 
     * @param curta
     * @param longa 
     */
    public void inclui(String curta, String longa) {
        Opcao item = new Opcao(curta, longa);
        opcoes.put(contador, item);
        contador++;
    }

    /**
     * Mostra as opcoes para o usuario, junto com um prompt.
     */
    public void exibe() {
        System.out.println("");
        if (titulo != null) {
            System.out.println(titulo);
            System.out.println("");
        }
        if (enunciado != null) 
            System.out.println(enunciado);
        else
            System.out.println("Digite uma das opcoes abaixo:");
        for (int i = inicio; i < contador; i++) {
            System.out.println("" + i + " - " + opcoes.get(i));
        }
        System.out.println("");
        System.out.print(">> ");
    }

    /**
     * Pede uma escolha para o usuario e retorna o resumo associado.
     * 
     * @return texto resumo da opcao escolhida pelo usuario
     */
    public String opcao() {
        java.util.Scanner input = new java.util.Scanner(System.in);
        int op;
        do {
            exibe();
            op = input.nextInt();
            if (op >= contador || op < inicio) {
                System.out.println("Opcao invalida. Tente novamente:");
            }
        } while (op >= contador || op < inicio);
        return opcoes.get(op).opcao();
    }
    
}
