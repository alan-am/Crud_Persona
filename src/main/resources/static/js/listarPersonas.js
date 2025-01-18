
// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#personaTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentDni;
let currentDomicilioId;

// Función para obtener y mostrarlos
function fetchPersonas() {
  // listar las personas
  fetch(`persona/buscartodos`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((persona, index) => {
        const row = document.createElement("tr");
        //CAMBIAR
        row.innerHTML = `
              <td>${persona.dni}</td>
              <td>${persona.apellido}</td>
              <td>${persona.nombre}</td>
              <td>${persona.fechaIngreso}</td>
              <td>${persona.domicilio.localidad}</td>
              <td>
                <button class="btn btn-primary btn-sm" onclick="editPersona(${persona.dni}, '${persona.apellido}','${persona.nombre}',
                '${persona.fechaIngreso}', '${persona.domicilio.id}', '${persona.domicilio.calle}', '${persona.domicilio.localidad}', '${persona.domicilio.provincia}')">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deletePersona(${persona.dni})">Eliminar</button>
              </td>
            `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

// Función para abrir el modal y cargar los datos de la persona
editPersona = function (
  dni,
  apellido,
  nombre,
  fechaIngreso,
  idDomicilio,
  calle,
  localidad,
  provincia
) {
  currentDni = dni;
  currentDomicilioId = idDomicilio;
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editNombre").value = nombre;
  document.getElementById("editDni").value = dni;
  document.getElementById("editFecha").value = fechaIngreso;
  document.getElementById("editCalle").value = calle;
  //document.getElementById("editNumero").value = numero;
  document.getElementById("editLocalidad").value = localidad;
  document.getElementById("editProvincia").value = provincia;
  editModal.show();
};

// Función para editar una persona
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const apellido = document.getElementById("editApellido").value;
  const nombre = document.getElementById("editNombre").value;
  const fecha = document.getElementById("editFecha").value;
  const calle = document.getElementById("editCalle").value;
  //const numero = document.getElementById("editNumero").value;
  const localidad = document.getElementById("editLocalidad").value;
  const provincia = document.getElementById("editProvincia").value;

  //modificar una persona
  fetch(`persona/modificar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      dni: currentDni,
      nombre,
      apellido,
      fechaIngreso: fecha,
      domicilio: {
        id: currentDomicilioId,
        calle,
        localidad,
        provincia,
      },
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Persona modificada con éxito");
      fetchPersonas();
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error editando persona:", error);
    });
});

// Función para eliminar una persona
deletePersona = function (dni) {
  if (confirm("¿Esta seguro de que desea eliminar esta persona?")) {
    // eliminar la persona
    fetch(`persona/eliminar/${dni}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("Persona eliminada con éxito");
        fetchPersonas();
      })
      .catch((error) => {
        console.error("Error borrando persona:", error);
      });
  }
};

// Llamar a la función para obtener y mostrarlos
fetchPersonas();
