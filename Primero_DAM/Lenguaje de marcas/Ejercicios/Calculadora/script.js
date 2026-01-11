var contador=0;
window.onload=function(){
    btnsumar=document.getElementById("btnsumar");
    btnsumar.addEventListener("click",suma)

    btnrestar=document.getElementById("btnrestar");
    btnrestar.addEventListener("click",resta)

    btnmultiplicar=document.getElementById("btnmultiplicar");
    btnmultiplicar.addEventListener("click",multiplicar)

    btndividir=document.getElementById("btndividir");
    btndividir.addEventListener("click",dividir)

    btnpotencia=document.getElementById("btnpotencia");
    btnpotencia.addEventListener("click",potencia)

}


function suma(){
    var numero1=parseFloat(document.getElementById("num1").value);
    var numero2=parseFloat(document.getElementById("num2").value);
    var resultado=numero1+numero2;

    document.getElementById("resultado").innerHTML=resultado;
   
}


function resta(){
    var numero1=parseFloat(document.getElementById("num1").value);
    var numero2=parseFloat(document.getElementById("num2").value);
    var resultado=numero1-numero2;

    document.getElementById("resultado").innerHTML=resultado;
   
}

function multiplicar(){
    var numero1=parseFloat(document.getElementById("num1").value);
    var numero2=parseFloat(document.getElementById("num2").value);
    var resultado=numero1*numero2;

    document.getElementById("resultado").innerHTML=resultado;
   
}


function dividir(){
    var numero1=parseFloat(document.getElementById("num1").value);
    var numero2=parseFloat(document.getElementById("num2").value);
    var resultado=numero1/numero2;

    document.getElementById("resultado").innerHTML=resultado;
   
}


function potencia(){
    var numero1=parseFloat(document.getElementById("num1").value);
    var numero2=parseFloat(document.getElementById("num2").value);
    var resultado=numero1**numero2;

    document.getElementById("resultado").innerHTML=resultado;
   
}
       