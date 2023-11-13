function includeHTML() {
    var header = document.getElementById('header');
    var footer = document.getElementById('footer');

    fetch('header.html')
        .then(response => response.text())
        .then(data => {
            header.innerHTML = data;
        });

    fetch('footer.html')
        .then(response => response.text())
        .then(data => {
            footer.innerHTML = data;
        });
}

function conditionallyRemoveButton(html) {
    if (window.location.pathname === '/specialPage.html') {
        html = html.replace('<button id="conditionalButton">Conditional Button</button>', '');
    }
    return html;
}

includeHTML();
