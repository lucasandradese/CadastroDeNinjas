package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os ninjas
    public List<NinjaDTO> listarNinja() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();

        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar Ninjas por ID
    public NinjaDTO listaPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
       NinjaModel ninja = ninjaMapper.map(ninjaDTO);
       ninja = ninjaRepository.save(ninja);
       return ninjaMapper.map(ninja);
    }

    // Deletar ninja cadastrado - tem que ser um metodo void
    public void deletarNinja(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Alterar dados no ninja cadastro
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninja = ninjaRepository.findById(id);
        if (ninja.isPresent()) {
            NinjaModel ninjaExistente = ninja.get();

            // Atualiza apenas os campos enviados
            if (ninjaDTO.getNome() != null)
                ninjaExistente.setNome(ninjaDTO.getNome());

            if (ninjaDTO.getEmail() != null)
                ninjaExistente.setEmail(ninjaDTO.getEmail());

            if (ninjaDTO.getImg_url() != null)
                ninjaExistente.setImg_url(ninjaDTO.getImg_url());

            if (ninjaDTO.getIdade() != 0)
                ninjaExistente.setIdade(ninjaDTO.getIdade());

            if (ninjaDTO.getRank() != null)
                ninjaExistente.setRank(ninjaDTO.getRank());

            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaExistente);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }
}
