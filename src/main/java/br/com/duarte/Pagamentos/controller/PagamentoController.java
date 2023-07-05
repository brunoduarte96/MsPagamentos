package br.com.duarte.Pagamentos.controller;

import br.com.duarte.Pagamentos.dto.PagamentoDto;
import br.com.duarte.Pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<PagamentoDto> list(@PageableDefault(size = 10) Pageable pageable) {
        return service.findAllPagamento(pageable);
    }

    @GetMapping
    public ResponseEntity<PagamentoDto> findById(@PathVariable @NotNull Long id) {
        PagamentoDto dto = service.findByIdPagamento(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> Save(@RequestBody PagamentoDto dto, UriComponentsBuilder uriBuilder) {
        PagamentoDto pagamento = service.savePagamento(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping
    public ResponseEntity<PagamentoDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto dto) {
        PagamentoDto atualizado = service.updatePagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping
    public ResponseEntity<PagamentoDto> delete(@PathVariable @NotNull Long id) {
        service.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }

}
