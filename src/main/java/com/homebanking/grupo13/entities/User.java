package com.homebanking.grupo13.entities;

import com.homebanking.grupo13.entities.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // todo asigno un nombre al campo porque esta en camelcase
  @Column(name = "name_User")
  private String nameUser;
  private String email;
  private String password;
  private String dni;
  private String birthday;
  private String address;
  // todo Status lo utilizaremos en vez de borrar
  //  diremos que esta habilitada o deshabilitada
  //  esta comprobacion la tendremos que hacer antes de aplicar un metodo
  //  tambien estaba pensando hacer un boolean que es mas facil de programar
  //  creo que es mejor, pero diganme ustedes, que opinan???
  //  evaluamos status is true, then (por ejemplo listar/actualizar/tranferir),
  //  nose los metodos que se apliquen,
  //  recuerden que todas las entidades son de CRUD incompleto, no tienen delete

  private Status status;

  //todo fecha de creacion del usuario
  // hibernate genera la fecha automaticamente
  // cuando creamos un User
  @CreationTimestamp
  private LocalDateTime createAt;

  //todo fecha de modificacion del usuario
  // hibernate actualiza la fecha automaticamente
  // cuando actualizamos el User
  @UpdateTimestamp
  private LocalDateTime modifiedAt;

  // todo Lista de cuentas de cada usuario
  // todo relacionando
  // Todo @OneToMany

  // todo como estamos en la entidad user la relacion
  //  seria un user => muchas cuentas por lo que en éste caso seria
  //             @OneToMany

  // todo mapeamos con el mismo nombre que Account "owner"
  // todo y aplicamos la cascade, forma de actualizar, para éste caso es all
  @OneToMany(mappedBy = "owner" ,cascade = CascadeType.ALL, , orphanRemoval = true)
  // todo la relacion a muchos la representamos como una lista de Account
  //  asignamos el nombre accounts que utilizaremos para la relacion, y generamos
  //  un Arraylist para mostrar/editar/Borrar cuentas y users
  private List<Account> accounts = new ArrayList<>();


}
