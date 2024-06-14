package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Ruta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class RutaRepository {
    private static AtomicLong seqId = new AtomicLong();
    private Collection<Ruta> rutas;
    private EntityManager entityManager;

    public RutaRepository(EntityManager entityManager) {

        this.rutas = new ArrayList<>();
        this.entityManager = entityManager;
    }

    public Ruta save(Ruta ruta) {

        if (Objects.isNull(ruta.getId())) {
            entityManager.getTransaction().begin();
            entityManager.persist(ruta);
            entityManager.getTransaction().commit();
        }
        return ruta;
    }

    public Ruta findById(Long id) {

        Ruta ruta = entityManager.find(Ruta.class, id);
        if (ruta == null) {
            throw new NoSuchElementException(String.format("No hay una ruta de id: %s", id));
        }
        return ruta;
    }

    public List<Ruta> findByHeladeras(Integer heladeraOrigen, Integer heladeraDestino) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ruta> criteriaQuery = criteriaBuilder.createQuery(Ruta.class);
        Root<Ruta> root = criteriaQuery.from(Ruta.class);
        criteriaQuery.select(root)
                .where(
                        criteriaBuilder.equal(root.get("heladeraIdOrigen"), heladeraOrigen),
                        criteriaBuilder.equal(root.get("heladeraIdDestino"), heladeraDestino)
                );
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
