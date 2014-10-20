package ui;

import lav.*;

/**
 * Interface com usuario para sistema de lavanderia.
 * 
 * @author orlando de andrade figueiredo
 */
public class LinhaDeComando {
    private Lavanderia lav;
    private Clientela cl;
    private RolDeServicos rol;
    
    public LinhaDeComando(Lavanderia lav, Clientela cl, RolDeServicos rol){
        this.lav = lav;
        this.cl = cl;
        this.rol = rol;
    }
    
    /**
     * Metodo a ser chamado para iniciar a interface com usuario.
     */
    public void executa() {

        Menu m = new Menu("MENU PRINCIPAL");
        m.inclui("roupas", "Roupas");
        m.inclui("relatorios", "Relatorios");
        m.inclui("clientes", "Clientes");
        m.inclui("servicos", "Servicos");
        m.inclui("sair", "Sair");
        
        do {
            switch (m.opcao()) {
                case "roupas":
                    roupas();
                    break;
                case "relatorios":
                    relatorios();
                    break;
                case "clientes":
                    clientes();
                    break;
                case "servicos":
                    servicos();
                    break;
                case "sair":
                    System.out.println("Fim");
                    return;
            }
        } while ( true );
    }
    
    private void roupas() {
        Menu m = new Menu("MENU ROUPAS");
        m.inclui("receber", "Receber roupas para lavar");
        m.inclui("limpas", "Disponibilizar roupas limpas");
        m.inclui("devolver", "Entregar roupas");
        m.inclui("sair", "Voltar");
        
        do {
            switch (m.opcao()) {
                case "receber":
                    receber();
                    break;
                case "limpas":
                    Dilema d = new Dilema("Mais alguma?");
                    do {
                        System.out.println("\nDISPONIBILIZAR CESTAS LAVADAS\nPedidos cadastrados:\n");
                        for (Cesta s : lav.getCestas(Cesta.SUJA, 1000))
                            System.out.println(s);
                        System.out.print("\nInforme o código da cesta:\n>> ");
                        if (lav.disponibiliza((new java.util.Scanner(System.in).nextInt()))) {
                            System.out.println("Feito");
                        } else {
                            System.out.println("Codigo desconhecido");
                        }
                    } while (d.decida());           
                    break;
                case "devolver":
                    System.out.println("\nENTREGAR ROUPAS LAVADAS\nPedidos cadastrados:\n");
                    for (Cesta s : lav.getCestas(Cesta.LIMPA, 1000))
                        System.out.println(s);
                    System.out.print("\nInforme o código da cesta:\n>> ");
                    if (lav.devolve((new java.util.Scanner(System.in).nextInt()))) {
                        System.out.println("Feito");
                    } else {
                        System.out.println("Codigo desconhecido");
                    }
                    break;
                case "sair":
                    return;
            }
        } while ( true );
        
    }
    
    private void receber(){
        
        System.out.println("\nRECEBER CESTA\n");
        System.out.println("Informe o codigo do cliente conforme tabela abaixo:\n");
        for(Cliente l : cl.getTodosClientes()){
            System.out.println(l);
        }
        System.out.print("\n>> ");
        Cliente c = cl.getCliente((new java.util.Scanner(System.in)).nextInt());
        Cesta nova;
        if (cl != null) {
            nova = new Cesta(c);
        } else {
            System.out.println("Codigo invalido");
            return;
        }
        
        Menu m = new Menu("RECEBER ROUPAS");
        m.inclui("peca", "Adicionar pecas");
        m.inclui("recibo", "Imprimir recibo");
        m.inclui("etiquetas", "Imprimir etiquetas");
        m.inclui("sair", "Voltar");
        
        do {
            switch (m.opcao()) {
                case "peca":
                    adicionarItens(nova);
                    break;
                case "recibo":
                    nova.recibo(lav.codigoNovaCesta);
                    break;
                case "etiquetas":
                    nova.etiquetas(lav.codigoNovaCesta);
                    break;
                case "sair":
                    lav.adicionaCesta(nova);
                    return;
            }
        } while ( true );
        
    }
    
    private void adicionarItens(Cesta c) {

        Dilema m = new Dilema("Mais alguma?");
        do {
            String r = (new Menu("ADICIONAR PECA\n\nTipo de roupa", rol.todasAsRoupas())).opcao();
            String c1 = (new Menu("Qual a cor principal da peca?", rol.todasAsCores())).opcao();
            String m1 = (new Dilema("Deseja acrescentar detalhe de cor?").decida()) ? (new Menu("",rol.todosOsModificadores()).opcao()) : null;
            String c2 = null, m2 = null, e = "liso";
            if (new Dilema("Ha uma segunda cor relevante na peca?").decida()) {
                c2 = (new Menu("", rol.todasAsCores())).opcao();
                m2 = (new Dilema("Deseja acrescentar detalhe de cor?").decida()) ? (new Menu("",rol.todosOsModificadores()).opcao()) : null;
                e = (new Menu("Como as cores estao dispostas na peca?", rol.todosOsEstilos())).opcao();
            }
            String t = (new Menu("Qual o tipo de serviço?", rol.todosOsTipos())).opcao();
            double p = rol.consultaPreco(new Servico(r, c1, t, 0.0));
            if (p < 0) {
                p = rol.consultaPreco(new Servico(r, "qualquer", t, 0.0));
                if (p < 0) {
                    System.out.println("Servico inexistente");
                    return;
                }
            }

            Roupa n = new Roupa(r, c1, m1, c2, m2, e, t, p, 
                    (new Dilema("A peca tem algum defeito?")).decida());
            n.setCodigo(lav.codigoNovaPeca++);
            c.adicionaPeca(n);
        } while (m.decida());
    }
    
