const API_URL = "http://localhost:8090/rest_peliculas";

async function cargarPeliculas() {
  const res = await fetch(`${API_URL}/get_peliculas`);
  const peliculas = await res.json();

  const container = document.getElementById("peliculas-container");
  container.innerHTML = "";

  peliculas.forEach(p => {
    const div = document.createElement("div");
    div.className = "pelicula";
    div.textContent = `ID: ${p.id_pelicula}, Título: ${p.titulo}, Director: ${p.director}`;
    container.appendChild(div);
  });
}

document.getElementById("pelicula-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const id = parseInt(document.getElementById("id_pelicula").value);
  const titulo = document.getElementById("titulo").value;
  const director = document.getElementById("director").value;

  const nuevaPelicula = { id_pelicula: id, titulo, director };

  const res = await fetch(`${API_URL}/add_pelicula`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(nuevaPelicula),
  });

  const result = await res.text();

  if (result == "0") {
    alert("Película añadida correctamente.");
    cargarPeliculas();
  } else {
    alert("Ya existe una película con ese ID.");
  }
});

window.onload = cargarPeliculas;
