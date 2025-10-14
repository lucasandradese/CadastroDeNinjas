package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    // POST -- Mandar uma requisição para criar missao (CREATE)
    @PostMapping("/cadastrar")
    public String criarMissao(){
        return "Missao criada";
    }

    // GET -- Mandar uma requisição para mostrar todas os missoes (READ)
    @GetMapping("/monstarTodos")
    public String monstarTodasMissoes(){
        return "Lista de missoes cadastradas";
    }

    // GET -- Mandar uma requisição para procurar missao por ID (READ)
    @GetMapping("/mostarID")
    public String procurarMissaoID(){
        return "Missao encontrado por ID";
    }

    // PUT -- Mandar uma requisição para alterar dados no missao por ID (UPDATE)
   @PutMapping("/alterarID")
    public String alterarMissaoID(){
        return "Alterado dados da missao com sucesso";
    }

    //DELETE -- Mandar uma requisição para deletar missao do cadastro por ID (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarMissaoID(){
        return "Missao deletada";
    }
}

