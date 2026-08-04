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

  const usuarios = JSON.parse(
    localStorage.getItem('usuarios') || '[]'
  );

  usuarios.push({
    usuario:newUsuario.value,
    password:newPassword.value
  });

  localStorage.setItem(
    'usuarios',
    JSON.stringify(usuarios)
  );

  backToLogin();

  alert('Cuenta creada');
}

function enterApp() {

    const usuarios = JSON.parse(
        localStorage.getItem('usuarios') || '[]'
    );

    if (
        usuarios.find(
            u =>
            u.usuario === loginUsuario.value &&
            u.password === loginPassword.value
        )
    ) {

        window.location.href = "/panel";

    } else {

        alert("Credenciales incorrectas");

    }
}


function cerrarSesion() {
    window.location.href = "/";
}




