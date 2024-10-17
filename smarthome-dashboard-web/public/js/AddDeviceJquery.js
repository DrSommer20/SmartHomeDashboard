$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/smartthings',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
            response.devices.forEach(device => {
                $('#deviceSmartThings').append(new Option(device.name, device.device_id));
            });
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });


 $.ajax({
                url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/device-type',
                type: 'GET',
                headers: {
                    'Authorization': localStorage.getItem('authToken')
                },
                success: function(response) {
                    console.log('Erfolgreiche Antwort: device Type', response);
                    response.devicetypes.forEach(type => {
                        $('#deviceType').append(new Option(type.type, type.typeID));
                    });
                },
                error: function(error) {
                    console.error('Fehler bei der Anfrage:', error);
                }
            });

 $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room',
        type: 'GET',
        headers: {
           'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort: Room', response);
            response.rooms.forEach(room => {
            $('#deviceLocation').append(new Option(room.name, room.room_id));
            });
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
});


    $("#AddDeviceSubmit").click(function() {
        var AddNewDevice = {
            device_id: $("#deviceSmartThings").find('option:selected').val(),
            name: $("#deviceName").val(),
            typeID: $("#deviceType").find('option:selected').val(),
            location: $("#deviceLocation").find('option:selected').val(),
        };
        console.log(AddNewDevice);
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
            type: 'POST',
            dataType: 'json',
            headers: {
                'Authorization': localStorage.getItem('authToken')
            },
            contentType: 'application/json',
            success: function(data) {
                location.href = 'Devices.html';

            },
            data: JSON.stringify(AddNewDevice),
            processData: false,
            error: function(xhr, ajaxOptions, thrownError) {
                alert('Error: ' + xhr.status + '   ' + thrownError);
            }
        });
    });
});
