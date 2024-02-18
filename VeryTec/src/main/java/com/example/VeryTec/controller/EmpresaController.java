package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Empresa;
import com.example.VeryTec.exception.EmpresaNotFoundException;
import com.example.VeryTec.service.EmpresaService;
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

import java.util.Map;
import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Empresas", description = "Empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Obtiene una empresa determinada por el cif")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresaCif/{cif}", produces = "application/json")
    public ResponseEntity<Empresa> getEmpresaByCif(@PathVariable String cif) {
        Empresa empresa = empresaService.findByCif(cif)
                .orElseThrow(() -> new EmpresaNotFoundException(cif));

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por el nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresaNombre/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByNombre(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<Empresa> empresas = null;
        if (nombre.equals(""))
            empresas = empresaService.findAll();
        else
            empresas = empresaService.findByNombre(nombre);

        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por la dirección o el teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresabydireccionortelefono/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByDireccionOrTelefono(@RequestParam Map<String,String> allParams) {
        Set<Empresa> empresas = null;
        if (allParams.containsKey("direccion")){
            String direccion = allParams.get("direccion");
            if (direccion.equals(""))
                empresas = empresaService.findAll();
            else
                empresas = empresaService.findByDireccion(direccion);
        }else if (allParams.containsKey("telefono")) {
            String telefono = allParams.get("telefono");
            if (telefono==null) {
                empresas = empresaService.findAll();
            }
            else {
                empresas = empresaService.findByTelefono(Integer.parseInt(telefono));
            }
        }
        else {
            empresas = empresaService.findAll();
        }
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por la web o el email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresabyweboremail/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByWebOrEmail(@RequestParam Map<String,String> allParams) {
        Set<Empresa> empresas = null;
        if (allParams.containsKey("web")){
            String web = allParams.get("web");
            if (web.equals(""))
                empresas = empresaService.findAll();
            else
                empresas = empresaService.findByWeb(web);
        }else if (allParams.containsKey("email")) {
            String email = allParams.get("email");
            if (email==null) {
                empresas = empresaService.findAll();
            }
            else {
                empresas = empresaService.findByEmail(email);
            }
        }
        else {
            empresas = empresaService.findAll();
        }
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por la actividad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresaActividad/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByActividad(@RequestParam(value = "actividad", defaultValue = "") String actividad) {
        Set<Empresa> empresas = null;
        if (actividad.equals(""))
            empresas = empresaService.findAll();
        else
            empresas = empresaService.findByActividad(actividad);

        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por el nombre y el teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresabynombreandtelefono/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByNombreAndTelefono(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "telefono", defaultValue = "") int telefono) {

        Set<Empresa> empresas = null;
        empresas = empresaService.findByNombreAndTelefono(nombre, telefono);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por el nombre y el email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresabynombreandemail/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByNombreAndEmail(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "email", defaultValue = "") String email) {

        Set<Empresa> empresas = null;
        empresas = empresaService.findByNombreAndEmail(nombre, email);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una empresa determinada por el nombre y la dirección")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/empresabynombreanddireccion/", produces = "application/json")
    public ResponseEntity<Set<Empresa>> getEmpresaByNombreAndDireccion(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "direccion", defaultValue = "") String direccion) {

        Set<Empresa> empresas = null;
        empresas = empresaService.findByNombreAndDireccion(nombre, direccion);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra la empresa", content = @Content(schema = @Schema(implementation = Empresa.class)))
    })
    @PostMapping(value = "/empresaAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Empresa> addEmpresa(@RequestBody Empresa empresa) {
        Empresa addedEmpresa = empresaService.addEmpresa(empresa);
        return new ResponseEntity<>(addedEmpresa, HttpStatus.OK);
    }

    @Operation(summary = "Modifica una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la empresa", content = @Content(schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/empresaMod/{cif}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Empresa> modifyEmpresa(@PathVariable String cif, @RequestBody Empresa newEmpresa) {
        Empresa empresa = empresaService.modifyEmpresa(cif, newEmpresa);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la empresa", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La empresa no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/empresaDel/{cif}", produces = "application/json")
    public ResponseEntity<Response> deleteEmpresa(@PathVariable String cif) {
        empresaService.deleteEmpresa(cif);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(EmpresaNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(EmpresaNotFoundException enfe) {
        Response response = Response.errorResonse(NOT_FOUND, enfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
