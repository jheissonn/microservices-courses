package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.http.OrderClient;
import br.com.alurafood.pagamentos.model.Payment;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PaymentRepositoy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoy repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderClient pedido;


    public Page<PaymentDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto obterPorId(Long id) {
        Payment pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PaymentDto.class);
    }

    public PaymentDto criarPagamento(PaymentDto dto) {
        Payment pagamento = modelMapper.map(dto, Payment.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PaymentDto.class);
    }

    public PaymentDto atualizarPagamento(Long id, PaymentDto dto) {
        Payment pagamento = modelMapper.map(dto, Payment.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PaymentDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id){
        Optional<Payment> pagamento = repository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedido.atualizaPagamento(pagamento.get().getPedidoId());
    }


    public void alteraStatus(Long id) {
        Optional<Payment> pagamento = repository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento.get());

    }
}

