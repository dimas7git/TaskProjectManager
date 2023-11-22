
async function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const loginUrl = 'http://localhost:8080/auth/login';

    const data = {
        usuario: username,
        senha: password
    };

    try {
        const response = await axios.post(loginUrl, data, { withCredentials: true });
        const token = response.data.token;

        localStorage.setItem('token', token);

        console.log('Login bem-sucedido. Token:', token);

        window.location.href = 'index.html';
    } catch (error) {
        console.error('Erro durante o login:', error);
        const err = document.getElementById('erro-login');
        err.textContent = 'Login inválido!'
    }
}

