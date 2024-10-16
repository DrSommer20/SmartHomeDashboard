$(document).ready(function() {
     updateDevices();
     setInterval(updateDevices, 30000);
});

function updateDevices(){
     $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
            type: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authToken')
            },
            success: function(response) {
                console.log('Erfolgreiche Antwort update Devices:', response);
                displayDevices(response.devices);
            },
            error: function(error) {
                console.error('Fehler bei der Anfrage:', error);
            }
        });
}

function displayDevices(devices) {
console.log('function display Devices');
    const contentDiv = document.getElementById('content');
    var index = 1;
    contentDiv.innerHTML = "";
    if(devices !== undefined){
        devices.forEach(device => {
                const uniqueId = 'toggleCheckbox' + index;
                const deviceDiv = document.createElement('div');
                deviceDiv.classList.add('col-sm-12', 'col-md-12', 'col-lg-6', 'col-xl-4');
                deviceDiv.innerHTML = `
                <div class="display-card">
                    <div class="display-card-header">
                        <h3>
                            <span class="material-symbols-outlined">${device.type}</span> ${device.name}
                            <button class="edit-button" id="device-edit-button`+uniqueId+`" >Edit</button>
                        </h3>
                    </div>
                    <div class="card-separator"></div>
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
            const editbutton = document.getElementById("device-edit-button" + uniqueId);
            if (device.state === 'On') {
                checkbox.checked = true;
            }

            function handleCheckboxChange() {
                handleChange(this.checked, device.device_id, uniqueId, this);
            }

            function handleeditbuttonclick(){
                editbuttonclick(device.device_id);
            }
            editbutton.addEventListener("click", handleeditbuttonclick);

            checkbox.addEventListener('change', handleCheckboxChange);
            index++;
            }
        );
    }
}


    function handleChange(isChecked, device_id, uniqueId, checkboxElement){
            $.ajax({
                url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id + '/switch/'  + (isChecked ? 'on' : 'off'),
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
                url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id,
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
//Edit Device
function editbuttonclick(device_id){
    $('.popup').css('display', 'flex');
            $.ajax({
                url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device', //TODO:hier Device Types abfragen
                type: 'GET',
                headers: {
                    'Authorization': localStorage.getItem('authToken')
                },
                //TODO: anpassen! also glaub ich
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
   //Felder vorausfüllen
    $.ajax({
                url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id ,
                type: 'GET',
                headers: {
                    'Authorization': localStorage.getItem('authToken')
                },
                success: function(response) {
                    console.log('Erfolgreiche Antwort Popup:', response);
                     $('#deviceName').val(response.name);
                     $('#deviceType').val(response.type);
                     $('#deviceLocation').val(response.location);
                     $('#deviceId').val(response.device_id);
                },
                error: function(error) {
                    console.error('Fehler bei der Anfrage:', error);
                }
    });
}
//Save, Change function
$("#saveBtn").on("submit",function() {
    const name = document.getElementById('deviceName').value;
    const type = document.getElementById('deviceType').value;
    const location = document.getElementById('deviceLocation').value;
    const device_id = document.getElementById('deviceId').value;

    const editDeviceData = {
        name: name,
        typeID: type,
        location: location,
        device_id:device_id
    };
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'POST',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            console.log('Device edit', editDeviceData);
                        window.location.href = 'Devices.html';
           },
        error: function(error) {
            console.error('Fehler beim Abrufen der aktuellen Daten:', error);
        }
    });
});

$('.popup-close').click(function() {
    $('.popup').css('display', 'none');
});

