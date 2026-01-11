function enviarDatos() {
    const id = document.getElementById("id").value.trim();
    const titulo = document.getElementById("titulo").value.trim();
    const genero = document.getElementById("genero").value.trim();
    const director = document.getElementById("director").value.trim();
    const anio = document.getElementById("anio").value.trim();

    if (!id || !titulo || !genero || !director || anio.length !== 4) {
        alert("Por favor, rellena todos los campos correctamente.");
        return;
    }

    const pelicula = {
        id: id,
        titulo: titulo,
        genero: genero,
        autor: director,
        anno: anio
    };

    fetch("http://localhost:8090/addpelicula", {
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
    fetch("http://localhost:8090/getpeliculas")
    .then(res => res.json())
    .then(data => {
        const tabla = data.map(p => `
            <tr>
                <td>${p.id}</td>
                <td>${p.titulo}</td>
                <td>${p.autor}</td>
                <td>${p.genero}</td>
                <td>${p.anno}</td>
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