const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  const apellido = document.getElementById("apellido").value;
  const nombre = document.getElementById("nombre").value;
  const cedula = document.getElementById("cedula").value;
  const sexo = document.getElementById("sexo").value;
  const fecha = document.getElementById("fecha").value;
  const calle = document.getElementById("calle").value;
  const localidad = document.getElementById("localidad").value;
  const provincia = document.getElementById("provincia").value;
  const telf = document.getElementById("telefono").value;

  // llamando al endpoint de agregar
  const datosFormulario = {
    nombre,
    apellido,
    cedula,
    sexo,
    fechaNacimiento: fecha,
    domicilio: {
      calle,
      localidad,
      provincia,
    },
    telefono: {
       telf,
    },
  };

  fetch(`persona/guardar`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datosFormulario),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Persona agregada con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando persona:", error);
    });
});
