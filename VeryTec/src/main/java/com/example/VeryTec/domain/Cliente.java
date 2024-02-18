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
@Table(name = "cliente")
public class Cliente {

    @Schema(description = "DNI del cliente", example = "20512958H", required = true)
    @Column(name = "dni")
    @Id
    private String dni;

    @Schema(description = "Nombre del cliente", example = "Manuel")
    @Column(name = "nombre")
    private String nombre;

    @Schema(description = "Apellidos del cliente", example = "Moreno García")
    @Column(name = "apellidos")
    private String apellidos;

    @Schema(description = "Telefono del cliente", example = "661661661")
    @Column(name = "telefono")
    private int telefono;

    @Schema(description = "Email del cliente", example = "mmorenog@gmail.com")
    @Column(name = "email")
    private String email;

    @Schema(description = "Password del cliente", example = "manumg1980")
    @Column(name = "password")
    private String password;

    @Schema(description = "Localizacion del cliente", example = "Lumbalgia")
    @Column(name = "historial")
    private String historial;

    @Schema(description = "Dinero del cliente", example = "1000")
    @Column(name = "dinero")
    private float dinero;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name="empresacliente",
            joinColumns = {@JoinColumn(name="cif")},
            inverseJoinColumns = {@JoinColumn(name="dni")}
    )
    private List<Empresa> empresas;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Compra> compras= new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas= new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

