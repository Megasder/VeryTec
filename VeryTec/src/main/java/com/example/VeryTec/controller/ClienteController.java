package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Cliente;
import com.example.VeryTec.exception.ClienteNotFoundException;
import com.example.VeryTec.service.ClienteService;
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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Clientes", description = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtiene un cliente determinado por el dni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clienteDni/{dni}", produces = "application/json")
    public ResponseEntity<Cliente> getClienteByDNI(@PathVariable String dni) {
        Cliente cliente = clienteService.findByDni(dni)
                .orElseThrow(() -> new ClienteNotFoundException(dni));

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente determinado por el nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clienteNombre/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByNombre(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<Cliente> clientes = null;
        if (nombre.equals(""))
            clientes = clienteService.findAll();
        else
            clientes = clienteService.findByNombre(nombre);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente determinado por el email o el telefono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clientebyemailortelefono/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByEmailOrTelefono(@RequestParam Map<String,String> allParams) {
        Set<Cliente> clientes = null;
        if (allParams.containsKey("email")){
            String email = allParams.get("email");
            if (email.equals(""))
                clientes = clienteService.findAll();
            else
                clientes = clienteService.findByEmail(email);
        }else if (allParams.containsKey("telefono")) {
            String telefono = allParams.get("telefono");
            if (telefono==null) {
                clientes = clienteService.findAll();
            }
            else {
                clientes = clienteService.findByTelefono(Integer.parseInt(telefono));
            }
        }
        else {
            clientes = clienteService.findAll();
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente determinado por el historial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clienteHistorial/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByHistorial(@RequestParam(value = "historial", defaultValue = "") String historial) {
        Set<Cliente> clientes = null;
        if (historial.equals(""))
            clientes = clienteService.findAll();
        else
            clientes = clienteService.findByHistorial(historial);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(value = "/clienteDinero/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByDinero(@RequestParam(value = "dinero", defaultValue = "") float dinero) {
        Set<Cliente> clientes = null;
        clientes = clienteService.findByDinero(dinero);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente determinado por el nombre y los apellidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clientebynombreandapellidos/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByNombreAndApellidos(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "apellidos", defaultValue = "") String apellidos) {

        Set<Cliente> clientes = null;
        clientes = clienteService.findByNombreAndApellidos(nombre, apellidos);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente determinado por el nombre y el telefono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/clientebynombreandtelefono/", produces = "application/json")
    public ResponseEntity<Set<Cliente>> getClienteByNombreAndTelefono(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "telefono", defaultValue = "") int telefono) {

        Set<Cliente> clientes = null;
        clientes = clienteService.findByNombreAndTelefono(nombre, telefono);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el cliente", content = @Content(schema = @Schema(implementation = Cliente.class)))
    })
    @PostMapping(value = "/clienteAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) throws NoSuchAlgorithmException {
        String noScript = cliente.getPassword();
        String password = sha256(noScript);
        cliente.setPassword(password);
        Cliente addedCliente = clienteService.addCliente(cliente);
        return new ResponseEntity<>(addedCliente, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un cliente con Sha256")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/clienteMod/{dni}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cliente> modifyCliente(@PathVariable String dni, @RequestBody Cliente newCliente) throws NoSuchAlgorithmException {
        String noScript = newCliente.getPassword();
        String password = sha256(noScript);
        newCliente.setPassword(password);
        Cliente cliente = clienteService.modifyCliente(dni, newCliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el cliente", content = @Content(schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/clienteModNormal/{dni}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cliente> modifyClienteNormal(@PathVariable String dni, @RequestBody Cliente newCliente) throws NoSuchAlgorithmException {
        Cliente cliente = clienteService.modifyCliente(dni, newCliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte [] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return convert(hash);
    }

    public static String convert(byte[] arrayBytes){
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i <arrayBytes.length; i++) {
            stringbuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringbuffer.toString();
    }

    @Operation(summary = "Elimina un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el cliente", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/clienteDel/{dni}", produces = "application/json")
    public ResponseEntity<Response> deleteCliente(@PathVariable String dni) {
        clienteService.deleteCliente(dni);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ClienteNotFoundException enfe) {
        Response response = Response.errorResonse(NOT_FOUND, enfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
