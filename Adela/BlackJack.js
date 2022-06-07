var puntaje = 0;
var puntajeBanca = "";
var numCartas = 0;
var apuesta = "0";
var cartaInBanca = "&&rango=";
function Pedir() {
    let sxmlhttp;
    let DNI = localStorage.getItem("dni");
    sxmlhttp = new XMLHttpRequest();

    sxmlhttp.onreadystatechange = function () {
        if (sxmlhttp.readyState == 4 && sxmlhttp.status == 200) {
            let respuesta = sxmlhttp.responseText;
            let carta = "";
            let puntos = "";
            let i = 0;
            for (i; i < respuesta.length; i++) {
                if (respuesta.charAt(i) == "$") {
                    i++
                    for (i; i < respuesta.length; i++) {
                        puntos += respuesta.charAt(i);
                    }
                }
                else {
                    carta += respuesta.charAt(i);
                }
            }
            puntaje = parseInt(puntos);
            document.getElementById("mano").removeChild(document.getElementById("puntos"));
            let x = document.createElement("li");
            let cartaX = document.createTextNode(carta);
            x.appendChild(cartaX);
            document.getElementById("mano").appendChild(x);
            let D = document.createElement("li");
            D.setAttribute("id", "puntos");
            let puntosJ = document.createTextNode("puntos " + puntaje);
            D.appendChild(puntosJ);
            document.getElementById("mano").appendChild(D);
            numCartas++;
        }

        if (puntaje > 20) {
            Plantarse();
        }
    }
    sxmlhttp.open("POST", "http://127.0.0.1:8080/ProyectoCasino/Pedir", true);
    sxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    sxmlhttp.send("puntos=" + puntaje);
}

function Jugar() {
    if ((localStorage.getItem("dni")==null) && (localStorage.getItem("dni")) == "") {
        alert("Debes iniciar sesión");
    }
    else {
        let sxmlhttp;
        sxmlhttp = new XMLHttpRequest();

        sxmlhttp.onreadystatechange = function () {
            if (sxmlhttp.readyState == 4 && sxmlhttp.status == 200) {
                let respuesta = sxmlhttp.responseText;
                if (respuesta == "No") {
                    alert("Saldo insuficiente");
                }
                else {
                    let carta1 = "";
                    let carta2 = "";
                    let puntos = "";
                    let cartaBanca = "";
                    let i = 0;
                    for (i; i < respuesta.length; i++) {
                        if (respuesta.charAt(i) == "&") {
                            i++
                            for (i; i < respuesta.length; i++) {
                                if (respuesta.charAt(i) == "$") {
                                    i++
                                    for (i; i < respuesta.length; i++) {
                                        if (respuesta.charAt(i) == "%") {
                                            i++
                                            for (i; i < respuesta.length; i++) {
                                                cartaBanca += respuesta.charAt(i);
                                            }
                                        }
                                        else {
                                            puntos += respuesta.charAt(i);
                                        }
                                    }
                                }
                                else {
                                    carta2 += respuesta.charAt(i);

                                }
                            }
                        }
                        else {
                            carta1 += respuesta.charAt(i);
                        }
                    }
                    for (let j = 0; j < cartaBanca.length; j++) {
                        if (cartaBanca.charAt(j) == " ") {
                            j += 4
                            cartaInBanca += "&&palo="
                            for (j; j < cartaBanca.length; j++) {
                                cartaInBanca += cartaBanca.charAt(j);
                            }
                        }
                        else {
                            cartaInBanca += cartaBanca.charAt(j);
                        }
                    }

                    puntaje += parseInt(puntos);
                    let mano = document.createElement("ul");
                    mano.setAttribute("id", "mano");
                    let manoB = document.createElement("ul");
                    manoB.setAttribute("id", "manoB");
                    let nombre = document.createElement("p");
                    nombre.setAttribute("id", "nombre");
                    let banca = document.createElement("p");
                    let A = document.createElement("li");
                    let B = document.createElement("li");
                    let C = document.createElement("li");
                    let D = document.createElement("li");
                    D.setAttribute("id", "puntos");
                    let Nbanca = document.createTextNode("Banca")
                    let cartaA = document.createTextNode(carta1);
                    let cartaB = document.createTextNode(carta2);
                    let cartaC = document.createTextNode(cartaBanca);
                    let user = document.createTextNode(localStorage.getItem("dni"));
                    let puntosJ = document.createTextNode("puntos " + puntaje)
                    A.appendChild(cartaA);
                    B.appendChild(cartaB);
                    D.appendChild(puntosJ);
                    C.appendChild(cartaC);
                    banca.appendChild(Nbanca);
                    nombre.appendChild(user);
                    mano.appendChild(nombre);
                    mano.appendChild(A);
                    mano.appendChild(B);
                    mano.appendChild(D);
                    banca.appendChild(C);
                    manoB.appendChild(banca);
                    document.getElementById("sect1").appendChild(mano);
                    document.getElementById("sect1").appendChild(manoB);

                    document.getElementById("jugar").style.visibility = "hidden";
                    document.getElementById("apuesta").style.visibility = "hidden";
                    document.getElementById("apostar").style.visibility = "hidden";
                    document.getElementById("plantarse").style.visibility = "visible";
                    document.getElementById("pedir").style.visibility = "visible";
                    numCartas += 2;
                }

            }
            if (puntaje > 20) {
                Plantarse();
            }
        }


        sxmlhttp.open("POST", "http://127.0.0.1:8080/ProyectoCasino/NuevaPartida", true);
        sxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        sxmlhttp.send("id=" + localStorage.getItem("dni") + "&&apuesta=" + apuesta);
    }
}

