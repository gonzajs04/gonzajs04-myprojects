document.addEventListener('DOMContentLoaded',()=>{

    iniciarApp();

});


function iniciarApp(){

    crearGaleria();

    scrollNav();
    navegacionFija();
}


//FUNCION CREAR GALERIA

function crearGaleria(){

   const galeria = document.querySelector('.grid-imagenes');

   for(let i = 1; i<=12 ; i++){
    const imagen = document.createElement('picture');

    imagen.innerHTML = ` <source srcset="build/img/thumb/${i}.avif" type="image/avif">
    <source srcset="build/img/thumb/${i}.webp" type="image/webp">
    <img src="build/img/thumb/${i}.jpg" alt="imagen vocalista">`;


//DARLE CLICK A LA IMAGEN, EJECUTA UNA FUNCION DE MOSTRAR IMAGEN PARA GRANDARLA
    imagen.onclick  = function (){
        mostrarImagen(i);
    }

    galeria.appendChild(imagen);

   }


}

//AGRANDAR IMAGEN AL DARLE CLICK
function mostrarImagen(id){

    const imagen = document.createElement('picture');

    imagen.innerHTML = `<source srcset="build/img/grande/${id}.avif" type="image/avif">
    <source srcset="build/img/grande/${id}.webp" type="image/webp">
    <img src="build/img/grande/${id}.jpg" alt="imagen/jpg">`;

    //CREAR DIV  con clase OVERLAY PARA FONDO OSCURO Y AÑADIRLE LAS IMAGENES

    const overlay = document.createElement('DIV');
    overlay.appendChild(imagen);
    overlay.classList.add('overlay');

    //AÑADIR OVERLAY CON IMAGENES AL BODY Y FIJAR PAGINA WEB AL ABRIR IMAGEN
    const body = document.querySelector('body');
    body.appendChild(overlay);
    body.classList.add('fijar-body');


    //CERRAR VENTANA MODAL O IMG GRANDE:
    //CREAMOS X PARA CERRAR Y SE LA AÑADIMOS AL OVERLAY
    const cerrarFoto = document.createElement('P');
    cerrarFoto.textContent = 'X';
    cerrarFoto.classList.add('btn-cerrar');
    overlay.appendChild(cerrarFoto);
//CUANDO TOCAMOS X CERRAMOS OVERLAY Y SACAMOS POSICION FIJA
    cerrarFoto.onclick = function(){
       overlay.remove();
       body.classList.remove('fijar-body');
    }
//CUANDO TOCAMOS el overlay, cerramosY SACAMOS POSICION FIJA
    overlay.onclick = function(){
        body.classList.remove('fijar-body');
        overlay.remove();
    }
  
   
}


//FUNCION DE SCROLLNAV

function scrollNav(){

    const enlaces = document.querySelectorAll('.navegacion a'); //SELECCIONO TODOS LOS ENLACES

  

    enlaces.forEach(enlace =>{ //RECORRO CADA UNO DE LOS ENLACES
        enlace.addEventListener('click',(e)=>{ //CUANDO LE DOY CLICK, PASA UN EVENTO
            e.preventDefault(); //EVITO QUE HAGA UN EVENTO DE VISITAR UN LINK

            const valorSeccion = e.target.attributes.href.value; //OBTENGO VALOR DE ID HREF: #galeria, #LINEUP
            const seccion = document.querySelector(valorSeccion); // SELECCIONO EL DIV O SECTION CON ID GAERIA, LINEUP
          
            seccion.scrollIntoView({behavior:"smooth"}); //LE PONGO SCROLL SUAVE PARA QUE VAYA DIRECTO A LA SECCION
           

        });


    });
    
}

//funcion SCROLL NAV FIJO


function navegacionFija(){

    const body = document.querySelector('body'); //Selecciono el BODY PARA DESPUES
    const header = document.querySelector('.header'); //SELECCIONO EL HEADER
    const festival = document.querySelector('.festival'); //SELECCIONO UN PUNTO DE PARTIDA PARA FIJAR LA BARRA
    //getBoundingClientRect() OBTENGO COORDENADAS DE UN CONTENEDOR O ELEMENTO
    window.addEventListener('scroll',()=>{ //LA BARRA SE VA A FIJAR CUANDO HAGAMOS SCROLL
        if(festival.getBoundingClientRect().top < 0){ //LLEGUÉ AL ELEMENTO EN EL CUAL QUIERO QUE APAREZCA LA NAV FIJA
            header.classList.add('fijo'); //FIJO LA BARRA
            body.classList.add('scroll-body'); //PARA ARREGLAR EL EMPUJE, TENEMOS QUE CREAR UNA CLASE PARA CUANDO VAYA A APARECER EL HEADER

        }else{
           header.classList.remove('fijo');// CUANDO NO PASE EL CONTENEDOR RAIZ DE LA NAV FIJA, SACAMOS LA CLASE
           body.classList.remove('scroll-body'); //ELIMINAMOS EL PADDING PARA CUANDO VOLVAMOS A ARRIBA
        }
    });
}
