package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    // Adicionar ninja (CREATE)
    @PostMapping("/cadastrar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/monstarTodos")
    public String monstarTodosNinjas(){
        return "Lista de ninjas cadastrados";
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/mostarID")
    public String procurarNinjaID(){
        return "Ninja encontrado por ID";
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PutMapping("/alterarID")
    public String alterarNinjaID(){
        return "Alterado dados do ninja com sucesso";
    }

    //Deletar ninja do cadastro por ID (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaID(){
        return "Ninja deletado";
    }

}
