$(document).ready(function() {
    updateRoutines();
    setInterval(updateRoutines, 30000);
});

function updateRoutines(){
    $.ajax({
           url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine',
           type: 'GET',
           headers: {
               'Authorization': localStorage.getItem('authToken')
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

           function handleCheckboxChange() {
               handleChange(this.checked, routine.id, uniqueId, this);
           }

           checkbox.addEventListener('change', handleCheckboxChange);
           index++;
           }
       );
   }
}


   function handleChange(isChecked, device_id, uniqueId, checkboxElement){
           
   }
