$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
            $('#firstName').val(response.firstName);
            $('#lastName').val(response.lastName);
            $('#email').val(response.email);
            $('#password').val(response.passwort);
            $('#pat').val(response.pat);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});

     