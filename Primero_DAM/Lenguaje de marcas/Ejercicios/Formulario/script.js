function validar() {
  var todocorrecto = true;
  var expresionRegular =
    /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+.([a-zA-Z])+([a-zA-Z])+$/;
  if (document.getElementById("nombre").value.length < 2) {
    todocorrecto = false;
    alert("El campo nombre es obligatorio");
  }
  if (document.getElementById("apellidos").value.length < 2) {
    todocorrecto = false;
    alert("El campo apellidos es obligatorio");
  }

  if (document.getElementById("telefono").value.length != 9) {
    todocorrecto = t;
    alert("El campo telfono es obligatorio");
  }

  if (!expresionRegular.getElementById("email").value) {
    todocorrecto = false;
    alert("El campo email es obligatorio");
  }

  if(!document.getElementById("acepto").Checked){
    todocorrecto = false;
    alert("Debe aceptar las condiciones");
  }

  if (condition) {
    
  }

  if ((todocorrescto = true)) {
    todocorresto = false;
    alert("Datos introucidos correctamente");
  } else {
    alert("Datos introducidos incorrectos");
  }
}
