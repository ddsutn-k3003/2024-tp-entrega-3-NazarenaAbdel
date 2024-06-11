package ar.edi.itn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.model.Ruta;
import ar.edu.utn.dds.k3003.model.Traslado;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPersistencia {

    static EntityManagerFactory entityManagerFactory ;
    EntityManager entityManager ;

    @BeforeAll
    public static void setUpClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
    }
    @BeforeEach
    public void setup() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
    }
    @Test
    public void testConectar() {
       // vacío, para ver que levante el ORM
    }


    @Test
    public void testGuardarYRecuperarDoc() throws Exception {
        Ruta ruta = new Ruta(15L, 0, 1);
        Traslado traslado = new Traslado("er4567jhngk",ruta, EstadoTrasladoEnum.CREADO, LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(ruta);
        entityManager.persist(traslado);
        entityManager.getTransaction().commit();
        entityManager.close();// Cierra el EntityManager después de cada transacción

        // Abre un nuevo EntityManager para recuperar la entidad
        entityManager = entityManagerFactory.createEntityManager(); //el entity manager factory se usa para crear entity managers
        Ruta rutaPersistida = entityManager.find(Ruta.class,1L);
        Traslado trasladoPersistido = entityManager.find(Traslado.class, 1L);

        assertEquals(ruta.getId(), rutaPersistida.getId());
        assertEquals(traslado.getId(), trasladoPersistido.getId());
    }




}
