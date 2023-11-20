function verificarLogin() {
    var token = localStorage.getItem('token');

    if (!token) {
        window.location.href = 'about.html';
    }
}

verificarLogin();

function limparFormulario() {
    var formulario = document.getElementById("proj-form");
    formulario.reset();
}

async function salvarProjeto() {
    verificarLogin();
    event.preventDefault();
    const nomeDoProjeto = document.getElementById('nomeDoProjeto').value;
    const dataDeInicio = document.getElementById('dataDeInicio').value;
    const custoDoProjeto = document.getElementById('custoDoProjeto').value;

    const projetoData = {
        nomeDoProjeto: nomeDoProjeto,
        dataDeInicio: dataDeInicio,
        custoDoProjeto: custoDoProjeto
    };

    const token = localStorage.getItem('token');
    const salvarProjetoUrl = 'http://localhost:8080/projetos';

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + token);

    const raw = JSON.stringify(projetoData);

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };
    try {
        const response = await fetch(salvarProjetoUrl, requestOptions);

        if (!response.ok) {
            throw new Error(`Erro HTTP! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Projeto salvo com sucesso:', data);
        window.location.href = 'andamento.html';
    } catch (error) {
        console.error('Erro ao salvar projeto:', error.message);
    }
}

