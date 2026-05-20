const API = "/api/tarjetas";

let todasLasTarjetas = [];

document.addEventListener("DOMContentLoaded", cargarTarjetas);

function cargarTarjetas() {
    fetch(API)
        .then(res => res.json())
        .then(data => {
            todasLasTarjetas = data;
            mostrarJSON(todasLasTarjetas);
        });
}

function mostrarJSON(data) {
    let galeria = document.getElementById("galeria");
    if (data.length === 0) {
        galeria.innerHTML = "<p>No se encontró ninguna tarjeta.</p>";
    } else {
        galeria.innerHTML = "<pre>" + JSON.stringify(data, null, 2) + "</pre>";
    }
}

function buscar() {
    let texto = document.getElementById("buscador").value.toLowerCase();
    let resultado = todasLasTarjetas.filter(t =>
        t.titulo.toLowerCase().includes(texto)
    );
    mostrarJSON(resultado);
}

function crearTarjeta() {
    let titulo = document.getElementById("tituloNueva").value.trim();
    let texto  = document.getElementById("textoNueva").value.trim();

    if (!titulo || !texto) {
        alert("Rellena el título y la descripción.");
        return;
    }

    fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ titulo: titulo, texto: texto })
    })
    .then(res => res.json())
    .then(tarjetaCreada => {
        todasLasTarjetas.push(tarjetaCreada);
        mostrarJSON(todasLasTarjetas);
        document.getElementById("tituloNueva").value = "";
        document.getElementById("textoNueva").value  = "";
    });
}

function borrarTarjeta() {
    let id = document.getElementById("idBorrar").value;

    if (!id) {
        alert("Introduce el ID de la tarjeta a borrar.");
        return;
    }

    fetch(API + "/" + id, { method: "DELETE" })
        .then(() => {
            todasLasTarjetas = todasLasTarjetas.filter(t => t.id != id);
            mostrarJSON(todasLasTarjetas);
            document.getElementById("idBorrar").value = "";
        });
}

function cambiarTema(tema) {
    const c = document.querySelector(".contenedor");
    const h = document.querySelector(".header");
    const f = document.querySelector(".footer");
    const ventana = document.getElementById("ventanaFlotanteConfiguracionTema");

    ventana.style.display = "none";

    if (tema === "oscuro") {
        c.style.background = "black";
        h.style.background = "#222";
        c.style.color = "white";
        f.style.background = "#222";
        f.style.color = "white";
    } else if (tema === "claro") {
        c.style.background = "white";
        h.style.background = "white";
        c.style.color = "black";
        f.style.background = "white";
        f.style.color = "black";
    } else if (tema === "personalizado") {
        ventana.style.display = "flex";
    }
}

function aplicarTemaPersonalizado() {
    let colorHeader = document.getElementById("entradaColorHeader").value;
    let colorMain   = document.getElementById("entradaColorMain").value;
    let colorFooter = document.getElementById("entradaColorFooter").value;

    document.querySelector(".header").style.background     = colorHeader;
    document.querySelector(".contenedor").style.background = colorMain;
    document.querySelector(".footer").style.background     = colorFooter;
}