package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.model.Ruta;
import ar.edu.utn.dds.k3003.model.Traslado;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TrasladoRepository {
    private static AtomicLong seqId = new AtomicLong();
    private Collection<Traslado> traslados;
    private EntityManager entityManager ;

    public TrasladoRepository(EntityManager entityManager){

        this.traslados = new ArrayList<>();
        this.entityManager = entityManager;
    }

    public Traslado save(Traslado traslado) {
        if (Objects.isNull(traslado.getId())) {
            entityManager.getTransaction().begin();
            entityManager.persist(traslado);
            entityManager.getTransaction().commit();
        }
        return traslado;
    }

    public Traslado findById(Long id) {

        Traslado traslado = entityManager.find(Traslado.class, id);
        if (traslado == null) {
            throw new NoSuchElementException(String.format("No hay una ruta de id: %s", id));
        }
        return traslado;
    }

    public List<Traslado> findByColaboradorId(Long id, Integer mes, Integer anio) {

        List<Traslado> trasladosDelColaborador = this.traslados.stream()
                .filter(t -> t.getRuta().getColaboradorId().equals(id))
                .filter(x -> x.getFechaTraslado().getMonthValue() == mes)
                .filter(x -> x.getFechaTraslado().getYear() == anio)
                .collect(Collectors.toList());


        return trasladosDelColaborador;
    }

    public Traslado modificarEstado(Long id, EstadoTrasladoEnum estadoNuevo) {

        Traslado traslado = findById(id);
        traslado.setEstado(estadoNuevo);

        return traslado;
    }



    public List<Traslado> findAll() {
        return this.traslados.stream().collect(Collectors.toList());
    }
//
}
