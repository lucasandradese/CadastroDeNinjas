package dev.java10x.CadastroDeNinjas.Missoes;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // POST -- Mandar uma requisição para criar missao (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Rota para criação de novas missoes", description = "Com esse rota o cliente \" +\n" +
            "            \"pode enviar uma requisição com os dados necessarios para registrar um ninja no bando de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Houve falha na criação da missão no banco de dados")
    })
    public ResponseEntity<String>  criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novamissao = missoesService.criarMissao(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão com id: " + novamissao.getId() + " e nome: " + novamissao.getNome());
    }

    // GET -- Mandar uma requisição para mostrar todas os missoes (READ)
    @GetMapping("/listar")
    @Operation(summary = "Rota de listar todas as missões", description = "A rota devolve ao cliente \" +\n" +
            "            \"uma lista de missões já cadastradas no banco de dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista exigida com sucesso"),
    })
    public ResponseEntity<List<MissoesDTO>> monstarTodasMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // GET -- Mandar uma requisição para procurar missao por ID (READ)
    @GetMapping("/listarId/{id}")
    @Operation(summary = "Rota de listar a missão por id", description = "O cliente requisita uma missão \" +\n" +
            "            \" por id e a rota devolve os dados da missão solicida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão exigida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada com o id informado")
    })
    public ResponseEntity<?> procurarMissaoID(@PathVariable Long id){
        MissoesDTO missaoId = missoesService.listarMissoesId(id);
        if(missaoId != null){
            return ResponseEntity.ok(missaoId);
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada com esse id: " + id);
    }

    // PATCH -- Mandar uma requisição para alterar dados no missao por ID (UPDATE)
   @PatchMapping("/alterar/{id}")
   @Operation(summary = "Rota de atualização", description = "Com base no id a rota realiza a atualização necessária")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Dados a missão atualizada com sucesso"),
           @ApiResponse(responseCode = "404", description = "Missão não encontrada com o id informado")
   })
    public ResponseEntity<?> alterarMissaoID(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        MissoesDTO missaoId = missoesService.missoesAlterar(id, missoesDTO);
        if (missaoId != null){
            return ResponseEntity.ok(missaoId);
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id: " + id + " não existe no nossos registros");
    }

    //DELETE -- Mandar uma requisição para deletar missao do cadastro por ID (DELETE)
    @DeleteMapping("/deletar")
    @Operation(summary = "Rota de deletação", description = "A rota faz exclusão de missão com base no id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada com o id informado")
    })
    public ResponseEntity<String> deletarMissaoID(@PathVariable Long id){
        if(missoesService.listarMissoesId(id) != null){
            missoesService.delete(id);
            return ResponseEntity.ok("Missão com id " + id + " deletado com sucesso");
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id: " + id + " não existe no nossos registros");
    }
}

