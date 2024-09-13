$(document).ready(function() {
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device', // Ersetze dies durch die tatsächliche URL
        type: 'GET',
        headers: {
            'Authorization': "08b4bb99-0d0a-42c2-a5ea-40326105c6f3"
        },
        success: function(response) {
            console.log('Erfolgreiche Antwort:', response);
        },
        error: function(error) {
            console.error('Fehler bei der Anfrage:', error);
        }
    });
});

function displayDevices(devices) {
    const contentDiv = document.getElementById('content'); 
    devices.forEach(device => {
        const deviceDiv = document.createElement('div');
        deviceDiv.classList.add('device');
        deviceDiv.innerHTML = `
            <div class="device-header">
                <h3>
                    <span class="material-symbols-outlined">${getIcon(device.type)}</span> ${device.name}
                </h3>
            </div>
            <div class="device-separator"></div>
            <div class="device-info">
                <p>Type: ${device.type}</p>
                <p>Location: ${device.location}</p>
                <p>State: ${device.state}</p>
                <br />
                <button class="device-button">${device.state === 'On' ? 'Turn Off' : 'Turn On'}</button>
            </div>
        `;
        contentDiv.appendChild(deviceDiv);
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
