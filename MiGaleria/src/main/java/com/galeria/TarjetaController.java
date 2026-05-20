package com.galeria;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// @RestController indica que esta clase es un controlador REST (devuelve JSON automáticamente)
// @CrossOrigin permite que el frontend pueda llamar a la API (evita errores CORS)
@RestController
@RequestMapping("/api/tarjetas")
@CrossOrigin(origins = "*")
public class TarjetaController {

    // Lista en memoria que hace de "base de datos" (se reinicia al parar el servidor)
    private List<Tarjeta> tarjetas = new ArrayList<>();
    private int contadorId = 1;

    // Constructor: cargamos unas tarjetas de ejemplo al arrancar
    public TarjetaController() {
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 1", "Descripción de la tarjeta número 1"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 2", "Descripción de la tarjeta número 2"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 3", "Descripción de la tarjeta número 3"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 4", "Descripción de la tarjeta número 4"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 5", "Descripción de la tarjeta número 5"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 6", "Descripción de la tarjeta número 6"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 7", "Descripción de la tarjeta número 7"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 8", "Descripción de la tarjeta número 8"));
        tarjetas.add(new Tarjeta(contadorId++, "Tarjeta 9", "Descripción de la tarjeta número 9"));
    }

    // GET /api/tarjetas → devuelve todas las tarjetas
    @GetMapping
    public List<Tarjeta> getTodas() {
        return tarjetas;
    }

    // POST /api/tarjetas → añade una tarjeta nueva (recibe JSON en el body)
    @PostMapping
    public Tarjeta crear(@RequestBody Tarjeta nueva) {
        nueva.setId(contadorId++);
        tarjetas.add(nueva);
        return nueva;
    }

    // DELETE /api/tarjetas/{id} → elimina la tarjeta con ese id
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        tarjetas.removeIf(t -> t.getId() == id);
        return "Tarjeta eliminada";
    }
}
