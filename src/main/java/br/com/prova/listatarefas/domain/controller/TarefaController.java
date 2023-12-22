package br.com.prova.listatarefas.domain.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.listatarefas.domain.DTO.TarefaDTO;
import br.com.prova.listatarefas.domain.model.Tarefa;
import br.com.prova.listatarefas.domain.service.TarefaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/tarefas")
public class TarefaController {
  @Autowired
  private TarefaService service;

  @GetMapping
  public ResponseEntity<List<Tarefa>> findTarefas() {
    List<Tarefa> listaTarefas = this.service.findTarefas();
    
    if(listaTarefas.size() <= 0) {
      return new ResponseEntity<List<Tarefa>>( HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Tarefa>>(listaTarefas, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Tarefa> saveTarefa(@RequestBody TarefaDTO dto) {
      this.service.saveTarefa(dto);
      
      return ResponseEntity.ok().build();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> findTarefaById(@PathVariable Long id) throws Exception {
      Tarefa novaTarefa = this.service.findTarefaById(id);
      if(Objects.isNull(novaTarefa)) {
        return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Tarefa>(novaTarefa, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) throws Exception {
    var tarefa = this.service.updateTarefa(id, dto);
    if(Objects.isNull(tarefa)) {
      return new ResponseEntity<Tarefa>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tarefa> deleteTarefa(@PathVariable Long id) throws Exception {
    this.service.deleteTarefa(id);
    return ResponseEntity.ok().build();
  }

}
