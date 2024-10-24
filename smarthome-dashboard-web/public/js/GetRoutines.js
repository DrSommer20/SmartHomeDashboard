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

           function handleCheckboxChange() {
               handleChange(this.checked, routine.id, uniqueId, this);
           }
            function handledeletebuttonclick(){
                deletebuttonclick(routine.id);
            }  
            deletebutton.addEventListener("click", handledeletebuttonclick);
           
           checkbox.addEventListener('change', handleCheckboxChange);
           index++;
           }
       );
   }
   function handleChange(isChecked, device_id, uniqueId, checkboxElement){
}


  


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
