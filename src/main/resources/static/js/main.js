function openRegisterScreen(){
  loginScreen.classList.add('hidden');
  registerScreen.classList.remove('hidden');
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

function crearCuenta(){

  const params = new URLSearchParams();
  params.append('usuario', newUsuario.value);
  params.append('password', newPassword.value);

  fetch('/registrarCuenta', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: params
  })
    .then(res => res.json().then(data => ({ status: res.status, data })))
    .then(({ data }) => {

      if (data.success) {

        alert('Cuenta creada');
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

    const params = new URLSearchParams();
    params.append('usuario', loginUsuario.value);
    params.append('password', loginPassword.value);

    fetch('/loginCuenta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: params
    })
        .then(res => res.json().then(data => ({ status: res.status, data })))
        .then(({ data }) => {

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
    window.location.href = "/";
}




