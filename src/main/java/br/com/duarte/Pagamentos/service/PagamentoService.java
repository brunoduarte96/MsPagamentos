package br.com.duarte.Pagamentos.service;

import br.com.duarte.Pagamentos.dto.PagamentoDto;
import br.com.duarte.Pagamentos.model.Pagamento;
import br.com.duarte.Pagamentos.model.Status;
import br.com.duarte.Pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> findAllPagamento(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto findByIdPagamento(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto savePagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto updatePagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void deletePagamento(Long id) {
        repository.deleteById(id);
    }


}
