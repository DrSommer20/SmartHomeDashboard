$(document).ready(function() {
    var GetDevices = {
           token: "efa9dd6e-6c8e-4d7d-b452-b9699b46de56"
        };
    $.ajax({
        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        
        success: function(data) {
            //displayDevices(data.devices);
            print(data.devices);
        },
        data: JSON.stringify(GetDevices),
        processData: false,
        contentType: 'application/json; charset=UTF-8',
        error: function (xhr, ajaxOptions, thrownError) {

            alert('Error: ' + xhr.status + '   ' + thrownError);

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

