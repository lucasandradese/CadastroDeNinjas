package dev.java10x.CadastroDeNinjas.Missoes;


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
    public ResponseEntity<String>  criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novamissao = missoesService.criarMissao(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão com id: " + novamissao.getId() + " e nome: " + novamissao.getNome());
    }

    // GET -- Mandar uma requisição para mostrar todas os missoes (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> monstarTodasMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // GET -- Mandar uma requisição para procurar missao por ID (READ)
    @GetMapping("/listarId/{id}")
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
    public ResponseEntity<String> deletarMissaoID(@PathVariable Long id){
        if(missoesService.listarMissoesId(id) != null){
            missoesService.delete(id);
            return ResponseEntity.ok("Missão com id " + id + " deletado com sucesso");
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id: " + id + " não existe no nossos registros");
    }
}

