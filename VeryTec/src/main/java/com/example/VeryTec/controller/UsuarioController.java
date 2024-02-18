package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Usuario;
import com.example.VeryTec.exception.UsuarioNotFoundException;
import com.example.VeryTec.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtiene un usuario determinado por el dni y el password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuariobydniandpassword/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByDniAndPassword(
            @RequestParam(value = "dni", defaultValue = "") String dni ,
            @RequestParam(value = "password", defaultValue = "") String password) throws NoSuchAlgorithmException {
        password = sha256(password);
        Set<Usuario> usuarios = null;
        usuarios = usuarioService.findByDniAndPassword(dni, password);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el dni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioDni/{dni}", produces = "application/json")
    public ResponseEntity<Usuario> getUsuarioByDNI(@PathVariable String dni) {
        Usuario usuario = usuarioService.findByDni(dni)
                .orElseThrow(() -> new UsuarioNotFoundException(dni));

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioRol/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByRol(@RequestParam(value = "rol", defaultValue = "") int rol) {
        Set<Usuario> usuarios = null;
            usuarios = usuarioService.findByRol(rol);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioNombre/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByNombre(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<Usuario> usuarios = null;
        if (nombre.equals(""))
            usuarios = usuarioService.findAll();
        else
            usuarios = usuarioService.findByNombre(nombre);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por los apellidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioApellidos/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByApellidos(@RequestParam(value = "apellidos", defaultValue = "") String apellidos) {
        Set<Usuario> usuarios = null;
        if (apellidos.equals(""))
            usuarios = usuarioService.findAll();
        else
            usuarios = usuarioService.findByApellidos(apellidos);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioEmail/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        Set<Usuario> usuarios = null;
        if (email.equals(""))
            usuarios = usuarioService.findAll();
        else
            usuarios = usuarioService.findByEmail(email);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por la dirección o el telefono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuariobydireccionortelefono/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByDireccionOrTelefono(@RequestParam Map<String,String> allParams) {
        Set<Usuario> usuarios = null;
        if (allParams.containsKey("direccion")){
            String direccion = allParams.get("direccion");
            if (direccion.equals(""))
                usuarios = usuarioService.findAll();
            else
                usuarios = usuarioService.findByDireccion(direccion);
        }else if (allParams.containsKey("telefono")) {
            String telefono = allParams.get("telefono");
            if (telefono==null) {
                usuarios = usuarioService.findAll();
            }
            else {
                usuarios = usuarioService.findByTelefono(Integer.parseInt(telefono));
            }
        }
        else {
            usuarios = usuarioService.findAll();
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el numero de identificación del colegio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuarioNumId/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByNumidentificacion(@RequestParam(value = "numidentificacion", defaultValue = "") String numidentificacion) {
        Set<Usuario> usuarios = null;
        if (numidentificacion.equals(""))
            usuarios = usuarioService.findAll();
        else
            usuarios = usuarioService.findByNumidentificacion(numidentificacion);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un usuario determinado por el nombre y los apellidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuariobynombreandapellidos/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByNombreAndApellidos(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "apellidos", defaultValue = "") String apellidos) {

        Set<Usuario> usuarios = null;
        usuarios = usuarioService.findByNombreAndApellidos(nombre, apellidos);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el nombre y el teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuariobynombreandtelefono/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByNombreAndTelefono(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "telefono", defaultValue = "") int telefono) {

        Set<Usuario> usuarios = null;
        usuarios = usuarioService.findByNombreAndTelefono(nombre, telefono);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado por el nombre y el rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/usuariobynombreandrol/", produces = "application/json")
    public ResponseEntity<Set<Usuario>> getUsuarioByNombreAndRol(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "rol", defaultValue = "") int rol) {

        Set<Usuario> usuarios = null;
        usuarios = usuarioService.findByNombreAndRol(nombre, rol);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el usuario", content = @Content(schema = @Schema(implementation = Usuario.class)))
    })
    @PostMapping(value = "/usuarioAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) throws NoSuchAlgorithmException {
        String noScript = usuario.getPassword();
        String password = sha256(noScript);
        usuario.setPassword(password);
        Usuario addedUsuario = usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(addedUsuario, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el usuario", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/usuarioDel/{dni}", produces = "application/json")
    public ResponseEntity<Response> deleteUsuario(@PathVariable String dni) {

        if(!dni.equals("superUsu01"))usuarioService.deleteUsuario(dni);



        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
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

    @Operation(summary = "Modifica un usuario con Sha256")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/usuarioUpdate/{dni}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> modifyUsuario(@PathVariable String dni, @RequestBody Usuario newUsuario) throws NoSuchAlgorithmException {
        String noScript = newUsuario.getPassword();
        String password = sha256(noScript);
        newUsuario.setPassword(password);
        Usuario usuario = usuarioService.modifyUsuario(dni, newUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/usuarioUpdateNormal/{dni}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> modifyUsuarioNormal(@PathVariable String dni, @RequestBody Usuario newUsuario) throws NoSuchAlgorithmException {
        Usuario usuario = usuarioService.modifyUsuario(dni, newUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(UsuarioNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
