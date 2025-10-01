function enviarDatos() {
  const id_pelicula = document.getElementById("id_pelicula").value.trim();
  const titulo = document.getElementById("titulo").value.trim();
  const genero = document.getElementById("genero").value.trim();
  const director = document.getElementById("director").value.trim();
  const año = document.getElementById("año").value.trim();

  if (!id_pelicula || !titulo || !genero || !director || año.length !== 4) {
      alert("Por favor, rellena todos los campos correctamente.");
      return;
  }

  const pelicula = {
      id_pelicula: id_pelicula,
      titulo: titulo,
      genero: genero,
      autor: director,
      año: año
  };

  fetch("http://localhost:8080/addpelicula", {
      method: "POST",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify(pelicula)
  })
  .then(res => res.json())
  .then(res => {
      const mensaje = document.getElementById("mensaje");
      if (res === 0) {
          mensaje.innerText = "Película añadida exitosamente.";
          cargarPeliculas();
      } else {
          mensaje.innerText = "Ya existe una película con ese ID.";
      }
  });
}


function cargarPeliculas() {
  fetch("http://localhost:8080/getpeliculas")
  .then(res => res.json())
  .then(data => {
      const tabla = data.map(p => `
          <tr>
              <td>${p.id_pelicula}</td>
              <td>${p.titulo}</td>
              <td>${p.autor}</td>
              <td>${p.genero}</td>
              <td>${p.año}</td>
          </tr>
      `).join("");

      document.getElementById("tablaPeliculas").innerHTML = `
          <table>
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>Título</th>
                      <th>Director</th>
                      <th>Género</th>
                      <th>Año</th>
                  </tr>
              </thead>
              <tbody>${tabla}</tbody>
          </table>`;
    });
}