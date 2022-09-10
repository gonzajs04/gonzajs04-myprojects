// function tarea(callback){
//     console.log('Mi primer tarea');

//     callback();
// }

// exports.tarea= tarea;

const{ src,dest, watch,parallel } = require("gulp");  //agarro todas las funciones de gulp

//IMAGENES WEBP

const webp = require('gulp-webp');

function versionWebp(done){

    const opciones = {
        quality : 50
    };

    src('img/**/*.{png,jpg}') //TODAS LAS IMÁGENES QUE ESTAN EN LA CARPETA, YA SEAN JPG O PNG
    .pipe(webp(opciones)) //CALIDAD DE 50, SE VE MUY BIEN
    .pipe(dest('build/img')); //LO GUARDO EN ESA CARPETA AL EJECUTARLO

    done();
}

//ALIGERAR IMAGENES

const imagemin = require('gulp-imagemin')//IMPORTO DEPENDENCIA INSTALADA
const cache = require('gulp-cache');
function aligerarImagen(done){
    const opciones = {
        optimizationLevel : 3 //LE PONGO NIVEL DE OPTIMIZACION
    }
    src('img/**/*.{png,jpg}')
    .pipe(cache( imagemin(opciones)) ) //LE PONGO NIVEL DE OPTIMIZACION
    .pipe(dest('build/img'));


    done()
}


//AVIF
const avif = require('gulp-avif');
function versionAvif(done){

    const opciones = {
        quality : 50
    }

    src('img/**/*.{png,jpg}')
    .pipe(avif(opciones))
    .pipe(dest('build/img'));

    done();

}

//CSS


const sass = require('gulp-sass')(require('sass')); //IMPORTAMOS EL ARCHIVO SASS

const autoprefixer = require('autoprefixer'); //Asegura de que funcione todo el CSS en todos los navegadores
const cssnano = require('cssnano'); //Comprime codigo de CSS
const postcss = require('gulp-postcss'); //Hace transformaciones de los otros 2.
const sourcemaps = require('gulp-sourcemaps');

function llamarCss(done){
    return src("src/scss/**/*.scss") //Identificar archivo SASS que creamos

    .pipe(sourcemaps.init()) //INICIALIZAMOS EL SOURCEMAPS

    .pipe( sass())     //COMPILARLO
    .pipe(postcss([autoprefixer(),cssnano()])) // Comprimimos archivos y lo adaptamos a todos los NAV

    .pipe(sourcemaps.write('.'))    //UTILIZAMOS EL SOURCEMAPS

    .pipe(dest('buildGulp/css'));      //Almacenarlo en el disco duro
    
    done(); //AVISA CUANDO LLEGA AL FINAL DE LA EJECUCION y se termina la compilacion de codigo
}
function dev(done){

    
    watch("src/scss/**/*.scss",llamarCss); //Le decimos que archivo queremos que le afecte el watch y llamamos a la funcion llamarCss() para que nos haga los cambios
    
     done();
  }


//JAVASCRIPT
const terser = require('gulp-terser-js');
function javascript(done){
    src('src/scss/js/**/*.js')
    .pipe(terser()) //EJECUTAMOS TERCER PARA MIMIFICAR JS
    .pipe(dest('build/js'))
    done();
}



 function dev2(done){

    watch("src/scss/js/**/*.js", javascript);
    done();
    
 }



exports.llamarCss = llamarCss;
exports.js = javascript;
exports.versionWebp = versionWebp; //convertir imagenes a webp y guardarlas en un lugar X
exports.versionAvif  =versionAvif;

exports.dev = parallel(aligerarImagen,versionWebp,versionAvif,javascript,dev2,dev); //Exporto el watch