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
    const contentDiv = document.getElementById('content');
    var index = 1;
    contentDiv.innerHTML = "";
    if(devices !== undefined){
        devices.forEach(device => {
                const uniqueId = 'toggleCheckbox' + index;
                const deviceDiv = document.createElement('div');
                deviceDiv.classList.add('col-sm-12', 'col-md-12', 'col-lg-6', 'col-xl-4');
                //TODO: Edit Button auf Dashboard verstecken
                deviceDiv.innerHTML = `
                <div class="display-card">
            <div class="display-card-header">
                <h3>
                    <span class="material-symbols-outlined">${device.typeIcon}</span> ${device.name}
                </h3>
                <div class="button-container">
                  <button class="delete-button" id="device-delete-button`+uniqueId+`" ><span class="material-symbols-outlined">delete</span></button>
                  <button class="edit-button" id="device-edit-button`+uniqueId+`" >Edit</button>
                </div>
            </div>
            <div class="card-separator"></div>
            <div class="device-info">
                <p>Type: ${device.type}</p>
                <p>Location: ${device.roomName}</p>
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
            const deletebutton = document.getElementById("device-delete-button" + uniqueId);
            if (device.state === 'On') {
                checkbox.checked = true;
            }

            function handleCheckboxChange() {
                handleChange(this.checked, device.device_id, uniqueId, this);
            }

            function handleeditbuttonclick(){
                editbuttonclick(device.device_id);
            }
            function handledeletebuttonclick(){
                deletebuttonclick(device.device_id);
            }
            editbutton.addEventListener("click", handleeditbuttonclick);
            deletebutton.addEventListener("click", handledeletebuttonclick);
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
//
//Edit Device
//
function editbuttonclick(device_id) {
    $('.popup').css('display', 'flex');
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
   //fill out fields
   let deviceOldData = {};
  $(document).ready(function() {
      $.ajax({
          url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id,
          type: 'GET',
          headers: {
              'Authorization': localStorage.getItem('authToken')
          },
          success: function(response) {
              $('#deviceName').val(response.name);
              $('#deviceType').val(response.typeID);
              $('#deviceLocation').val(response.location);
              $('#deviceId').val(response.device_id);

              deviceOldData = {
                  name: response.name,
                  typeID: response.typeID,
                  location: response.location,
                  device_id: response.device_id
              };
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
  });

// Save button, Change function
$("#saveBtn").click(function() {
    const currentData = {
        name: $('#deviceName').val(),
        typeID: parseInt($('#deviceType').val()),
        location: parseInt($('#deviceLocation').val()),
        device_id: $('#deviceId').val()
        };
    updateDeviceIfDifferent('name', currentData.name, deviceOldData.name, currentData.device_id);
    updateDeviceIfDifferent('typeID', currentData.typeID, deviceOldData.typeID, currentData.device_id);
    updateDeviceIfDifferent('location', currentData.location, deviceOldData.location, currentData.device_id);
});

function updateDeviceIfDifferent(field, newValue, oldValue, device_id) {
    if (newValue !== oldValue) {
        updateField(field, newValue, device_id);
    }
}

function updateField(field, newValue, device_id) {
    const data = {
        "new-value": newValue,
        "field": field
    };
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id,
        type: 'PUT',
        dataType: 'json',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            console.log(`Field ${field} successfully updated:`, response);
            window.location.href = 'Devices.html';
            window.location.reload();
        },
        error: function(error) {
            console.error(`Error updating ${field}:`, error);
            window.location.href = 'Devices.html';
            window.location.reload();
        },
    });
}

$('.popup-close').click(function() {
    $('.popup').css('display', 'none');
});
}

//Delete Device
function deletebuttonclick(device_id) {
    $('.deletepopup').css('display', 'flex');
    $('#deviceDeleteBtn').click(function() {
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/' + device_id,
            type: 'DELETE',
            headers: {
                'Authorization': localStorage.getItem('authToken'),
                'Content-Type': 'application/json'
            },
            success: function(response) {
                console.log('Device deleted successfully:', response);
                window.location.href = 'Devices.html';
                window.location.reload();
            },
            error: function(error) {
                console.error('Error deleting device:', error);
                window.location.href = 'Devices.html';
                
            }
        });
    });

    $('#deviceclosePopup').click(function() {
        $('.deletepopup').css('display', 'none');
    });
}