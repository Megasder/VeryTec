package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Compra;
import com.example.VeryTec.exception.CompraNotFoundException;
import com.example.VeryTec.service.CompraService;
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

import java.sql.Time;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Compras", description = "Compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Operation(summary = "Obtiene una compra determinada por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/compraID/{idCompra}", produces = "application/json")
    public ResponseEntity<Compra> getCompraByIdCompra(@PathVariable long idCompra) {
        Compra compra = compraService.findByIdCompra(idCompra)
                .orElseThrow(() -> new CompraNotFoundException(idCompra));

        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una compra determinada por la fecha o la hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/comprabyfechaorhora/", produces = "application/json")
    public ResponseEntity<Set<Compra>> getCompraByFechaOrHora(@RequestParam Map<Date,Time> allParams) {
        Set<Compra> compras = null;
        if (allParams.containsKey("fecha")){
            Date fecha = allParams.get("fecha");
            if (fecha.equals(""))
                compras = compraService.findAll();
            else
                compras = compraService.findByFecha(fecha);
        }else if (allParams.containsKey("hora")) {
            String hora = String.valueOf(allParams.get("hora"));
            if (hora==null) {
                hora = String.valueOf(compraService.findAll());
            }
            else {
                compras = compraService.findByHora(Time.valueOf(hora));
            }
        }
        else {
            compras = compraService.findAll();
        }
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una compra determinada por la cantidad de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/compraCantidad/", produces = "application/json")
    public ResponseEntity<Set<Compra>> getCompraByCantidad(@RequestParam(value = "cantidad", defaultValue = "") int cantidad) {
        Set<Compra> compras = null;
        if (cantidad == 0)
            compras = compraService.findAll();
        else
            compras = compraService.findByCantidad(cantidad);

        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una compra determinada por la fecha y la hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/comprabyfechaandhora/", produces = "application/json")
    public ResponseEntity<Set<Compra>> getCompraByFechaAndHora(
            @RequestParam(value = "fecha", defaultValue = "") Date fecha ,
            @RequestParam(value = "hora", defaultValue = "") Time hora) {

        Set<Compra> compras = null;
        compras = compraService.findByFechaAndHora(fecha, hora);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una compra determinada por la cantidad de productos y la fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/comprabycantidadandfecha/", produces = "application/json")
    public ResponseEntity<Set<Compra>> getCompraByCantidadAndFecha(
            @RequestParam(value = "cantidad", defaultValue = "") int cantidad ,
            @RequestParam(value = "fecha", defaultValue = "") Date fecha) {

        Set<Compra> compras = null;
        compras = compraService.findByCantidadAndFecha(cantidad, fecha);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra la compra", content = @Content(schema = @Schema(implementation = Compra.class)))
    })
    @PostMapping(value = "/compraAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Compra> addCompra(@RequestBody Compra compra) {
        Compra addedCompra = compraService.addCompra(compra);
        return new ResponseEntity<>(addedCompra, HttpStatus.OK);
    }

    @Operation(summary = "Modifica una compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la compra", content = @Content(schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/compraMod/{idCompra}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Compra> modifyCompra(@PathVariable long idCompra, @RequestBody Compra newCompra) {
        Compra compra = compraService.modifyCompra(idCompra, newCompra);
        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la compra", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La compra no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/compraDel/{idCompra}", produces = "application/json")
    public ResponseEntity<Response> deleteCompra(@PathVariable long idCompra) {
        compraService.deleteCompra(idCompra);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/compraDniCliente/{dniCliente}", produces = "application/json")
    public ResponseEntity<Set<Compra>> getCompraByDniCliente(@PathVariable String dniCliente) {
        Set<Compra> compra = compraService.findByDniCliente(dniCliente);

        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @ExceptionHandler(CompraNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(CompraNotFoundException cnfe) {
        Response response = Response.errorResonse(NOT_FOUND, cnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
