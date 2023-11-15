
function isUserLoggedIn() {
    const token = localStorage.getItem('token');
    return token !== null;
}

function updateAuthButton() {
    const authButton = document.getElementById('entrar');

    if (isUserLoggedIn()) {
        authButton.textContent = 'Logado';
        document.getElementById('entrar').style.display = 'none';
    } else {
        authButton.textContent = 'Entrar';
        document.getElementById('sair').style.display = 'none';
    }
}

window.onload = function () {
    updateAuthButton();
};
