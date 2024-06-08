package ar.edu.utn.dds.k3003.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
//
@Entity
@Getter
@Setter
@Table(name = "rutas")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "colaborador_Id")
    private Long colaboradorId;
    @Column(name = "heladera_Id_Origen")
    private Integer heladeraIdOrigen;
    @Column(name = "heladera_Id_Destino")
    private Integer heladeraIdDestino;

    @Transient
    private List<Traslado> traslados;

    public Ruta(Long colaboradorId, Integer heladeraIdOrigen, Integer heladeraIdDestino){
        //el constructor

        this.colaboradorId = colaboradorId;
        this.heladeraIdOrigen = heladeraIdOrigen;
        this.heladeraIdDestino = heladeraIdDestino;
    }

    public Ruta(){

    }


}
