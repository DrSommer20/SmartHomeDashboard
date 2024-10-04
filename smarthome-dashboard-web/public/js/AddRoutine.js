    const devices = new Array();

    $(document).ready(function() {
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
            type: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authToken')
            },
            success: function(response) {
                console.log('Erfolgreiche Antwort:', response);
                response.devices.forEach(device => {
                    $('.action-device').append(new Option(device.name, device.device_id));
                    devices.push(device);
                });
                
            },
            error: function(error) {
                console.error('Fehler bei der Anfrage:', error);
            }
        });

        $('#contentRoutine').on('submit', AddDeviceSubmit);
        
    
        
    });

    function AddDeviceSubmit(event){
        event.preventDefault(); // Verhindert das Standardverhalten (Seiten-Reload)

        const routineName = document.getElementById('routine-name').value;
        const routineTime = document.getElementById('routine-time').value;

        const actions = [];
        $('.action').each(function() {
            const deviceId = $(this).find('.action-device').find('option:selected').val();
            const deviceName = $(this).find('.action-device').find('option:selected').text();
            const actionType = $(this).find('.action-type').find('option:selected').val();
            console.log(deviceId, actionType);
            actions.push({ device_id: deviceId, device_name: deviceName, action: actionType });
        });

        const routineData = {
            name: routineName,
            actions: actions,
            trigger: {
                type: 'time',
                value: routineTime
            },
            state: true

        };


        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            headers: {
                'Authorization': localStorage.getItem('authToken')
            },
            success: function (data) {
                console.log('Routine added', data);
                window.location.href = 'Routines.html'; // Weiterleitung auf eine andere Seite
            },
            data: JSON.stringify(routineData),
            contentType: "application/json; charset=UTF-8",
            error: function (xhr, ajaxOptions, thrownError) {
                console.log('Error: ' + xhr.status + '   ' + thrownError);
                alert('An error occurred during adding routine. Please try again.');
            }
        });
    }