    private void clientes() {
        
        Menu m = new Menu("MENU CLIENTES");
        m.inclui("listar", "Todos os clientes");
        m.inclui("cadastrar", "Incluir novo cliente");
        m.inclui("remover", "Remover cliente");
        m.inclui("sair", "Voltar para Menu Principal");
        
        do {
            switch (m.opcao()) {
                case "listar":
                    for(Cliente c : cl.getTodosClientes()) {
                        System.out.println(c);
                    };
                    break;
                case "cadastrar":
                    System.out.print("\nNOVO CLIENTE\n\nDigite o primeiro nome:\n>> ");
                    java.util.Scanner input = new java.util.Scanner(System.in);
                    input.useDelimiter("\n");
                    String nome = input.next();
                    System.out.print("\nDigite o sobrenome:\n>> ");
                    String sobrenome = input.next();
                    System.out.print("\nDigite o endereco:\n>> ");
                    String end = input.next();
                    System.out.println(input.delimiter());
                    cl.adicionaCliente(new Cliente(nome, sobrenome, end));
                    break;
                case "remover":
                    System.out.print("\nREMOVER CLIENTE\n\nDigite o codigo:\n>> ");
                    cl.removeCliente((new java.util.Scanner(System.in).nextInt()));
                    break;                    
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
            
        } while (true);
    }
    
    private void servicos() {
        
        Menu m = new Menu("MENU SERVICOS");
        m.inclui("servicos", "Servicos");
        m.inclui("tipos", "Tipos de servico (lavar/secar/passar/etc.)");
        m.inclui("roupas", "Tipos de roupa (camisa/calca/bermuda/etc.)");
        m.inclui("cores", "Cores");
        m.inclui("sair", "Voltar para Menu Principal");
        
        do {
            switch (m.opcao()) {
                case "servicos":
                    servicos2();
                    break;
                case "tipos":
                    tipos();
                    break;
                case "roupas":
                    roupas2();
                    break;
                case "cores":
                    cores();
                    break;                    
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
            
        } while (true);
    }
    
    private void servicos2() {

        Menu m = new Menu("SERVICOS");
        m.inclui("listar", "Todos servicos");
        m.inclui("incluir", "Adicionar servico");
        m.inclui("remover", "Remover servico");
        m.inclui("alterar", "Alterar preco de servico");
        m.inclui("sair", "Voltar para menu anterior");
        
        Menu m2 = new Menu("NOVO SERVICO\n\nTipo de roupa", rol.todasAsRoupas());
        Dilema m3 = new Dilema("A cor define o servico?");
        Menu m4 = new Menu("Qual a cor que define o servico?", rol.todasAsCores());
        Menu m5 = new Menu("Tipo do servico", rol.todosOsTipos());
        
        do {
            switch (m.opcao()) {
                case "listar":
                    for(Servico s : rol.todosOsServicos())
                        System.out.println(s);
                    break;
                case "incluir":
                    Servico novo = new Servico(
                        m2.opcao(),
                        m3.decida() ? m4.opcao() : "qualquer",
                        m5.opcao(),
                        0.0);
                    System.out.print("\nQual o preco a ser cobrado?\n>> ");
                    novo.setPreco((new java.util.Scanner(System.in)).nextDouble());
                    rol.adicionaServico(novo);
                    break;
                case "remover":
                    //Mensagens: nao encontrado, feito, etc.
                    Servico alvo = new Servico(
                        m2.opcao(),
                        m3.decida() ? m4.opcao() : "qualquer",
                        m5.opcao(),
                        0.0);
                    boolean naoAchou = true;
                    for (Servico s : rol.todosOsServicos())
                        if (alvo.equals(s)) {
                            rol.removeServico(s);
                            System.out.println("\n--> SERVICO REMOVIDO");
                            naoAchou = false;
                            break;
                        }
                    if (naoAchou)
                        System.out.println("\n--> Servico inexistente");
                    break;                    
                case "alterar":
                    Servico alvo2 = new Servico(
                        m2.opcao(),
                        m3.decida() ? m4.opcao() : "qualquer",
                        m5.opcao(),
                        0.0);
                    boolean naoAchou2 = true;
                    for (Servico s : rol.todosOsServicos())
                        if (alvo2.equals(s)) {
                            System.out.println("\nO preco atual eh " + s.getPreco());
                            System.out.print("\nDigite o novo preco:\n>> ");
                            s.setPreco((new java.util.Scanner(System.in)).nextDouble());
                            System.out.println("\n--> PRECO ALTERADO");
                            naoAchou2 = false;
                            break;
                        }
                    if (naoAchou2)
                        System.out.println("\n--> Servico inexistente");
                    break;                    
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
        } while (true);
    }
    
    private void tipos() {

        Menu m = new Menu("TIPOS DE SERVICO");
        m.inclui("listar", "Todos os tipos de servico");
        m.inclui("incluir", "Adicionar um novo tipo de servico");
        m.inclui("remover", "Remover um tipo de servico");
        m.inclui("sair", "Voltar para menu anterior");
        
        do {
            switch (m.opcao()) {
                case "listar":
                    for(String s : rol.todosOsTipos())
                        System.out.println(s);
                    break;
                case "incluir":
                    System.out.print("/nDigite uma palavra que descreve o tipo:\n>> ");
                    rol.adicionaTipo((new java.util.Scanner(System.in)).next());
                    break;
                case "remover":
                    Menu m2 = new Menu("Remover tipo", rol.todosOsTipos());
                    rol.removeTipo(m2.opcao());
                    break;                    
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
        } while (true);
    }

    private void roupas2() {

        Menu m = new Menu("TIPOS DE ROUPAS");
        m.inclui("listar", "Todos os tipos de roupa");
        m.inclui("incluir", "Adicionar um novo tipo de roupa");
        m.inclui("remover", "Remover um tipo de roupa");
        m.inclui("sair", "Voltar para menu anterior");
        
        do {
            switch (m.opcao()) {
                case "listar":
                    for(String s : rol.todasAsRoupas())
                        System.out.println(s);
                    break;
                case "incluir":
                    System.out.print("/nDigite uma palavra que descreve o tipo:\n>> ");
                    rol.adicionaRoupa((new java.util.Scanner(System.in)).next());
                    break;
                case "remover":
                    Menu m2 = new Menu("Remover tipo", rol.todasAsRoupas());
                    rol.removeRoupa(m2.opcao());
                    break;                    
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
        } while (true);
    }

    private void cores() {

        Menu m = new Menu("CORES");
        m.inclui("listar", "Todos as cores e modificadores");
        m.inclui("incluir", "Adicionar um nova cor");
        m.inclui("incluir2", "Adicionar um novo modificador");
        m.inclui("remover", "Remover uma cor");
        m.inclui("remover2", "Remover um modificador");
        m.inclui("sair", "Voltar para menu anterior");
        
        do {
            switch (m.opcao()) {
                case "listar":
                    System.out.println("Cores:");
                    for(String s : rol.todasAsCores())
                        System.out.println(s);
                    System.out.println("\nModificadores:");
                    for(String s2 : rol.todosOsModificadores())
                        System.out.println(s2);
                    break;
                case "incluir":
                    System.out.print("/nDigite uma palavra que descreve a cor:\n>> ");
                    rol.adicionaCor((new java.util.Scanner(System.in)).next());
                    break;
                case "incluir2":
                    System.out.print("/nDigite uma palavra que descreve o modificador:\n>> ");
                    rol.adicionaModificador((new java.util.Scanner(System.in)).next());
                    break;
                case "remover":
                    Menu m2 = new Menu("Remover cor", rol.todasAsCores());
                    rol.removeCor(m2.opcao());
                    break;                    
                case "remover2":
                    Menu m3 = new Menu("Remover modificador", rol.todosOsModificadores());
                    rol.removeModificador(m3.opcao());
                case "sair":
                    return;
                default:
                    System.out.println("Nao implementado");
            }
        } while (true);
    }

    private void relatorios() {
        Menu m = new Menu("MENU DE RELATORIOS");
        m.inclui("caixa", "Pedidos recebidos por periodo");
        m.inclui("prontos", "Pedidos prontos por periodo");
        m.inclui("entregues", "Pedidos entregues por periodo");
        m.inclui("tudo", "Relacao completa de pedidos");
        m.inclui("sair", "Voltar para o menu anterior");
        
        int estados = 0; 
        double preco;
        
        do {
            preco = 0.0;
            switch (m.opcao()) {
                case "caixa":
                    estados = Cesta.SUJA;
                    break;
                case "prontos":
                    estados = Cesta.LIMPA;
                    break;
                case "entregues":
                    estados = Cesta.ENTREGUE;
                    break;
                case "tudo":
                    estados = Cesta.SUJA + Cesta.LIMPA + Cesta.ENTREGUE;
                    break;
                case "sair":
                    return;
            }
            
            System.out.print("\nQual eh o periodo? (0 = hoje, 1 = ontem e hoje, etc.)\n\n>> ");
            int dias = (new java.util.Scanner(System.in)).nextInt();
            System.out.println("\nPEDIDOS:\n");
            for (Cesta c : lav.getCestas(estados, dias)) {
                System.out.println(c);
                preco += c.total();
            }
            System.out.println("\nTotal : " + java.text.NumberFormat.getCurrencyInstance().format(preco));
            
        } while (true);
    }
}
