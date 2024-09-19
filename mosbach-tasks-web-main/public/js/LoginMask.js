document.querySelector('.sign-up').addEventListener('click', function() {
    document.querySelector('.login-box').style.height="650px"
});

document.querySelector('.sign-in').addEventListener('click', function() {
    document.querySelector('.login-box').style.height="500px" // Zurück zur normalen Größe

});    

document.getElementById('login-space').addEventListener('submit', async function(event) {
    event.preventDefault(); // Verhindert das Standardverhalten (Seiten-Reload)

    // E-Mail und Passwort aus dem Formular abrufen
    const email = document.getElementById('login-mail').value;
    const password = document.getElementById('pass').value;

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
});function signUp() {
    // Collect form values
    const firstName = $('#userfirstname').val();
    const lastName = $('#userlastname').val();
    const email = $('#email').val();
    const password = $('#password').val();
    const pat = $('#userpat').val() || null;

    // Create an object with the form data
    const signUpData = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
        pat: pat
    };

    // Log the data (for debugging purposes)
    console.log('Sign Up Data:', signUpData);

    // Send the data to the backend using $.ajax
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/auth', // Replace with your backend URL
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(signUpData), // Convert the object to a JSON string
        success: function(response) {
            console.log('Success:', response);
            alert('Sign up successful!');
            // You can redirect the user after a successful signup if needed
             window.location.href = '/SubPages/Homepage.html';
        },
        error: function(xhr, status, error) {
            console.error('Error:', status, error);
            alert('Sign up failed! Please try again.');
        }
    });
}