package br.com.fiap.guilherme561910.controller;

import br.com.fiap.guilherme561910.service.LeituraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("limiarAlerta", LeituraService.LIMIAR_ALERTA);
        model.addAttribute("limiarAtencao", LeituraService.LIMIAR_ATENCAO);
        return "index";
    }

}
