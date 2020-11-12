package com.br.viasoft.model.repository;

import java.util.Optional;
import java.util.UUID;

import com.br.viasoft.model.enumerations.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.viasoft.model.entity.State;

public interface StateRepository extends JpaRepository<State, UUID>{

    State findByState(StateEnum state);
}
