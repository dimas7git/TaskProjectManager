function isUserLoggedIn() {
    const token = localStorage.getItem('token');
    return token !== null;
}

function updateAuthButtons() {
    const entrarButton = document.getElementById('entrar');
    const sairButton = document.getElementById('sair');

    if (isUserLoggedIn()) {
        entrarButton.textContent = 'Logado';
        entrarButton.style.display = 'none';  
        sairButton.style.display = 'block';  
    } else {
        entrarButton.textContent = 'Entrar';
        entrarButton.style.display = 'block';  
        sairButton.style.display = 'none';  
    }
}

window.onload = function () {
    updateAuthButtons();
};

