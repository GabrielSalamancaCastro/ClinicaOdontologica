
// METODO POST 
let registrarOdontologo = document.querySelector('#registro-odontologo');

registrarOdontologo.addEventListener("click", function(event){
    event.preventDefault(); 

let nombreOdntologo = document.querySelector('#nombre').value;
let apellidoOdontologo = document.querySelector('#apellido').value;
let matriculaOdontologo = document.querySelector('#matricula').value;



let dataPost={
   nombre: nombreOdntologo,
   apellido: apellidoOdontologo,
   matricula: matriculaOdontologo
}

//console.log(dataPost);



    fetch('http://localhost:8080/odontologos/create',{
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

  fetch('http://localhost:8080/odontologos')
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
            <td>${element.matricula}</td>         
            </tr>
            `

        }); 
    })
    .catch(function(err){
        console.error(err);
    })

})


//POR DOCUMENTO
let buscarOdontologoBtn = document.querySelector('#buscar-matricula'); 

buscarOdontologoBtn.addEventListener("click", function(event){
  event.preventDefault();
  let matriculaOdontologo = document.querySelector('#buscarmatricula').value;
  fetch(`http://localhost:8080/odontologos/matricula/${matriculaOdontologo}`)
  .then(function(response){
    return response.json();
})
.then(function(odontologo){
    console.log(odontologo);  
        tablaPacientes.innerHTML=`
        <tr>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>DOCUMENTO</th>
            <th>ACCIONES</th>
          </tr>
        <tr>
        <td>${odontologo.nombre}</td>
        <td>${odontologo.apellido}</td>
        <td>${odontologo.matricula}</td>
        <td>
        <button id="eliminar-odontologo">ELIMINAR</button>
        <button id="actualizar-odontologo">ACTUALIZAR</button>
        <button id="turno-odontologo">TURNOS</button>
        </td>
        </tr>
        ` 

        //ELIMINAR
        let eliminarOdontologoBtn = document.querySelector('#eliminar-odontologo'); 

        eliminarOdontologoBtn.addEventListener("click", function(event){
          event.preventDefault();

          let confirmacionDelete=confirm("¿ESTAS SEGURO QUE QUIERES ELIMINAR ESTE ODONTOLOGO DEL SISTEMA?")
          if(confirmacionDelete){
          console.log("CLICKE EN ACEPTAR");
          fetch(`http://localhost:8080/odontologos/delete/${odontologo.id}`,{
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
          let updateOdontologoBtn = document.querySelector('#actualizar-odontologo');

          updateOdontologoBtn.addEventListener("click", function(event){
            event.preventDefault();
            let myModal = document.querySelector('.modal');
            myModal.style.display = "block";
            let updateModal = document.querySelector('#updateOdontologoModal');
            updateModal.addEventListener("click", function(event){
              event.preventDefault();
                let nombreOdontologoModal = document.querySelector('#nombreModal').value;
                let apellidoOdontologoeModal = document.querySelector('#apellidoModal').value;
                let documentoOdontologoModal = document.querySelector('#matriculaModal').value;
            

                let dataUpdate= {
                  id: odontologo.id,
                  nombre: nombreOdontologoModal,
                  apellido: apellidoOdontologoeModal,
                  matricula: documentoOdontologoModal
                }

                fetch('http://localhost:8080/odontologos/update',{
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


        
   
})
.catch(function(err){
    console.error(err);
})



})


