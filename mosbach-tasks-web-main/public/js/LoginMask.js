$(document).ready(function() {
    if($('#tab-2').is(':checked')) {
        document.querySelector('.login-box').style.height = "650px";
    }
    else if($('#tab-1').is(':checked')) {
        document.querySelector('.login-box').style.height = "500px";
    }
});

$('#tab-2').on('click', function() {
    document.querySelector('.login-box').style.height="650px";
});

$('#tab-1').on('click', function() {
    document.querySelector('.login-box').style.height="500px"; // Zurück zur normalen Größe
});

$('#login-form').on('submit', loginSubmit);
$('#sign-up-form').on('submit', signUp);

function loginSubmit(event){
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
            localStorage.setItem('authToken', data.token); // Token im Local Storage speichern
            console.log('Login successful', data);
            window.location.href = '../homepage.html'; // Weiterleitung auf eine andere Seite
        },
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=UTF-8",
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('Error: ' + xhr.status + '   ' + thrownError);
            alert('An error occurred during login. Please try again.');
        }
    });
}

function signUp(event) {
    event.preventDefault(); // Verhindert das Standardverhalten (Seiten-Reload)

    const firstName = $('#userfirstname').val();
    const lastName = $('#userlastname').val();
    const email = $('#useremail').val();
    const password = $('#userpassword').val();
    const pat = $('#userpat').val() || null;

    const signUpData = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
        pat: pat
    };

    // Log the data (for debugging purposes)
    console.log('Sign Up Data:', signUpData);

    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/auth/sign-up',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(signUpData),
        success: function(response) {
            console.log('Success:', response);
            $('#sign-up-failure').hide();
            $('#sign-up-success').show();
        },
        error: function(status, error) {
            console.error('Error:', status, error);
            $('#sign-up-failure').show();
            $('#sign-up-success').hide();
        }
    });

}