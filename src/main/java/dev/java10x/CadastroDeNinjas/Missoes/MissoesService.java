package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesMapper missoesMapper;
    private final MissoesRepository missoesRepository;

    public MissoesService(MissoesMapper missoesMapper, MissoesRepository missoesRepository) {
        this.missoesMapper = missoesMapper;
        this.missoesRepository = missoesRepository;
    }

    // Lista todas as missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();

        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // Lista a missão por ID
    public MissoesDTO listarMissoesId(Long id){
        Optional<MissoesModel> missoesId = missoesRepository.findById(id);
        return missoesId.map(missoesMapper::map).orElse(null);
    }

    // Criar missoes
    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    // Deletar missoes
    public void delete(Long id){
        missoesRepository.deleteById(id);
    }
    // Atualizar missão
    public MissoesDTO missoesAlterar(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missao = missoesRepository.findById(id);
        if(missao.isPresent()){
            MissoesModel missaoExistente = missao.get();

            if(missoesDTO.getNome() != null){
                missaoExistente.setNome(missoesDTO.getNome());
            }
            if (missoesDTO.getDificuldade() != null){
                missaoExistente.setDificuldade(missoesDTO.getDificuldade());
            }
            MissoesModel missoesSalvo = missoesRepository.save(missaoExistente);
            return missoesMapper.map(missoesSalvo);
        }
        return null;
    }
}
