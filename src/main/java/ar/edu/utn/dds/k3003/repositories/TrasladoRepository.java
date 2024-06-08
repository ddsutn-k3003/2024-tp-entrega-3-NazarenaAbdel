package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Ruta;
import ar.edu.utn.dds.k3003.model.Traslado;

import javax.persistence.EntityManager;
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
       /* if (Objects.isNull(traslado.getId())) {
            traslado.setId(seqId.getAndIncrement());
            this.traslados.add(traslado);
        }
        return traslado;

        */
        if (Objects.isNull(traslado.getId())) {
            entityManager.getTransaction().begin();
            entityManager.persist(traslado);
            entityManager.getTransaction().commit();
        }
        return traslado;
    }

    public Traslado findById(Long id) {
       /* Optional<Traslado> first = this.traslados.stream().filter(x -> x.getId().equals(id)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay un traslado de id: %s", id)
        ));

        */

        Traslado traslado = entityManager.find(Traslado.class, id);
        if (traslado == null) {
            throw new NoSuchElementException(String.format("No hay una ruta de id: %s", id));
        }
        return traslado;
    }

    public List<Traslado> findAll() {
        return this.traslados.stream().collect(Collectors.toList());
    }
//
}
