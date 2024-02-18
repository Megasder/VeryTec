package com.example.VeryTec.controller;

import com.example.VeryTec.domain.Producto;
import com.example.VeryTec.exception.ProductoNotFoundException;
import com.example.VeryTec.service.ProductoService;
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

import java.util.Map;
import java.util.Set;

import static com.example.VeryTec.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Producto", description = "Producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtiene el listado de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Producto.class)))),
    })
    @GetMapping(value = "/productoCategoria/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByCategoria(@RequestParam(value = "categoria", defaultValue = "") String categoria) {
        Set<Producto> productos = null;
        if (categoria.equals(""))
            productos = productoService.findAll();
        else
            productos = productoService.findByCategoria(categoria);

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productoID/{idProducto}", produces = "application/json")
    public ResponseEntity<Producto> getProductoByIdProducto(@PathVariable long idProducto) {
        Producto productos = productoService.findByIdProducto(idProducto)
                .orElseThrow(() -> new ProductoNotFoundException(idProducto));

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado por el nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productoNombre/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByNombre(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<Producto> productos = null;
        if (nombre.equals(""))
            productos = productoService.findAll();
        else
            productos = productoService.findByNombre(nombre);

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado por la categoría o el precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productobycategoriaorprecio/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByCategoriaOrPrecio(@RequestParam Map<String,String> allParams) {
        Set<Producto> productos = null;
        if (allParams.containsKey("categoria")){
            String categoria = allParams.get("categoria");
            if (categoria.equals(""))
                productos = productoService.findAll();
            else
                productos = productoService.findByCategoria(categoria);
        }else if (allParams.containsKey("precio")) {
            String precio = allParams.get("precio");
            if (precio==null) {
                productos = productoService.findAll();
            }
            else {
                productos = productoService.findByPrecio(Float.parseFloat(precio));
            }
        }
        else {
            productos = productoService.findAll();
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado por el descuento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productoDescuento/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByDescuento(@RequestParam(value = "descuento", defaultValue = "") int descuento) {
        Set<Producto> productos = null;
        if (descuento == 0)
            productos = productoService.findAll();
        else
            productos = productoService.findByDescuento(descuento);

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un producto determinado por el nombre y el precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productobynombreandprecio/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByNombreAndPrecio(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "precio", defaultValue = "") float precio) {

        Set<Producto> productos = null;
        productos = productoService.findByNombreAndPrecio(nombre, precio);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado por el nombre y la categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "el producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/productobynombreandcategoria/", produces = "application/json")
    public ResponseEntity<Set<Producto>> getProductoByNombreAndCategoria(
            @RequestParam(value = "nombre", defaultValue = "") String nombre ,
            @RequestParam(value = "categoria", defaultValue = "") String categoria) {

        Set<Producto> productos = null;
        productos = productoService.findByNombreAndCategoria(nombre, categoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el producto", content = @Content(schema = @Schema(implementation = Producto.class)))
    })
    @PostMapping(value = "/productoAdd/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
        Producto addedProducto = productoService.addProducto(producto);
        return new ResponseEntity<>(addedProducto, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el producto", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/productoMod/{idProducto}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Producto> modifyProducto(@PathVariable long idProducto, @RequestBody Producto newProducto) {
        Producto producto = productoService.modifyProducto(idProducto, newProducto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el producto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/productoDel/{idProducto}", produces = "application/json")
    public ResponseEntity<Response> deleteProducto(@PathVariable long idProducto) {
        productoService.deleteProducto(idProducto);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ProductoNotFoundException enfe) {
        Response response = Response.errorResonse(NOT_FOUND, enfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
