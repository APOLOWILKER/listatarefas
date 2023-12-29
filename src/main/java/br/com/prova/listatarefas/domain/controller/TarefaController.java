package br.com.prova.listatarefas.domain.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.prova.listatarefas.domain.DTO.TarefaDTO;
import br.com.prova.listatarefas.domain.model.Tarefa;
import br.com.prova.listatarefas.domain.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {
  @Autowired
  private TarefaService service;

  @CrossOrigin
  @GetMapping
  public ResponseEntity<List<Tarefa>> findTarefas() {
    List<Tarefa> listaTarefas = this.service.findTarefas();
    
    if(listaTarefas.size() <= 0) {
      return new ResponseEntity<List<Tarefa>>( HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Tarefa>>(listaTarefas, HttpStatus.OK);
  }

  @CrossOrigin
  @PostMapping()
  public ResponseEntity<Tarefa> saveTarefa(@RequestBody TarefaDTO dto) {
      this.service.saveTarefa(dto);
      
      return ResponseEntity.ok().build();
  }

  
  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> findTarefaById(@PathVariable Long id) throws Exception {
      Tarefa novaTarefa = this.service.findTarefaById(id);
      if(Objects.isNull(novaTarefa)) {
        return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Tarefa>(novaTarefa, HttpStatus.OK);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto) throws Exception {
    var tarefa = this.service.updateTarefa(id, dto);
    if(Objects.isNull(tarefa)) {
      return new ResponseEntity<Tarefa>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> updateTarefaImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws Exception {
    this.service.updateTarefaImage(id, image);
    return new ResponseEntity<Tarefa>(HttpStatus.OK);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  public ResponseEntity<Tarefa> deleteTarefa(@PathVariable Long id) throws Exception {
    this.service.deleteTarefa(id);
    return ResponseEntity.ok().build();
  }

}
