const switchers = [...document.querySelectorAll('.switcher')]

switchers.forEach(item => {
	item.addEventListener('click', function() {
		switchers.forEach(item => item.parentElement.classList.remove('is-active'))
		this.parentElement.classList.add('is-active')
	})
})

// Login-Funktion

document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Verhindert das Standardverhalten (Seiten-Reload)

    // E-Mail und Passwort aus dem Formular abrufen
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    // JSON-Payload erstellen
    const loginData = {
        email: email,
        password: password
    };

    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/auth',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            alert('Login successful!');
            localStorage.setItem('authToken', data.token); // Token im Local Storage speichern
            window.location.href = '../homepage.html'; // Weiterleitung auf eine andere Seite
        },
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=UTF-8",
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('Error: ' + xhr.status + '   ' + thrownError);
            alert('An error occurred during login. Please try again.');
        }
    });
});
