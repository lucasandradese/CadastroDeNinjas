package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController{

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " com o id: " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinja(){
        List<NinjaDTO> ninjas = ninjaService.listarNinja();
        return ResponseEntity.ok(ninjas);
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listaPorId(id);
       if(ninja != null){
           return ResponseEntity.ok(ninja);
       }else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).
                   body("Ninja com id: " + id + " não existe no nossos registros");
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaID(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
       NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
       if(ninja != null){
           return ResponseEntity.ok(ninja);
       }else
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja com id: " + id + " não encontrado nos nossos registros");
    }

    //Deletar ninja do cadastro por ID (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaID(@PathVariable Long id){
        if(ninjaService.listaPorId(id) != null){
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com id: " + id + " deletado com sucesso");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não encontrado");
    }
}
