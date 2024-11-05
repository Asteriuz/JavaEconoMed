package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.economed.repository.*;

@Controller
public class Thymeleaf {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private HistoricoHospitalClienteRepository historicoHospitalClienteRepository;

    @Autowired
    private EnderecoUnidadeRepository enderecoUnidadeRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ConvenioRepository convenioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/estadosView")
    public String estadosView(Model model) {
        model.addAttribute("estados", estadoRepository.findAll());
        return "estadosView";
    }

    @GetMapping("/historicoHospitalClienteView")
    public String historicoHospitalClienteView(Model model) {
        model.addAttribute("historicos", historicoHospitalClienteRepository.findAll());
        return "historicoHospitalClienteView";
    }

    @GetMapping("/enderecosUnidadeView")
    public String enderecosUnidadeView(Model model) {
        model.addAttribute("enderecos", enderecoUnidadeRepository.findAll());
        return "enderecosUnidadeView";
    }

    @GetMapping("/unidadesView")
    public String unidadesView(Model model) {
        model.addAttribute("unidades", unidadeRepository.findAll());
        return "unidadesView";
    }

    @GetMapping("/cidadesView")
    public String cidadesView(Model model) {
        model.addAttribute("cidades", cidadeRepository.findAll());
        return "cidadesView";
    }

    @GetMapping("/clientesView")
    public String clientesView(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientesView";
    }

    @GetMapping("/conveniosView")
    public String conveniosView(Model model) {
        model.addAttribute("convenios", convenioRepository.findAll());
        return "conveniosView";
    }

    @GetMapping("/empresasView")
    public String empresasView(Model model) {
        model.addAttribute("empresas", empresaRepository.findAll());
        return "empresasView";
    }

    @GetMapping("/enderecosClienteView")
    public String enderecosClienteView(Model model) {
        model.addAttribute("enderecosCliente", enderecoClienteRepository.findAll());
        return "enderecosClienteView";
    }

    @GetMapping("/historicoSaudeClienteView")
    public String historicoSaudeClienteView(Model model) {
        model.addAttribute("historicosSaude", historicoSaudeClienteRepository.findAll());
        return "historicoSaudeClienteView";
    }
}