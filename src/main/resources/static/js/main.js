function openRegisterScreen(){
  loginScreen.classList.add('hidden');
  registerScreen.classList.remove('hidden');
}

function getCookie(name) {
  const prefix = name + '=';
  const cookie = document.cookie.split(';').map(value => value.trim())
      .find(value => value.startsWith(prefix));
  return cookie ? decodeURIComponent(cookie.substring(prefix.length)) : null;
}

function backToLogin(){
  registerScreen.classList.add('hidden');
  loginScreen.classList.remove('hidden');
}

function openGoogleLogin(){
  loginScreen.classList.add('hidden');
  googleScreen.classList.remove('hidden');
}

function backToLoginFromGoogle(){
  googleScreen.classList.add('hidden');
  loginScreen.classList.remove('hidden');
}

function loginGoogle(){

    if(
        googleCorreo.value !== '' &&
        googlePassword.value !== ''
    ){

        window.location.href = "/panel";

    }else{

        alert("Completa los campos");

    }
}

function crearCuenta() {

    const usuario = newUsuario.value;
    const password = newPassword.value;

    if (usuario === '' || password === '') {
        alert('Completa todos los campos');
        return;
    }

    const csrfToken = document.querySelector('meta[name="_csrf"]')?.content || getCookie('XSRF-TOKEN');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-XSRF-TOKEN';

    fetch('/registrarCuenta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({
            usuario: usuario,
            password: password
        })
    })
    .then(res => res.json().then(data => ({
        status: res.status,
        data: data
    })))
    .then(({ status, data }) => {

        if (data.success) {

            alert('Cuenta creada correctamente');
            newUsuario.value = '';
            newPassword.value = '';
            backToLogin();

        } else {

            alert(data.mensaje || 'No se pudo crear la cuenta');

        }
    })
    .catch(() => {
        alert('Error de conexión con el servidor');
    });
}

function enterApp() {

    const usuario = loginUsuario.value;
    const password = loginPassword.value;

    if (usuario === '' || password === '') {
        alert('Completa todos los campos');
        return;
    }

    const csrfToken = document.querySelector('meta[name="_csrf"]')?.content || getCookie('XSRF-TOKEN');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-XSRF-TOKEN';

    fetch('/loginCuenta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({
            usuario: usuario,
            password: password
        })
    })
    .then(res => res.json().then(data => ({
        status: res.status,
        data: data
    })))
    .then(({ status, data }) => {

        if (data.success) {

            window.location.href = "/panel";

        } else {

            alert(data.mensaje || "Credenciales incorrectas");

        }
    })
    .catch(() => {
        alert('Error de conexión con el servidor');
    });
}


function cerrarSesion() {
    const csrfToken = document.querySelector('meta[name="_csrf"]')?.content || getCookie('XSRF-TOKEN');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-XSRF-TOKEN';

    fetch('/logout', {
        method: 'POST',
        headers: { [csrfHeader]: csrfToken }
    }).then(() => window.location.href = '/');
}




