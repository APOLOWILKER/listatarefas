package br.com.prova.listatarefas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prova.listatarefas.domain.model.Tarefa;

public interface TarefaRepository extends JpaRepository< Tarefa ,Long> {

  
}
