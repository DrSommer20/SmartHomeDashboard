$(document).ready(function() {
    updateRoutines();
    setInterval(updateRoutines, 30000);
});

function updateRoutines(){
    $.ajax({
           url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine',
           type: 'GET',
           headers: {
               'Authorization': localStorage.getItem('authToken'),
               'Content-Type': 'application/json'
           },
           success: function(response) {
               console.log('Erfolgreiche Antwort:', response);
               displayRoutine(response.routines);
           },
           error: function(error) {
               console.error('Fehler bei der Anfrage:', error);
           }
       });
}

function displayRoutine(routines) {
   const contentDiv = document.getElementById('content');
   var index = 1;
   contentDiv.innerHTML = "";
   if(routines !== undefined){
       routines.forEach(routine => {
            const actionsDisplay = document.createElement('div');
            routine.actions.forEach(action => {
                actionDiv = document.createElement('div');
                actionDiv.classList.add('action-display');
                actionDiv.innerHTML = `<p>Device: ${action.device_name}</p>
                    <p>Action: Turn ${action.action}</p>`;
                actionsDisplay.appendChild(actionDiv);
            });
            const uniqueId = 'toggleCheckbox' + index;
            const routineDiv = document.createElement('div');
            routineDiv.classList.add('col-sm-12', 'col-md-12', 'col-lg-6', 'col-xl-4');
            routineDiv.innerHTML = `
            <div class="display-card">
        <div class="display-card-header">
            <h3>
                <span class="material-symbols-outlined">schedule</span> ${routine.name}
            </h3>
            <div class="button-container">
              <button class="delete-button" id="routine-delete-button`+uniqueId+`" ><span class="material-symbols-outlined">delete</span></button>
              <button class="edit-button" id="routine-edit-button`+uniqueId+`" >Edit</button>
         </div>
        </div>
        <div class="card-separator"></div>
        <div class="routine-info">
            <p>Trigger Time: ${routine.trigger.value}</p>
            <div class="actions-display">
                <h5>Actions:</h5>
                ${actionsDisplay.innerHTML}     
            </div>
            <input type="checkbox" id="`+uniqueId+`" class="toggleCheckbox" />
                <label for="`+uniqueId+`" class="toggleContainer">
                <div>OFF</div>
                <div>ON</div>
                </label>
    </div>
            `;

           contentDiv.appendChild(routineDiv);
           // Set checkbox to checked if the device state is "On"
           const checkbox = document.getElementById(uniqueId);
           if (routine.state) {
               checkbox.checked = true;
           }
           const deletebutton = document.getElementById("routine-delete-button"+uniqueId);
           const editbutton = document.getElementById("routine-edit-button"+uniqueId);
           function handleCheckboxChange() {
               handleChange(this.checked, routine.id, uniqueId, this);
           }
            function handledeletebuttonclick(){
                deletebuttonclick(routine.id);
            }  
            function handleeditbuttonclick(){
                editbuttonclick(routine.id);
            }
            deletebutton.addEventListener("click", handledeletebuttonclick);
            editbutton.addEventListener("click", handleeditbuttonclick);
           checkbox.addEventListener('change', handleCheckboxChange);
           index++;
           }
       );
   }
   function handleChange(isChecked, device_id, uniqueId, checkboxElement){
}

//Edit Routine
const devices = [];

$(document).ready(function() {
    return $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function (response) {
            console.log('Geräte erfolgreich abgerufen:', response);
            response.devices.forEach(device => {
                devices.push(device); 
            });
        },
        error: function (error) {
            console.error('Fehler beim Abrufen der Geräte:', error);
        }
    });
});

function editbuttonclick(id) {
    routineId = id;
    $('.popup').css('display', 'flex');
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine/' + id,
        type: 'GET',
        headers: {
            'Authorization': localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        },
        success: function (response) {
            console.log('Routine erfolgreich abgerufen:', response);
            $('#routineName').val(response.name);
            $('#routineTime').val(response.trigger.value);
            displayExistingActions(response.actions);
        },
        error: function (error) {
            console.error('Fehler beim Abrufen der Routine:', error);
        }
    });
    if (devices.length === 0) {
        fetchDevices().done(function () {
            $('#add-action').click(function () {
                addAction();
            });
        });
    } else {
        $('#add-action').click(function () {
            addAction();
        });
    }

    $('.popup-close').click(function () {
        $('.popup').css('display', 'none');
    });
}

function displayExistingActions(actions) {
    $('#actions').empty();
    actions.forEach(action => {
        addAction(action.id, action.device_id, action.action);
    });
}
function addAction(actionId = '', selectedDeviceId = '', selectedAction = '') {
    const actionDiv = $(`
        <div class="action" data-action-id="${actionId}">
            <div class="action-group">
                <label for="action-device">Gerät:</label>
                <select class="action-device" required></select>
            </div>
            <div class="action-group">
                <label for="action-type">Aktion:</label>
                <select class="action-type" required>
                    <option value="on" ${selectedAction === 'on' ? 'selected' : ''}>An</option>
                    <option value="off" ${selectedAction === 'off' ? 'selected' : ''}>Aus</option>
                </select>
            </div>
            <button type="button" class="delete-action">
                <span class="material-symbols-outlined">delete</span>
            </button>
        </div>
    `);

    // Geräteliste hinzufügen und das ausgewählte Gerät markieren
    devices.forEach(device => {
        const option = new Option(device.name, device.device_id);
        if (device.device_id === selectedDeviceId) {
            $(option).attr("selected", "selected");
        }
        actionDiv.find('.action-device').append(option);
    });

    // Klick-Event für das Löschen der Aktion
    actionDiv.find('.delete-action').click(function () {
        $(this).closest('.action').remove();
    });

    // Aktion zum Actions-Bereich hinzufügen
    $('#actions').append(actionDiv);
}

//save/change Routine
let routineId = null;

$('#RoutinesaveBtn').click(function (id) {
    const routineName = $('#routineName').val();
    const routineTime = $('#routineTime').val();
    const actions = [];
    $('.action').each(function () {
        const actionId = $(this).data('action-id');
        const deviceId = $(this).find('.action-device').find('option:selected').val();
        const deviceName = $(this).find('.action-device').find('option:selected').text();
        const actionType = $(this).find('.action-type').find('option:selected').val();
        actions.push({ action_id: actionId, device_id: deviceId, device_name: deviceName, action: actionType });

    });
    const routine = {
        name: routineName,
        actions: actions,
        trigger: {
            type: 'time',
            value: routineTime
        },
        state: true

    };
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine/' + routineId,
        type: 'PUT',
        headers: {
            'Authorization': localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(routine),
            success: function (response) {
                console.log('Routine erfolgreich erstellt:', response);
                window.location.href = "routines.html";
                window.location.reload();
            },
            
        error: function (error) {
            console.error('Fehler beim Erstellen der Routine:', error);
        }
    });
});

//Delete Routine
function deletebuttonclick(id){
    $('.deletepopup').css('display', 'flex');
    $('#routineDeleteBtn').click(function() {
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine/' + id,
            type: 'DELETE',
            headers: {
                'Authorization': localStorage.getItem('authToken'),
                'Content-Type': 'application/json'
            },
            success: function(response) {
                console.log('Routine delete succesfully:', response);
                window.location.href = "routines.html";
                window.location.reload();
            },
            error: function(error) {
                console.error('Error deleting routine:', error);
            }
        });
    });
    $('#routineclosePopup').click(function() {
        $('.deletepopup').css('display', 'none');
    });
}
};
