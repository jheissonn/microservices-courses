package br.com.alurafood.pedidos.service;

import br.com.alurafood.pedidos.dto.OrderDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.model.Order;
import br.com.alurafood.pedidos.model.Status;
import br.com.alurafood.pedidos.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDto> obterTodos() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto obterPorId(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto criarPedido(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDataHora(LocalDateTime.now());
        order.setStatus(Status.REALIZADO);
        order.getItens().forEach(item -> item.setOrder(order));
        Order salvo = repository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto atualizaStatus(Long id, StatusDto dto) {

        Order order = repository.porIdComItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), order);
        return modelMapper.map(order, OrderDto.class);
    }

    public void aprovaPagamentoPedido(Long id) {

        Order order = repository.porIdComItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, order);
    }
}
