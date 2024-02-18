package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Servicio;
import com.example.VeryTec.exception.ServicioNotFoundException;
import com.example.VeryTec.service.ServicioService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Servicio", description = "Servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Operation(summary = "Obtiene un producto determinado por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el servicio", content = @Content(schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "404", description = "El servicio no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/servicioID/{idServicio}", produces = "application/json")
    public ResponseEntity<Servicio> getServicioByIdServicio(@PathVariable long idServicio) {
        Servicio servicios = servicioService.findByIdServicio(idServicio)
                .orElseThrow(() -> new ServicioNotFoundException(idServicio));

        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un servicio determinado por el precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el servicio", content = @Content(schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "404", description = "El servicio no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/servicioPrecio/", produces = "application/json")
    public ResponseEntity<Set<Servicio>> getServicioByPrecio(@RequestParam(value = "precio", defaultValue = "") float precio) {
        Set<Servicio> servicios = null;
        if (precio == 0)
            servicios = servicioService.findAll();
        else
            servicios = servicioService.findByPrecio(precio);

        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }


    @Operation(summary = "Registra un nuevo Servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el servicio", content = @Content(schema = @Schema(implementation = Servicio.class)))
    })
    @PostMapping(value = "/servicioAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Servicio> addServicio(@RequestBody Servicio servicio) {
        Servicio addedServicio = servicioService.addServicio(servicio);
        return new ResponseEntity<>(addedServicio, HttpStatus.OK);
    }


    @Operation(summary = "Modifica un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el servicio", content = @Content(schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "404", description = "El servicio no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/servicioMod/{idServicio}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Servicio> modifyServicio(@PathVariable long idServicio, @RequestBody Servicio newServicio) {
        Servicio servicio = servicioService.modifyServicio(idServicio, newServicio);
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el servicio", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El servicio no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/servicioDel/{idServicio}", produces = "application/json")
    public ResponseEntity<Response> deleteServicio(@PathVariable long idServicio) {
        servicioService.deleteServicio(idServicio);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ServicioNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ServicioNotFoundException enfe) {
        Response response = Response.errorResonse(NOT_FOUND, enfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
