package br.com.fiap.guilherme561910.controller;

import br.com.fiap.guilherme561910.dto.LeituraDTO;
import br.com.fiap.guilherme561910.service.LeituraService;
import br.com.fiap.guilherme561910.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/leituras")
public class LeituraController {

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    public LeituraController(LeituraService leituraService, TrechoService trechoService) {
        this.leituraService = leituraService;
        this.trechoService = trechoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        model.addAttribute("limiarAlerta", LeituraService.LIMIAR_ALERTA);
        model.addAttribute("limiarAtencao", LeituraService.LIMIAR_ATENCAO);
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        carregarOpcoesFormulario(model);
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("leitura", leituraService.buscarPorId(id));
            carregarOpcoesFormulario(model);
            return "leituras/form";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/leituras";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO dto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            carregarOpcoesFormulario(model);
            return "leituras/form";
        }
        try {
            LeituraDTO salvo = leituraService.salvar(dto);
            redirectAttributes.addFlashAttribute("mensagemSucesso",
                    "Leitura salva com status " + salvo.getStatus() + ".");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            leituraService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Leitura excluída com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/leituras";
    }

    private void carregarOpcoesFormulario(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        model.addAttribute("limiarAlerta", LeituraService.LIMIAR_ALERTA);
        model.addAttribute("limiarAtencao", LeituraService.LIMIAR_ATENCAO);
    }

}
