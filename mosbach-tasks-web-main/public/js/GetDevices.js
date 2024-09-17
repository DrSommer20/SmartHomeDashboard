$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
            displayDevices(response.devices);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});

function displayDevices(devices) {
    const contentDiv = document.getElementById('content');
    var index = 1;
    devices.forEach(device => {
        const uniqueId = 'toggleCheckbox' + index;
        const deviceDiv = document.createElement('div');
        deviceDiv.classList.add('col-sm-12', 'col-md-12', 'col-lg-6', 'col-xl-4');
        deviceDiv.innerHTML = `
        <div class="device">
            <div class="device-header">
                <h3>
                    <span class="material-symbols-outlined">${getDeviceIcon(device.type)}</span> ${device.name}
                </h3>
            </div>
            <div class="device-separator"></div>
            <div class="device-info">
                <p>Type: ${device.type}</p>
                <p>Location: ${device.location}</p>
                <p>State: ${device.status}</p>
                 <input type="checkbox" id="`+uniqueId+`" class="toggleCheckbox" />
            <label for="`+uniqueId+`" class="toggleContainer">
            <div>OFF</div>
            <div>ON</div>
          </label>
        </div>
      </div>
    `;
        contentDiv.appendChild(deviceDiv);
        // Set checkbox to checked if the device state is "On"
        const checkbox = document.getElementById(uniqueId);
        if (device.state === 'On') {
            checkbox.checked = true;
        }

        function handleCheckboxChange() {
            handleChange(this.checked, device.device_id, uniqueId, this);
        }

        checkbox.addEventListener('change', handleCheckboxChange);
        index++;
    });

}
    //Symbole
    function getDeviceIcon(type) {
        switch(type.toLowerCase()) {
            case 'light': return 'light';
            case 'outlet': return 'outlet';
            case 'lamp': return 'table_lamp';
            default: return 'device_unknown';
        }
    }


    function handleChange(isChecked, device_id, uniqueId, checkboxElement){
            $.ajax({
                url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id + '/switch/'  + (isChecked ? 'on' : 'off'),
                method: 'POST',
                headers: {
                            'Authorization': localStorage.getItem('authToken')
                        },
                success: function(response) {
                    console.log('Device updated successfully:', response);
                    onSuccess(device_id, uniqueId);
                },
                error: function(error) {
                   console.error('Error updating device:', error);
                }
           });
    }

    function onSuccess(device_id, uniqueId){
        $.ajax({
                url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id,
                type: 'GET',
                headers: {
                    'Authorization': localStorage.getItem('authToken')
                },
                success: function(response) {
                    console.log('Erfolgreiche Antwort:', response);
                    const checkbox = document.getElementById(uniqueId);
                            if (response.state === 'On') {
                                checkbox.checked = true;
                            }
                            else{
                                checkbox.checked = false;
                            }
                },
                error: function(error) {
                    console.error('Fehler bei der Anfrage:', error);
                }
            });
    }


