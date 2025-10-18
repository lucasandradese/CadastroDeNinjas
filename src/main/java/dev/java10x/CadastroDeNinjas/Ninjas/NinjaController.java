package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Rota para criação de ninjas", description = "Com esse rota o cliente " +
            "pode enviar uma requisição com os dados necessarios para registrar um ninja no bando de dados")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Ninjas criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Houve um falha na criação do ninja no banco de dados")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " com o id: " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista de ninjas cadastrados", description = "A rota devolve a requisição do cliente um" +
            "lista de todos o ninjas cadastrados incluindo a foreign key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Requisição de lista enviado com sucesso")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinja(){
        List<NinjaDTO> ninjas = ninjaService.listarNinja();
        return ResponseEntity.ok(ninjas);
    }

    // Procurar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar ninja por ID", description = "A rota envia uma lista completa do ninja de acrodo com id enviado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista de ninja enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado com Id informado")
    })
    public ResponseEntity<?> listarNinjaId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listaPorId(id);
       if(ninja != null){
           return ResponseEntity.ok(ninja);
       }else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).
                   body("Ninja com id: " + id + " não existe no nossos registros");
    }

    // Alterar dados no ninja por ID (UPDATE)
   @PatchMapping("/alterar/{id}")
   @Operation(summary = "Atualizar dado do ninja", description = "Rota para atualizar dados do ninja de acordo em envio do id")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Ninja atualizado com sucesso"),
           @ApiResponse(responseCode = "404", description = "Ninja não encontrado nos registro de acordo com ID informado")
   })
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
    @Operation(summary = "Deletar ninja", description = "Rota para deletar ninja de acordo em envio do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado nos registro de acordo com ID informado")
    })
    public ResponseEntity<String> deletarNinjaID(@PathVariable Long id){
        if(ninjaService.listaPorId(id) != null){
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com id: " + id + " deletado com sucesso");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não encontrado");
    }
}
