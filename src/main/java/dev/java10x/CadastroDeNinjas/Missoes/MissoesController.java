package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MissoesController {

    // Adicionar missao (CREATE)
    @PostMapping("/cadastrarMissao")
    public String criarMissao(){
        return "Missao criada";
    }

    // Mostrar todas os missoes (READ)
    @GetMapping("/monstarTodasMissoes")
    public String monstarTodasMissoes(){
        return "Lista de missoes cadastradas";
    }

    // Procurar missao por ID (READ)
    @GetMapping("/mostarMissaoId")
    public String procurarMissaoID(){
        return "Missao encontrado por ID";
    }

    // Alterar dados no missao por ID (UPDATE)
   @PutMapping("/alterarMissoaID")
    public String alterarMissaoID(){
        return "Alterado dados da missao com sucesso";
    }

    //Deletar missao do cadastro por ID (DELETE)
    @DeleteMapping("/deletarMissaoID")
    public String deletarMissaoID(){
        return "Missao deletada";
    }
}

