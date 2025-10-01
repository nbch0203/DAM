add_alumno = () => {
  console.log("add_pelicula");
  var id_alumno = document.getElementById("id_pelicula").value;
  var nombre = document.getElementById("titulo").value;
  var nombre = document.getElementById("genero").value;
  var asignatura = document.getElementById("autor").value;
  var nota = document.getElementById("año").value;

  fetch("http://localhost:8090/rest_peliculas/add_pelicula", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      "id_pelicula": 100,
      "titulo": "Juan",
      "genero": "lenguaje marcas",
      "autor": 8.5,
      "año": 1995,
    }),
  })
  .then((response) => {
    console.log(response);
  });
};
