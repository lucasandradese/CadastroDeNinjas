package dev.java10x.CadastroDeNinjas.Ninjas;

import lombok.Data;
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
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
        return ninjaService.criarNinja(ninja);
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinja(){
        return ninjaService.listarNinja();
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjaId(@PathVariable Long id){
        retucgrn ninjaService.listaPorId(id);
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaID(@PathVariable Long id, NinjaModel ninjaAtualizado){
        return ninjaService.alterarDados(id, ninjaAtualizado);
    }

    //Deletar ninja do cadastro por ID (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaID(@PathVariable Long id){
        ninjaService.deletarNinja(id);
    }
}
