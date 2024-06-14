package ar.edu.utn.dds.k3003.Controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.facades.dtos.TrasladoDTO;
import ar.edu.utn.dds.k3003.facades.exceptions.TrasladoNoAsignableException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
//
public class TrasladoController {
    private Fachada fachada;
    public TrasladoController(Fachada fachada) {
        this.fachada = fachada;
    }

    public void asignar(Context context) {
        try {
            var trasladoDTO = this.fachada.asignarTraslado(context.bodyAsClass(TrasladoDTO.class));
            context.json(trasladoDTO);
        } catch (TrasladoNoAsignableException | NoSuchElementException e) {
            context.result(e.getLocalizedMessage());
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

    public void obtener(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        try {
            var trasladoDTO = this.fachada.buscarXId(id);
            context.json(trasladoDTO);
        } catch (NoSuchElementException ex) {
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }

    public void ObtenerTrasladosColaborador(Context context){
        var id = context.queryParamAsClass("id", Long.class).get();
        var anio = context.queryParamAsClass("anio", Integer.class).get();
        var mes = context.queryParamAsClass("mes", Integer.class).get();
        try {
            var trasladoDTO = this.fachada.trasladosDeColaborador(id,mes,anio);
            context.json(trasladoDTO);
        } catch (NoSuchElementException ex) {
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }

    }

    public void modificarEstado(Context context) {
        var idTraslado = context.pathParamAsClass("id", Long.class).get();

        try {
            TrasladoDTO trasladoDTO = context.bodyAsClass(TrasladoDTO.class);
            trasladoDTO.setId(idTraslado);
            String nuevoEstado = trasladoDTO.getStatus().toString();

            if (nuevoEstado == "ENTREGADO") {
                this.fachada.trasladoDepositado(idTraslado);
            }
            if (nuevoEstado == "EN_VIAJE") {
                this.fachada.trasladoRetirado(idTraslado);
            }
        }
        catch(NoSuchElementException ex) {
                context.result(ex.getLocalizedMessage());
                context.status(HttpStatus.NOT_FOUND);

            }
    }
}
