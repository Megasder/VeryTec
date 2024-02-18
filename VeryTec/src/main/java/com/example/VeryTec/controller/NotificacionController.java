package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Notificacion;
import com.example.VeryTec.exception.NotificacionNotFoundException;
import com.example.VeryTec.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Notificaciones", description = "Notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Obtiene el listado de notificaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de notificaciones",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Notificacion.class)))),
    })
    @GetMapping(value = "/notificacionAsunto/", produces = "application/json")
    public ResponseEntity<Set<Notificacion>> getNotificacionByAsunto(@RequestParam(value = "asunto", defaultValue = "") String asunto) {
        Set<Notificacion> notificaciones = null;
        if (asunto.equals(""))
            notificaciones = notificacionService.findAll();
        else
            notificaciones = notificacionService.findByAsunto(asunto);

        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una notificación determinada por la fecha y si lo ha leído o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la notificación", content = @Content(schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "La notificación no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/notificacionbyfechaandleido/", produces = "application/json")
    public ResponseEntity<Set<Notificacion>> getNotificacionByFechaAndLeido(
            @RequestParam(value = "fecha", defaultValue = "") String fecha ,
            @RequestParam(value = "leido", defaultValue = "") boolean leido) {

        Set<Notificacion> notificaciones = null;
        notificaciones = notificacionService.findByFechaAndLeido(fecha, leido);
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra la notificación", content = @Content(schema = @Schema(implementation = Notificacion.class)))
    })
    @PostMapping(value = "/notificacionAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Notificacion> addNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion addedNotificacion = notificacionService.addNotificacion(notificacion);
        return new ResponseEntity<>(addedNotificacion, HttpStatus.OK);
    }

    @Operation(summary = "Modifica una notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la notificación", content = @Content(schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "La notificación no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/notificacionMod/{idNotificacion}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Notificacion> modifyNotificacion(@PathVariable long idNotificacion, @RequestBody Notificacion newNotificacion) {
        Notificacion notificacion = notificacionService.modifyNotificacion(idNotificacion, newNotificacion);
        return new ResponseEntity<>(notificacion, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la notificación", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La notificación no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/notificacionDel/{idNotificacion}", produces = "application/json")
    public ResponseEntity<Response> deleteNotificacion(@PathVariable long idNotificacion) {
        notificacionService.deleteNotificacion(idNotificacion);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(NotificacionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(NotificacionNotFoundException nnfe) {
        Response response = Response.errorResonse(NOT_FOUND, nnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
