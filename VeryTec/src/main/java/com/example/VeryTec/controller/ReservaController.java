package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Reserva;
import com.example.VeryTec.exception.ReservaNotFoundException;
import com.example.VeryTec.service.ReservaService;
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

import java.sql.Time;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Reservas", description = "Reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @Operation(summary = "Obtiene una reserva por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reservas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Reserva.class)))),
    })
    @GetMapping(value = "/reservaID/{idReserva}", produces = "application/json")
    public ResponseEntity<Reserva> getReservaByIdReserva(@PathVariable long idReserva) {
        Reserva reserva = reservaService.findByIdReserva(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));

        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una reserva determinada por la fecha o la hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la reserva", content = @Content(schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "La reserva no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/reservabyfechaorhora/", produces = "application/json")
    public ResponseEntity<Set<Reserva>> getReservaByFechaOrHora(@RequestParam Map<Date, Time> allParams) {
        Set<Reserva> reservas = null;
        if (allParams.containsKey("fecha")){
            Date fecha = allParams.get("fecha");
            if (fecha.equals(""))
                reservas = reservaService.findAll();
            else
                reservas = reservaService.findByFecha(fecha);
        }else if (allParams.containsKey("hora")) {
            String hora = String.valueOf(allParams.get("hora"));
            if (hora==null) {
                hora = String.valueOf(reservaService.findAll());
            }
            else {
                reservas = reservaService.findByHora(Time.valueOf(hora));
            }
        }
        else {
            reservas = reservaService.findAll();
        }
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una reserva determinada por la fecha y si el empleado la ha confirmado o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la reserva", content = @Content(schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "La reserva no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/reservabyfechaandconfirmacion/", produces = "application/json")
    public ResponseEntity<Set<Reserva>> getReservaByFechaAndConfirmacion(
            @RequestParam(value = "fecha", defaultValue = "") Date fecha ,
            @RequestParam(value = "confirmacion", defaultValue = "") boolean confirmacion) {

        Set<Reserva> reservas = null;
        reservas = reservaService.findByFechaAndConfirmacion(fecha, confirmacion);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una reserva determinada por la fecha y la hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la reserva", content = @Content(schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "La reserva no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/reservabyfechaandhora/", produces = "application/json")
    public ResponseEntity<Set<Reserva>> getReservaByFechaAndHora(
            @RequestParam(value = "fecha", defaultValue = "") Date fecha ,
            @RequestParam(value = "hora", defaultValue = "") Time hora) {

        Set<Reserva> reservas = null;
        reservas = reservaService.findByFechaAndHora(fecha, hora);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra la reserva", content = @Content(schema = @Schema(implementation = Reserva.class)))
    })
    @PostMapping(value = "/reservaAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Reserva> addReserva(@RequestBody Reserva reserva) {
        //if(reserva.getFecha() == reserva.getUsuario().getHorarioVacaciones())
        Reserva addedReserva = reservaService.addReserva(reserva);
        return new ResponseEntity<>(addedReserva, HttpStatus.OK);
    }

    @Operation(summary = "Modifica una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la reserva", content = @Content(schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "La reserva no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/reservaMod/{idReserva}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Reserva> modifyReserva(@PathVariable long idReserva, @RequestBody Reserva newReserva) {
        Reserva reserva = reservaService.modifyReserva(idReserva, newReserva);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la reserva", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La reserva no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/reservaDel/{idReserva}", produces = "application/json")
    public ResponseEntity<Response> deleteReserva(@PathVariable long idReserva) {
        reservaService.deleteReserva(idReserva);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ReservaNotFoundException rnfe) {
        Response response = Response.errorResonse(NOT_FOUND, rnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}