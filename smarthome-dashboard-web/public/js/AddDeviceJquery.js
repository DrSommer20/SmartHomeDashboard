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

    $("#deviceSmartThings").change(function() {
        alert(this.value);
    });
    
    $("#AddDeviceSubmit").click(function() {

        var AddNewDevice = {
                device_id: $("#deviceSmartThings").find('option:selected').val(),
                name: $("#deviceName").val(),
                type:$("#deviceType").val(),
                location:$("#deviceLocation").val()
            };

        console.log(AddNewDevice);
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
            type: 'post',
            dataType: 'json',
            headers: {
                        'Authorization': localStorage.getItem('authToken')
                    },

             contentType: 'application/json',

            success: function (data) {
                location.href='../homepage.html';
            },

            data: JSON.stringify(AddNewDevice),

            processData: false,

            contentType: "application/json; charset=UTF-8",

            error: function (xhr, ajaxOptions, thrownError) {

                alert('Error: ' + xhr.status + '   ' + thrownError);

            }

        });

    });
});

