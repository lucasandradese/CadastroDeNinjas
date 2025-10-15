package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController{

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinja(){
        return ninjaService.listarNinja();
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjaId(@PathVariable Long id){
        return ninjaService.listaPorId(id);
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PutMapping("/alterar")
    public String alterarNinjaID(){
        return "Alterado dados do ninja com sucesso";
    }

    //Deletar ninja do cadastro por ID (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinjaID(){
        return "Ninja deletado";
    }

}
