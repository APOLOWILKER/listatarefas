package br.com.prova.listatarefas.domain.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;

import br.com.prova.listatarefas.domain.DTO.TarefaDTO;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tarefa")
@Table(name = "tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column
  private Long id;
  
  @NotBlank(message = "Descrição é obrigatorio")
  @Column
  private String description;

  @NotNull
  @Column
  private String status;

  @Lob
  @Column
  private byte[] image;

  public Tarefa(TarefaDTO tarefaDTO) {
    this.description = tarefaDTO.description();
    this.status = tarefaDTO.status();
    this.image = tarefaDTO.image();
  }

  public void atualizarTarefa(TarefaDTO tarefaDTO) {
    this.description = tarefaDTO.description();
    this.status = tarefaDTO.status();
    this.image = tarefaDTO.image();
  }

}
