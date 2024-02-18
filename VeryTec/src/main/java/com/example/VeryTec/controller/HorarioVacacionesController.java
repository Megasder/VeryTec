package com.example.VeryTec.controller;

import com.example.VeryTec.domain.HorarioVacaciones;
import com.example.VeryTec.exception.HorarioVacacionesNotFoundException;
import com.example.VeryTec.service.HorarioVacacionesService;
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
@Tag(name = "Horariovacaciones", description = "Horariovacaciones")
public class HorarioVacacionesController {

    @Autowired
    private HorarioVacacionesService horariovacacionesService;

    @Operation(summary = "Obtiene el listado de horarios de vacaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de horarios de vacaciones",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HorarioVacaciones.class)))),
    })
    @GetMapping(value = "/listadoHorariosVacaciones/", produces = "application/json")
    public ResponseEntity<Set<HorarioVacaciones>> getHorariosVacacionesByFecha(@RequestParam(value = "fecha", defaultValue = "") String fecha) {
        Set<HorarioVacaciones> horariosVacaciones = null;
        if (fecha.equals(""))
            horariosVacaciones = horariovacacionesService.findAll();
        else
            horariosVacaciones = horariovacacionesService.findByFecha(fecha);

        return new ResponseEntity<>(horariosVacaciones, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene el horario de vacaciones determinado por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el horario de vacaciones", content = @Content(schema = @Schema(implementation = HorarioVacaciones.class))),
            @ApiResponse(responseCode = "404", description = "El horario de vacaciones no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/horariovacacionesID/{idHorarioVacaciones}", produces = "application/json")
    public ResponseEntity<HorarioVacaciones> getHorarioVacacionesByID(@PathVariable long idHorarioVacaciones) {
        HorarioVacaciones horariovacaciones = horariovacacionesService.findByIdHorarioVacaciones(idHorarioVacaciones)
                .orElseThrow(() -> new HorarioVacacionesNotFoundException(idHorarioVacaciones));

        return new ResponseEntity<>(horariovacaciones, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un horario de vacaciones determinado por la fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el horario de vacaciones", content = @Content(schema = @Schema(implementation = HorarioVacaciones.class))),
            @ApiResponse(responseCode = "404", description = "El horario de vacaciones no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/horariovacacionesFecha/", produces = "application/json")
    public ResponseEntity<Set<HorarioVacaciones>> getHorarioVacacionesByFecha(@RequestParam(value = "fecha", defaultValue = "") String fecha) {
        Set<HorarioVacaciones> horariovacaciones = null;
        horariovacaciones = horariovacacionesService.findByFecha(fecha);

        return new ResponseEntity<>(horariovacaciones, HttpStatus.OK);
    }


    @Operation(summary = "Registra un nuevo horario de vacaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el horario de vacaciones", content = @Content(schema = @Schema(implementation = HorarioVacaciones.class)))
    })
    @PostMapping(value = "/horariovacacionesAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<HorarioVacaciones> addHorarioVacaciones(@RequestBody HorarioVacaciones horariovacaciones) {
        HorarioVacaciones addedHorarioVacaciones = horariovacacionesService.addHorariovacaciones(horariovacaciones);
        return new ResponseEntity<>(addedHorarioVacaciones, HttpStatus.OK);
    }



    @Operation(summary = "Elimina un horario de vacaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el horario de vacaciones", content = @Content(schema = @Schema(implementation = HorarioVacaciones.class))),
            @ApiResponse(responseCode = "404", description = "El horario de vacaciones no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/horariovacacionesDel/{idHorarioVacaciones}", produces = "application/json")
    public ResponseEntity<Response> deleteHorarioVacaciones(@PathVariable long idHorarioVacaciones) {
        horariovacacionesService.deleteHorariovacaciones(idHorarioVacaciones);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }



    @Operation(summary = "Modifica un horario de vacaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el horario de vacaciones", content = @Content(schema = @Schema(implementation = HorarioVacaciones.class))),
            @ApiResponse(responseCode = "404", description = "El horario de vacaciones no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/horariovacacionesMod/{idHorarioVacaciones}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<HorarioVacaciones> modifyHorarioVacaciones(@PathVariable long idHorarioVacaciones, @RequestBody HorarioVacaciones newHorarioVacaciones) {
        HorarioVacaciones horariovacaciones  = horariovacacionesService.modifyHorariovacaciones(idHorarioVacaciones, newHorarioVacaciones);
        return new ResponseEntity<>(horariovacaciones, HttpStatus.OK);
    }



    @ExceptionHandler(HorarioVacacionesNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(HorarioVacacionesNotFoundException hvnfe) {
        Response response = Response.errorResonse(NOT_FOUND, hvnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
