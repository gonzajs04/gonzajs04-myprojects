
// const botonForm = document.querySelector('.enviarForm'); 

// botonForm.addEventListener('click', (e)=>{
//     e.preventDefault();
//     console.log('Enviando formulario');
// })

//Eventos con teclado INPUT

const datos = {
    nombre: '',
    mail: '',
    telefono: '',
    msg: ''
};

const inputMail = document.querySelector('#mail');

inputMail.addEventListener('input',leerInput);


const inputNombre = document.querySelector('#nombre');

inputNombre.addEventListener('input',leerInput);

const inputTel = document.querySelector('#telefono');

inputTel.addEventListener('input',leerInput);

const areaMsg  = document.querySelector('#msg');

areaMsg.addEventListener('input',leerInput);
    


 function leerInput(e){

   datos[e.target.id] = e.target.value;
   console.log(datos);

}


//Eventos con teclado SUBMIT
const form = document.querySelector('.form-contacto');

form.addEventListener('submit',(e)=>{
    e.preventDefault();
   
    //VALIDAR FORM

    const{nombre,mail,telefono,msg} = datos;
    if(nombre ==='' || mail=='' || telefono =='' || msg=='' ){
       
       mostrarAlerta('todos los campos son obligatorios','error');
        return; //Cortamos ejecucion para que no ejecute enviando form
    }else{
        mostrarAlerta('Se lo ha validado correctamente','correcto');
    }

 

    //ENVIAR FORMULARIO
});


//REFACTURYING
function mostrarAlerta(msg, estado){
    
     //creo H4 en html y lo guardo en  variable validacion
    const alerta = document.createElement('H4');
    //le guardo al h4 el contenido
    alerta.textContent = msg;

    if(estado == 'error'){

        alerta.classList.add('error'); //LE AGREGO CLASE al HTML LLAMADA ERROR O CORRECTO

    } else{
        alerta.classList.add('correcto');
       
    }
    form.appendChild(alerta); //Le pongo al form el el texto de correcto o error

    setTimeout(()=>{
     alerta.remove();   
    },5000);
}


function mostrarError(msg){
 
  
    const error = document.createElement('H4');
    error.textContent = msg;
    error.classList.add('error');
   form.appendChild(error);

   //desaparezca dsp de 3 segundos
   setTimeout(()=>{
    error.remove();
   },5000)
    
}

function mostrarAprobacion(msg){
 
     //creo H4 en html y lo guardo en  variable validacion
    const validacion = document.createElement('H4');
    //le agregamos el contenido del mensaje a la variable
    validacion.textContent = msg;
    //le agregamos una clase llamada validacion al H4
    validacion.classList.add('validacion');

    form.appendChild(validacion); //Le agrego al formulario el h4

    //se elimina la validacion
    setTimeout(()=>{
        validacion.remove();

    },5000);


}