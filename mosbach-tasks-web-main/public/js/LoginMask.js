document.querySelector('.sign-up').addEventListener('click', function() {
    document.querySelector('.login-box').style.height="650px"
});

document.querySelector('.sign-in').addEventListener('click', function() {
    document.querySelector('.login-box').style.height="500px" // Zurück zur normalen Größe

});    

// On login
function loginUser(token, rememberMe) {
    if (rememberMe) {
      localStorage.setItem('authToken', token);
    } else {
      sessionStorage.setItem('authToken', token);
    }
  }
  
  // On page load
  function checkAuth() {
    let token = localStorage.getItem('authToken') || sessionStorage.getItem('authToken');
    
    if (token) {
      // Validate the token with the server
      validateToken(token).then(isValid => {
        if (isValid) {
          // User is authenticated
          console.log("User is signed in");
        } else {
          // Token is invalid
          localStorage.removeItem('authToken');
          sessionStorage.removeItem('authToken');
        }
      });
    }
  }
  
  // Validate token with the server
  async function validateToken(token) {
    // Implement token validation with your backend
    return true; // or false based on actual validation
  }
  
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
