package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")

public class NinjaControllerUi {
    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public String listarNinja(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinja();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // tem que retornar o nome da pagina que renderiza
    }
}