function Plantarse() {
    let sxmlhttp;
    sxmlhttp = new XMLHttpRequest();

    sxmlhttp.onreadystatechange = function () {
        if (sxmlhttp.readyState == 4 && sxmlhttp.status == 200) {
            let respuesta = sxmlhttp.responseText;
            let carta = "";
            var resultado = "";

            let i = 0;
            for (i; i < respuesta.length; i++) {
                if (respuesta.charAt(i) == "&") {
                    let li = document.createElement("li");
                    let naipe = document.createTextNode(carta);
                    li.appendChild(naipe);
                    document.getElementById("manoB").appendChild(li);
                    carta = "";
                }
                else if (respuesta.charAt(i) == "$") {
                    i++
                    for (i; i < respuesta.length; i++) {
                        if (respuesta.charAt(i) == "%") {
                            i++
                            for (i; i < respuesta.length; i++) {
                                puntajeBanca += respuesta.charAt(i);
                            }
                        }
                        else {
                            resultado += respuesta.charAt(i);
                        }
                    }
                }

                else {
                    carta += respuesta.charAt(i);
                }
            }
            let D = document.createElement("li");
            D.setAttribute("id", "puntosB");
            let puntosJ = document.createTextNode("puntos " + puntajeBanca);
            D.appendChild(puntosJ);
            document.getElementById("manoB").appendChild(D);
            alert(resultado);

        }
        document.getElementById("plantarse").style.visibility = "hidden";
        document.getElementById("pedir").style.visibility = "hidden";
        document.getElementById("rejugar").style.visibility = "visible";


    }
    sxmlhttp.open("POST", "http://127.0.0.1:8080/ProyectoCasino/Plantarse", true);
    sxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    sxmlhttp.send("puntos=" + puntaje + "&&numCartas=" + numCartas + "&&apuesta=" + apuesta + "&&id=" + localStorage.getItem("dni") + cartaInBanca);
}



function Apostar() {
    if (document.getElementById("apuesta").value.length > 0) {
        apuesta = document.getElementById("apuesta").value;

        document.getElementById("jugar").style.visibility = "visible";

    }
    else {
        alert("Introduzca una apuesta")
    }
}

function ReJugar() {
    let sec = document.getElementById("sect1");
    let m = document.getElementById("mano");
    let mB = document.getElementById("manoB");
    sec.removeChild(m);
    sec.removeChild(mB);
    puntaje = 0;
    numCartas = 0;
    cartaInBanca = "&&rango=";
    puntajeBanca = "";
    document.getElementById("apostar").style.visibility = "visible";
    document.getElementById("apuesta").style.visibility = "visible";
    document.getElementById("rejugar").style.visibility = "hidden";


}

function logIn() {
    let usuario = document.getElementById("username").value;
    let clave = document.getElementById("password").value;
    let sxmlhttp;
    sxmlhttp = new XMLHttpRequest();

    sxmlhttp.onreadystatechange = function () {
        if (sxmlhttp.readyState == 4 && sxmlhttp.status == 200) {
            let respuesta = sxmlhttp.responseText;
            if (respuesta == "Si") {
                localStorage.setItem("dni",document.getElementById('username').value);
                alert("Bienvenido " + localStorage.getItem("dni"));
            }
            else {
                alert(respuesta);
            }
        }
    }
    sxmlhttp.open("POST", "http://127.0.0.1:8080/ProyectoCasino/LogIn", true);
    sxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    sxmlhttp.send("id=" + usuario + "&&clave=" + clave);
}

function logOut(){
    sessionStorage.clear();
    localStorage.clear();
    loged()
}

function loged(){
    let DNI = localStorage.getItem("dni");
    if((DNI != "")&&(DNI != null)){
        document.getElementById("login").style.visibility="hidden";
        let p = document.createElement("p");
        p.setAttribute("id","saludo");
        let texto = document.createTextNode("Bienvenido " + DNI);
        p.appendChild(texto);
        document.getElementById("header").appendChild(p);
    }
    else{
        document.getElementById("header").removeChild(document.getElementById("saludo"));
        document.getElementById("login").style.visibility="visible";
    }
}