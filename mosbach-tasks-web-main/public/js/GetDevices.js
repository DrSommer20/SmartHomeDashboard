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
        const uniqueId = 'toggleCheckbox§{index}';
        const deviceDiv = document.createElement('div');
        deviceDiv.classList.add('col-sm-12 col-md-12 col-lg-6 col-xl-4');
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
