document.addEventListener('DOMContentLoaded', ()=>{
    iniciarApp();
});




function iniciarApp(){
   

    navegacionSmooth();

}


function navegacionSmooth(){
    const enlaces = document.querySelectorAll('nav .smooth'); //LE CREO UNA CLASE A LOS ARCHIVOS HTML QUE SOLO TENGAN EL SMOOTH
  
    enlaces.forEach(enlace =>{

        enlace.addEventListener('click',(e)=>{
            e.preventDefault();
            const valorSeccion = e.target.attributes.href.value;
           const seccion = document.querySelector(valorSeccion);

           console.log(seccion);
         
            seccion.scrollIntoView({behavior: 'smooth'});
        })
    })



}


/* const name = document.querySelector(".container-tecnologias");

const btnMenu = document.querySelector("#btnmenu");
const contMenu = document.querySelector("#contMenu");

btnMenu.addEventListener("click", () => {
    contMenu.style.transition = 'all .5s ease'
    contMenu.style.transform = 'translateX(0%)';
    
    
})
 */


