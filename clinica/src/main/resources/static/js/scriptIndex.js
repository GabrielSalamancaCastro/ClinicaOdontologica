
// METODO POST 
let registrarPaciente = document.querySelector('#registro-pacientes');

registrarPaciente.addEventListener("click", function(event){
    event.preventDefault(); 

let nombrePaciente = document.querySelector('#nombre').value;
let apellidoPaciente = document.querySelector('#apellido').value;
let documentoPaciente = document.querySelector('#numeroDocumento').value;
let fechaIngresoPaciente = document.querySelector('#ingreso').value;
let callePaciente = document.querySelector('#calle').value;
let numeroCallePaciente = document.querySelector('#numeroCalle').value;
let barrioPaciente = document.querySelector('#barrio').value;
let ciudadPaciente = document.querySelector('#ciudad').value;



let dataPost={
    nombre: nombrePaciente,
    apellido: apellidoPaciente,
    dni: documentoPaciente,
    fechaIngreso: fechaIngresoPaciente,
    direccion: {
      calle: callePaciente,
      numero: numeroCallePaciente,
      barrio: barrioPaciente,
      ciudad: ciudadPaciente
    }
}

//console.log(dataPost);



    fetch('http://localhost:8080/pacientes/create',{
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dataPost),
    })
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log('Success:', data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
      
})

//METODOS GET

//ALL

let tablaPacientes = document.querySelector('#tabla-pacientes')

window.addEventListener("load", function(event){
  event.preventDefault();

  fetch('http://localhost:8080/pacientes')
    .then(function(response){
        return response.json();
    })
    .then(function(personas){
        console.log(personas);
        personas.forEach((element) => {
            tablaPacientes.innerHTML+=`
        
            <tr>
            <td>${element.id}</td>
            <td>${element.nombre}</td>
            <td>${element.apellido}</td>
            <td>${element.dni}</td>         
            </tr>
            `

        }); 
    })
    .catch(function(err){
        console.error(err);
    })

})


//POR DOCUMENTO
let buscarPacienteBtn = document.querySelector('#buscar-documento'); 

buscarPacienteBtn.addEventListener("click", function(event){
  event.preventDefault();
  let documentoPaciente = document.querySelector('#buscarDocumento').value;
  fetch(`http://localhost:8080/pacientes/documento/${documentoPaciente}`)
  .then(function(response){
    return response.json();
})
.then(function(paciente){
    console.log(paciente);  
        tablaPacientes.innerHTML=`
        <tr>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>DOCUMENTO</th>
            <th>ACCIONES</th>
          </tr>
        <tr>
        <td>${paciente.nombre}</td>
        <td>${paciente.apellido}</td>
        <td>${paciente.dni}</td>
        <td>
        <button id="eliminar-pacientes">ELIMINAR</button>
        <button id="actualizar-pacientes">ACTUALIZAR</button>
        <button id="turno-pacientes">TURNO</button>
        </td>
        </tr>
        ` 

        //ELIMINAR
        let eliminarPacienteBtn = document.querySelector('#eliminar-pacientes'); 

        eliminarPacienteBtn.addEventListener("click", function(event){
          event.preventDefault();

          let confirmacionDelete=confirm("¿ESTAS SEGURO QUE QUIERES ELIMINAR ESTE PACIENTE DEL SISTEMA?")
          if(confirmacionDelete){
          console.log("CLICKE EN ACEPTAR");
          fetch(`http://localhost:8080/pacientes/delete/${paciente.id}`,{
              method: 'DELETE',
          })
          .then(function(response){
              return response.json();
          }).then(function(data){
              console.log('Success',data);
          }).catch(function(err){
              console.error(err);
          })
          }
          });

          //UPDATE
          let updatePacienteBtn = document.querySelector('#actualizar-pacientes');

          updatePacienteBtn.addEventListener("click", function(event){
            event.preventDefault();
            let myModal = document.querySelector('.modal');
            myModal.style.display = "block";
            let updateModal = document.querySelector('#updatePacienteModal');
            updateModal.addEventListener("click", function(event){
              event.preventDefault();
                let nombrePacienteModal = document.querySelector('#nombreModal').value;
                let apellidoPacienteModal = document.querySelector('#apellidoModal').value;
                let documentoPacienteModal = document.querySelector('#numeroDocumentoModal').value;
                let fechaIngresoPacienteModal = document.querySelector('#ingresoModal').value;
                let callePacienteModal = document.querySelector('#calleModal').value;
                let numeroCallePacienteModal = document.querySelector('#numeroCalleModal').value;
                let barrioPacienteModal = document.querySelector('#barrioModal').value;
                let ciudadPacienteModal = document.querySelector('#ciudadModal').value;

                let dataUpdate= {
                  id: paciente.id,
                  nombre: nombrePacienteModal,
                  apellido: apellidoPacienteModal,
                  dni: documentoPacienteModal,
                  fechaIngreso: fechaIngresoPacienteModal,
                  direccion: {
                    calle: callePacienteModal,
                    numero: numeroCallePacienteModal,
                    barrio: barrioPacienteModal,
                    ciudad: ciudadPacienteModal
                }
                }

                fetch('http://localhost:8080/pacientes/update',{
                                method: 'PUT',
                                headers: {
                                    'Content-Type': 'application/json',
                                  },
                                  body: JSON.stringify(dataUpdate),
                                })
                                .then(function(response){
                                  return response.json()
                              })
                              .then(function(respuesta){
                                  console.log('Success:', respuesta);
                                })
                                .catch((error) => {
                                  console.error('Error:', error);
                                });
                
            })


          })

          //TURNOS
          let turnoPacienteBtn = document.querySelector('#turno-pacientes');

          turnoPacienteBtn.addEventListener("click", function(event){
            event.preventDefault();
            let myModal2 = document.querySelector('.modal2');
            myModal2.style.display = "block";
            let odontologosDisponibles = document.querySelector('#odontologos-disponibles');
            

            //COMPLETANDO EL SELECT
            fetch('http://localhost:8080/odontologos')
            .then(function(response){
                return response.json();
            })
            .then(function(odontologos){
                //console.log(odontologos);
                odontologos.forEach((element) => {
                    odontologosDisponibles.innerHTML+=`
                    <option value="${element.id}">${element.nombre} ${element.apellido} </option>
                    `
                    fetch(`http://localhost:8080/odontologos/${odontologosDisponibles.value}`)
                    .then(function(response){
                      return response.json();
                  })
                  .then(function(odontologoTurno){
                    
                    let reservarTurno = document.querySelector('#reservar-turno');
                    reservarTurno.addEventListener("click", function(event){
                      event.preventDefault();
                      let fechaHoraTurno =document.querySelector('#fechaHoraTurno').value;
                      
                      let dataTurno = {
                        odontologo: odontologoTurno,
                        paciente: paciente,
                        fechaHora: fechaHoraTurno
                      }

                      console.log(dataTurno);
                      fetch('http://localhost:8080/turnos/create',{
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                          },
                          body: JSON.stringify(dataTurno),
                        })
                        .then(function(response){
                            return response.json()
                        })
                        .then(function(data){
                            console.log('Success:', data);
                          })
                          .catch((error) => {
                            console.error('Error:', error);
                          });

                    })

                  })

                    
                }); 
            })
            .catch(function(err){
                console.error(err);
            })

            
        
        })

          })

    
   
})
.catch(function(err){
    console.error(err);
})













