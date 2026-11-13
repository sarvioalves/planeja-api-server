package io.github.sa.planeja.dominio.lancamento;

import io.github.sa.planeja.dominio.cartao.CartaoService;
import io.github.sa.planeja.dominio.cartao.dto.CartaoDetalhes;
import io.github.sa.planeja.dominio.categoria.CategoriaService;
import io.github.sa.planeja.dominio.categoria.dto.CategoriaDetalhes;
import io.github.sa.planeja.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.sa.planeja.dominio.lancamento.dto.LancamentoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lancamentos")
public class CadastroLancamentoController {

    @Autowired
    private LancamentoService service;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CartaoService cartaoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoDetalhes criar(@RequestBody @Valid LancamentoForm form){
        return service.criar(form);
    }

    @GetMapping("categorias-disponiveis")
    public List<CategoriaDetalhes> listarCategoriasDisponiveis(){
        return categoriaService.listarAtivas();
    }

    @GetMapping("cartoes-disponiveis")
    public List<CartaoDetalhes> listarCartoesDisponiveis(){
        return cartaoService.listarAtivos();
    }

}
