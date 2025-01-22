
// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#personaTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentCedula;
let currentDomicilioId;
let currentTelefonoId;

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
              <td>${String(persona.cedula)}</td>
              <td>${persona.apellido}</td>
              <td>${persona.nombre}</td>
              <td>${persona.sexo}</td>
              <td>${persona.fechaNacimiento}</td>
              <td>${persona.domicilio.localidad}</td>
              <td>${persona.telefono.telf}</td>
              <td>
                <button class="btn btn-primary btn-sm" onclick="editPersona(${String(persona.cedula)}, '${persona.apellido}','${persona.nombre}','${persona.sexo}',
                '${persona.fechaNacimiento}', '${persona.domicilio.id}', '${persona.domicilio.calle}', '${persona.domicilio.localidad}', '${persona.domicilio.provincia}','${persona.telefono.id}', '${persona.telefono.telf}')">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deletePersona(${persona.cedula})">Eliminar</button>
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
  cedula,
  apellido,
  nombre,
  sexo,
  fechaNacimiento,
  idDomicilio,
  calle,
  localidad,
  provincia,
  idTelefono,
  telf,
) {
  currentCedula = String(cedula);
  currentDomicilioId = idDomicilio;
  currentTelefonoId = idTelefono
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editNombre").value = nombre;
  document.getElementById("editCedula").value = String(cedula);
  document.getElementById("editSexo").value = sexo;
  document.getElementById("editFecha").value = fechaNacimiento;
  document.getElementById("editCalle").value = calle;
  document.getElementById("editLocalidad").value = localidad;
  document.getElementById("editProvincia").value = provincia;
  document.getElementById("editTelefono").value = telf;
  editModal.show();
};

// Función para editar una persona
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const apellido = document.getElementById("editApellido").value;
  const nombre = document.getElementById("editNombre").value;
  const sexo = document.getElementById("editSexo").value;
  const fecha = document.getElementById("editFecha").value;
  const calle = document.getElementById("editCalle").value;
  const localidad = document.getElementById("editLocalidad").value;
  const provincia = document.getElementById("editProvincia").value;
  const telf = document.getElementById("editTelefono").value;

  //modificar una persona
  fetch(`persona/modificar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      cedula: String(currentCedula),
      nombre,
      apellido,
      sexo,
      fechaNacimiento: fecha,
      domicilio: {
        id: currentDomicilioId,
        calle,
        localidad,
        provincia,
      },
      telefono: {
        id: currentTelefonoId,
        telf,
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
deletePersona = function (cedula) {
  if (confirm("¿Esta seguro de que desea eliminar esta persona?")) {
    // eliminar la persona
    fetch(`persona/eliminar/${String(cedula)}`, {
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
