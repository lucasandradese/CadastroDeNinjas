package dev.java10x.CadastroDeNinjas.Missoes;


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
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoes){
        return missoesService.criarMissao(missoes);
    }

    // GET -- Mandar uma requisição para mostrar todas os missoes (READ)
    @GetMapping("/listar")
    public List<MissoesDTO> monstarTodasMissoes(){
        return missoesService.listarMissoes();
    }

    // GET -- Mandar uma requisição para procurar missao por ID (READ)
    @GetMapping("/listarId/{id}")
    public MissoesDTO procurarMissaoID(@PathVariable Long id){
        return missoesService.listarMissoesId(id);
    }

    // PATCH -- Mandar uma requisição para alterar dados no missao por ID (UPDATE)
   @PatchMapping("/alterar/{id}")
    public MissoesDTO alterarMissaoID(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        return missoesService.missoesAlterar(id, missoesDTO);
    }

    //DELETE -- Mandar uma requisição para deletar missao do cadastro por ID (DELETE)
    @DeleteMapping("/deletar")
    public void deletarMissaoID(@PathVariable Long id){
        missoesService.delete(id);
    }
}

