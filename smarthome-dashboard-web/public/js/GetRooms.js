//display rooms
$(document).ready(function() {
    updateRooms();
    setInterval(updateRooms, 30000);
});
function updateRooms(){
    $.ajax({
           url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room',
           type: 'GET',
           headers: {
               'Authorization': localStorage.getItem('authToken')
           },
           success: function(response) {
               console.log('Erfolgreiche Antwort get Room:', response);
               displayRooms(response.rooms);
           },
           error: function(error) {
               console.error('Fehler bei der Anfrage:', error);
           }
       });
}
function displayRooms(rooms) {
  const contentDiv = document.getElementById("content");
  var index = 1;
  contentDiv.innerHTML = "";
  if (rooms !== undefined) {
    rooms.forEach((room) => {
        const uniqueId = 'toggleCheckbox' + index;
      const roomDiv = document.createElement("div");
      roomDiv.classList.add("col-sm-12", "col-md-12", "col-lg-6", "col-xl-4");
      //TODO: Edit Button auf Dashboard verstecken
      roomDiv.innerHTML =`
                 <div class="display-card">
                     <div class="display-card-header">
                       <h3>
                <span></span> ${room.name}
                <button class="edit-button" id="room-edit-button `+ uniqueId +`" >Edit</button>
            </h3>
        </div>
        <div class="card-separator"></div>
        <div class="device-info">
            <p>ID: ${room.room_id}</p>
             <input type="checkbox" id="` + uniqueId +`" class="toggleCheckbox" />
        <label for="` + uniqueId + `" class="toggleContainer">
        <div>OFF</div>
        <div>ON</div>
         </label>
            </div>
        </div>
            `;
      contentDiv.appendChild(roomDiv);
      // Set checkbox to checked if the device state is "On"
      const checkbox = document.getElementById(uniqueId);
      const editbutton = document.getElementById(
        "room-edit-button" + uniqueId
      );
      if (room.state === "On") {
        checkbox.checked = true;
      }

      function handleCheckboxChange() {
        handleChange(this.checked, room.room_id, uniqueId, this);
      }

      function handleeditbuttonclick() {
        editbuttonclick(room.room_id);
      }
      editbutton.addEventListener("click", handleeditbuttonclick);

      checkbox.addEventListener("change", handleCheckboxChange);
      index++;
    });
  }
}
