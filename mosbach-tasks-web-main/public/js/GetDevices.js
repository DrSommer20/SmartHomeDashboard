$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device', // Ersetze dies durch die tatsächliche URL
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});
