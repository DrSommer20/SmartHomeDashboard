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
        
        $('#add-action').click(function() {
            addAction();
        });      
 
    });

    function addAction() {
        const actionDiv = $(`
            <div class="action">
                <div class="action-group">
                    <label for="action-device">Device:</label>
                    <select class="action-device" required></select>
                </div>
                <div class="action-group">
                    <label for="action-type">Action:</label>
                    <select class="action-type" required>
                        <option value="on">On</option>
                        <option value="off">Off</option>
                    </select>
                </div>
                <button type="button" class="delete-action">
                    <span class="material-symbols-outlined">DELETE</span>
                </button>
            </div>
        `);
        devices.forEach(device => {
            actionDiv.find('.action-device').append(new Option(device.name, device.device_id));
        });
        actionDiv.find('.delete-action').click(function() {
            $(this).closest('.action').remove(); // Entfernt die Action
        });
        $('#actions').append(actionDiv);
    }

    function AddDeviceSubmit(event){
        event.preventDefault(); 

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
                window.location.href = 'Routines.html'; 
            },
            data: JSON.stringify(routineData),
            contentType: "application/json; charset=UTF-8",
            error: function (xhr, ajaxOptions, thrownError) {
                console.log('Error: ' + xhr.status + '   ' + thrownError);
                alert('An error occurred during adding routine. Please try again.');
            }
        });
    }

