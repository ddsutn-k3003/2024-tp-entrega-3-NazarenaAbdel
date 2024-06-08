package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.model.Ruta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
//
@Entity
@Getter
@Setter
@Table(name = "traslados")
public class Traslado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qr_vianda")
    private String qrVianda;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoTrasladoEnum estado;
    @Column(name = "fecha_traslado")
    private LocalDateTime fechaTraslado;
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;


    public Traslado(String qr, Ruta ruta, EstadoTrasladoEnum estado, LocalDateTime fecha){
            this.qrVianda = qr;
            this.estado = estado;
            this.fechaTraslado = fecha;
            this.ruta = ruta;
    }

    public Traslado(){

    }


}
