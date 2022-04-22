package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.State;
import br.com.alelo.consumer.consumerpat.exception.StateNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.StateRepository;
import br.com.alelo.consumer.consumerpat.services.StateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findOrFail(Long id) {
        return stateRepository.findById(id).orElseThrow(() -> new StateNotFoundException(id));
    }
}
