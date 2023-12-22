package br.com.prova.listatarefas.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.listatarefas.domain.DTO.TarefaDTO;
import br.com.prova.listatarefas.domain.model.Tarefa;
import br.com.prova.listatarefas.domain.repository.TarefaRepository;

@Service
public class TarefaService {
  @Autowired
  private TarefaRepository repository;

  public List<Tarefa> findTarefas() {
    return this.repository.findAll();
  }

  public void saveTarefa(TarefaDTO tarefa) {
    Tarefa novaTarefa = new Tarefa(tarefa);
    this.repository.save(novaTarefa);
  
  }

  public Tarefa findTarefaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("tarefa não encontrada"));
  }

  public Tarefa updateTarefa(Long id, TarefaDTO dto) throws Exception {
    var novaTarefa = this.findTarefaById(id);
    novaTarefa.atualizarTarefa(dto);
    return this.repository.save(novaTarefa);
  }

  public void deleteTarefa(Long id) throws Exception {
    var tarefa = this.findTarefaById(id);
    this.repository.delete(tarefa);
  }
}
