$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
            $('#userfirstName').val(response.firstName);
            $('#userlastName').val(response.lastName);
            $('#useremail').val(response.email);
            $('#userpassword').val(response.passwort);
            $('#userpat').val(response.pat);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});

     