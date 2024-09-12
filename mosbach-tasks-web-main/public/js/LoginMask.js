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

    try {
        // POST-Anfrage an den Server senden
        const response = await fetch('/api/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json(); // Antwort in JSON umwandeln

        if (response.ok) {
            // Login erfolgreich - Token speichern
            alert('Login successful!');
            localStorage.setItem('authToken', data.token); // Token im Local Storage speichern
            window.location.href = '/Homepage.html'; // Weiterleitung auf eine andere Seite
        } else {
            // Fehlgeschlagener Login - Fehlermeldung anzeigen
            alert(data.message || 'Login failed. Please try again.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred during login. Please try again.');
    }
});

// Sign-up-Funktion
document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Verhindert das Standardverhalten (Seiten-Reload)

    // Anmeldedaten aus dem Formular abrufen
    const email = document.getElementById('signup-email').value;
    const password = document.getElementById('signup-password').value;
    const passwordConfirm = document.getElementById('signup-password-confirm').value;

    // Überprüfen, ob die Passwörter übereinstimmen
    if (password !== passwordConfirm) {
        alert('Passwords do not match!');
        return;
    }

    // JSON-Payload erstellen
    const signupData = {
        email: email,
        password: password
    };

    try {
        // POST-Anfrage an den Server senden
        const response = await fetch('/api/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(signupData)
        });

        const data = await response.json(); // Antwort in JSON umwandeln

        if (response.ok) {
            // Sign-up erfolgreich - Token speichern
            alert('Signup successful!');
            localStorage.setItem('authToken', data.token); // Token im Local Storage speichern
            window.location.href = '/Homepage.html'; // Weiterleitung auf eine andere Seite
        } else {
            // Fehlgeschlagener Sign-up - Fehlermeldung anzeigen
            alert(data.message || 'Signup failed. Please try again.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred during signup. Please try again.');
    }
});