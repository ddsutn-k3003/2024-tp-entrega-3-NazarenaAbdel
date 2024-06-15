package ar.edi.itn.dds.k3003.model;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.FachadaLogistica;
import ar.edu.utn.dds.k3003.facades.dtos.ColaboradorDTO;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.facades.dtos.TrasladoDTO;
import ar.edu.utn.dds.k3003.model.Ruta;
import ar.edu.utn.dds.k3003.model.Traslado;
import ar.edu.utn.dds.k3003.repositories.TrasladoMapper;
import ar.edu.utn.dds.k3003.repositories.TrasladoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class testPersistencia {

    static EntityManagerFactory entityManagerFactory ;
    EntityManager entityManager ;
    static private TrasladoRepository trasladoRepository;
    static private FachadaLogistica fachadaLogistica;
    static private TrasladoMapper trasladoMapper;

    @BeforeAll
    public static void setUpClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("entrega3_tp_dds");


    }
    @BeforeEach
    public void setup() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        trasladoRepository = new TrasladoRepository(entityManager);
        fachadaLogistica = new Fachada();
        trasladoMapper = new TrasladoMapper();
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

        // Crea un colaborador ficticio y un mes y año de prueba
        Long colaboradorId = 15L;
        Integer mes = 6;
        Integer anio = 2024;

        // Ejecuta el método trasladosDeColaborador que deseas probar
        List<TrasladoDTO> trasladosDto = fachadaLogistica.trasladosDeColaborador(colaboradorId, mes, anio);

        // Verifica que la lista de DTOs no esté vacía
        assertFalse(trasladosDto.isEmpty());

        assertEquals(ruta.getId(), rutaPersistida.getId());
        assertEquals(traslado.getId(), trasladoPersistido.getId());
    }




}
