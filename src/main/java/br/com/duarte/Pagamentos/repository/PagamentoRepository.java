package br.com.duarte.Pagamentos.repository;

import br.com.duarte.Pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {
}
