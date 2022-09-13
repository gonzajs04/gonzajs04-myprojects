


//SELECCIONAR CONTENIDO DEL HTML:

//querySelector: 

//headingTitulo.textContent = 'Cambie el heading'; //CAMBIO EL CONTENIDO DEL HEADING

//headingTitulo.classList = 'Agrego-clase' //Agrego una clase mas al heading



//querySelectorAll

//getElementById

//Validar FORMULARIO

const datos ={
    nombreForm:' ',
    telForm:'',
    mailForm:'',
    msgForm:''
}

const nombreForm = document.querySelector('#nombreForm');
nombreForm.addEventListener('input',leerInput);
const telForm  = document.querySelector('#telForm');

telForm.addEventListener('input',leerInput);
const mailForm = document.querySelector('#mailForm');

mailForm.addEventListener('input',leerInput);

const msgForm = document.querySelector('#msgForm');

msgForm.addEventListener('input',leerInput);

function leerInput(e){
    

    datos[e.target.id] = e.target.value;
    

}





const form = document.querySelector(".form-contacto");
form.addEventListener('submit',(e)=>{
    e.preventDefault();
    const{nombreForm,telForm,mailForm,msgForm}  = datos;
    
  
    if(nombreForm == '' || telForm == ''|| mailForm=='' || msgForm==''){
    
      

        mostrarAlerta('TODOS LOS CAMPOS SON OBLIGATORIOS','error');

    }
    else{

        mostrarAlerta('ENVIADO CORRECTAMENTE','correcto');
        
    }

})


function mostrarAlerta(alerta,estado){

    const encabezadoTexto = document.querySelector('.encabezado__texto');
   

    const alertah4 = document.createElement('H4');

    alertah4.textContent = alerta;
   

    if(estado=='error'){

        alertah4.classList.add('error');

    }
    else{
        alertah4.classList.add('correcto');

       
    }



    form.appendChild(alertah4);

    setTimeout(()=>{
        alertah4.remove();   
       },5000);


    




}

