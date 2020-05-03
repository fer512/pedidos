var callbackResponse = function (request) {
    if (request.readyState === 4 && request.status === 200) {
        addAlert('Pedido guardado correctamente','success');
    }else{
         addAlert('Error al guardar Pedido','danger');
    }
}

function createRequestDTO() {
    var data = JSON.stringify({
        "nombre": document.getElementById("nombre").value,
        "monto": document.getElementById("monto").value,
        "descuento": document.getElementById("descuento").value
    });
    return data;
}

function createHttpRequest(callbackResponse,url) {
    var request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = callbackResponse;
    return request;
}

function addValidationMessage(input) {
    if (!input.checkValidity()) {
        input.nextSibling.nextSibling.innerHTML = input.validationMessage;
    } else {
        input.nextSibling.nextSibling.innerHTML = '';
    }
}

function updateValidationMessage() {
    var inputs = document.getElementsByTagName("input");
    var input;
    for (input of inputs) {
        addValidationMessage(input);
    }
}

function onSubmit(form, event) {
    updateValidationMessage();
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
        event.preventDefault();
        var request = createHttpRequest(callbackResponse,"/pedidos/guardar");
        request.send(createRequestDTO());
    }
}

function addAlert(txt,alertType){
    var alert = document.getElementById('alert-component');
    alert.innerHTML = '';
    alert.innerHTML = '<div class="alert alert-'+alertType+'" role="alert">'+txt+'</div>';
}


(function () {
    'use strict';
    window.addEventListener('load', function () {
        var form = document.getElementById('form-pedidos');
        form.addEventListener('submit', function (event) {
            onSubmit(form, event);
        }, false);

    }, false);
})();
