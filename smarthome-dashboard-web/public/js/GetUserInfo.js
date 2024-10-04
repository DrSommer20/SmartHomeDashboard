$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
            $('#userfirstName').val(response.firstName);
            $('#userlastName').val(response.lastName);
            $('#useremail').val(response.email);
            $('#userpassword').val(response.password);
            $('#sidebarfirstname').html(response.firstName);
            $('#sidebarlastname').html(response.lastName);
            $('#sidebarinitials').html(response.firstName.charAt(0) + response.lastName.charAt(0));
            $('#userpat').val(response.pat);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});

$("#user-editButton").click(function() {
    const currentData = {
        firstName: $('#userfirstName').val(),
        lastName: $('#userlastName').val(),
        email: $('#useremail').val(),
        password: $('#userpassword').val(),
        pat: $('#userpat').val()
    };
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Aktuelle Daten vom Server:', response);

            if (response.firstName !== currentData.firstName) {
                updateField('firstName', currentData.firstName);
            }
            if (response.lastName !== currentData.lastName) {
                updateField('lastName', currentData.lastName);
            }
            if (response.email !== currentData.email) {
                updateField('email', currentData.email);
            }
            if (response.password !== currentData.password) {
                updateField('password', currentData.password);
            }
            if (response.pat !== currentData.pat) {
                updateField('pat', currentData.pat);
           }
        },
        error: function(error) {
            console.error('Fehler beim Abrufen der aktuellen Daten:', error);
        }
    });
});


function updateField(field, newValue) {
    const data = {
            "new-value": newValue,
            "field": field
    };

    if (field === 'password') {
        console.log(`Feld ${field} wird aktualisiert.`);
    } else {
        console.log(`Feld ${field} wird aktualisiert auf: ${newValue}`);
    }
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'PUT',
        datatype: 'json',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            console.log(`Feld ${field} erfolgreich aktualisiert:`, response);
        },
        error: function(error) {
            console.error(`Fehler beim Aktualisieren von ${field}:`, error);
        },
        async: false
    });
     location.reload(true);
}
