package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class NinjaController {

    // Adicionar ninja (CREATE)
    @PostMapping("/cadastrarNinja")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/monstarTodosNinjas")
    public String monstarTodosNinjas(){
        return "Lista de ninjas cadastrados";
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/mostarNinjaId")
    public String procurarNinjaID(){
        return "Ninja encontrado por ID";
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PutMapping("/alterarNinjaID")
    public String alterarNinjaID(){
        return "Alterado dados do ninja com sucesso";
    }

    //Deletar ninja do cadastro por ID (DELETE)
    @DeleteMapping("/deletarNinjaID")
    public String deletarNinjaID(){
        return "Ninja deletado";
    }

}
