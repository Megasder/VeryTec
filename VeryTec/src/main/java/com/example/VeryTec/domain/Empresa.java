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
@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {

    @Schema(description = "Cif de la empresa, tambien usado como primary key", example = "A29268166", required = true)
    @Id
    @Column(name="cif")
    private String cif;

    @Schema(description = "Nombre de la empresa", example = "FisioterApp S.A.")
    @Column(name="nombre")
    private String nombre;

    @Schema(description = "Web de la empresa", example = "tufisio.com")
    @Column(name="web")
    private String web;

    @Schema(description = "Telefono de la empresa", example = "661661661")
    @Column(name="telefono")
    private int telefono;

    @Schema(description = "Email de la empresa", example = "tufisio@gmail.com")
    @Column(name="email")
    private String email;

    @Schema(description = "Direccion de la empresa", example = "C/Sevilla 3")
    @Column(name="direccion")
    private String direccion;

    @Schema(description = "Actividad de la empresa", example = "Fisioterapia y osteopatia")
    @Column(name="actividad")
    private String actividad;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.DETACH, mappedBy="empresas")
    private List<Usuario> usuarios;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.DETACH, mappedBy="empresas")
    private List<Cliente> clientes;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Producto> productos= new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Servicio> servicios= new ArrayList<>();
}
