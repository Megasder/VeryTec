package com.example.VeryTec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="usuario")
public class Usuario {

    @Schema(description = "DNI del usuario, usado tambien como primary key", example = "74155212C", required = true)
    @Column(name="dni")
    @Id
    private String dni;

    @Schema(description = "Contraseña del usuario", example = "contraseña1")
    @Column(name="password")
    private String password;

    @Schema(description = "Rol del usuario", example = "1")
    @Column(name="rol")
    private int rol;

    @Schema(description = "Descripcion del usuario", example = "Especializado en osteopatia")
    @Column(name="descripcion")
    private String descripcion;

    @Schema(description = "Nombre del usuario", example = "Juan")
    @Column(name="nombre")
    private String nombre;

    @Schema(description = "Apellidos del usuario", example = "Gutierrez Perea")
    @Column(name="apellidos")
    private String apellidos;

    @Schema(description = "Telefono del usuario", example = "655415214")
    @Column(name="telefono")
    private int telefono;

    @Schema(description = "Email del usuario", example = "tucorreo@gmail.com")
    @Column(name="email")
    private String email;

    @Schema(description = "Direccion del usuario", example = "C/ Lavanda 2")
    @Column(name="direccion")
    private String direccion;

    @Schema(description = "Numero de Id del usuario", example = "332514")
    @Column(name="numidentificacion")
    private String numidentificacion;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name="empresausuario",
            joinColumns = {@JoinColumn(name="cif")},
            inverseJoinColumns = {@JoinColumn(name="dni")}
    )
    private List<Empresa> empresas;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reserva> reservas= new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<HorarioVacaciones> compHorarioVacacionesras= new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
