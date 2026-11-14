package br.com.fiap.guilherme561910.controller;

import br.com.fiap.guilherme561910.dto.BairroDTO;
import br.com.fiap.guilherme561910.dto.PainelItemDTO;
import br.com.fiap.guilherme561910.service.BairroService;
import br.com.fiap.guilherme561910.service.LeituraService;
import br.com.fiap.guilherme561910.service.PainelService;
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

import java.util.List;

@Controller
@RequestMapping("/bairros")
public class BairroController {

    private static final List<String> ZONAS = List.of("Centro", "Norte", "Sul", "Leste", "Oeste");

    private final BairroService bairroService;
    private final PainelService painelService;

    public BairroController(BairroService bairroService, PainelService painelService) {
        this.bairroService = bairroService;
        this.painelService = painelService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        model.addAttribute("zonas", ZONAS);
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("bairro", bairroService.buscarPorId(id));
            model.addAttribute("zonas", ZONAS);
            return "bairros/form";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/bairros";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO dto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("zonas", ZONAS);
            return "bairros/form";
        }
        try {
            bairroService.salvar(dto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Bairro salvo com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/bairros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bairroService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Bairro excluído com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/bairros";
    }

    @GetMapping("/{id}/painel")
    public String painel(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            BairroDTO bairro = bairroService.buscarPorId(id);
            List<PainelItemDTO> itens = painelService.montarPainel(id);
            model.addAttribute("bairro", bairro);
            model.addAttribute("itens", itens);
            model.addAttribute("limiarAlerta", LeituraService.LIMIAR_ALERTA);
            model.addAttribute("limiarAtencao", LeituraService.LIMIAR_ATENCAO);
            model.addAttribute("qtdNormal", painelService.contarPorStatus(itens, "NORMAL"));
            model.addAttribute("qtdAtencao", painelService.contarPorStatus(itens, "ATENCAO"));
            model.addAttribute("qtdAlerta", painelService.contarPorStatus(itens, "ALERTA"));
            return "bairros/painel";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/bairros";
        }
    }

}
