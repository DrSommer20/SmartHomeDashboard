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

// Vergleichen der Daten
$("#user-editButton").click(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/user',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Aktuelle Daten vom Server:', response);
            const currentData = {
                firstName: $('#userfirstName').val(),
                lastName: $('#userlastName').val(),
                email: $('#useremail').val(),
                password: $('#userpassword').val(),
                pat: $('#userpat').val()
            };

            const changes = {};
            let hasChanges = false;

            if (response.firstName !== currentData.firstName) {
                changes.firstName = currentData.firstName;
                hasChanges = true;
            }
            if (response.lastName !== currentData.lastName) {
                changes.lastName = currentData.lastName;
                hasChanges = true;
            }
            if (response.email !== currentData.email) {
                changes.email = currentData.email;
                hasChanges = true;
            }
            if (response.passwort !== currentData.password) {
                changes.passwort = currentData.password;
                hasChanges = true;
            }
            if (response.pat !== currentData.pat) {
                changes.pat = currentData.pat;
                hasChanges = true;
            }

            if (hasChanges) {
                $.ajax({
                    url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/user',
                    type: 'PUT', 
                    headers: {
                        'Authorization': localStorage.getItem('authToken')
                    },
                    data: JSON.stringify(changes), 
                    contentType: 'application/json',
                    success: function(response) {
                        console.log('Daten erfolgreich aktualisiert:', response);
                    },
                    error: function(error) {
                        console.error('Fehler beim Aktualisieren der Daten:', error);
                    }
                });
            } else {
                console.log('Keine Änderungen festgestellt, kein Update erforderlich.');
            }
        },
        error: function(error) {
            console.error('Fehler beim Abrufen der aktuellen Daten:', error);
        }
    });
});
