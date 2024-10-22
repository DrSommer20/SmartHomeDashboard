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
                        </h3>
                            <div class="button-container">
                                 <button class="delete-button" id="device-delete-button`+uniqueId+`" ><span class="material-symbols-outlined">delete</span></button>
                                 <button class="edit-button" id="device-edit-button`+uniqueId+`" >Edit</button>
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

//Edit Room
let roomOldData = {};
function editbuttonclick(room_id) {
    $('.popup').css('display', 'flex');
    $(document).ready(function() {
        $.ajax({
            url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room/' + room_id,
            type: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authToken')
            },
            success: function(response) {
                $('#roomName').val(response.name);

                roomOldData = {
                    name: response.name,
                    room_id: response.room_id
                    
                };
                console.log('roomOldData', roomOldData);
            },
            error: function(error) {
                console.error('Fehler bei der Anfrage:', error);
            }
        });
        });
}

// Save button, Change function
$("#saveBtn").click(function() {
    const currentData = {
        name: $('#roomName').val(),
        };
        console.log('currentData', currentData);
    updateRoomIfDifferent('name', currentData.name, roomOldData.name, roomOldData.room_id);
});

function updateRoomIfDifferent(field, newValue, oldValue, room_id) {
    if (newValue !== oldValue) {
        updateFieldRoom(field, newValue, room_id);
    }
}

function updateFieldRoom(field, newValue, room_id) {
    const data = {
        "new-value": newValue,
        "field": field
    };
    console.log(data, 'was soll geputet werden');
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room/' + room_id,
        type: 'PUT',
        dataType: 'json',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            console.log(`Field ${field} successfully updated:`, response);
            window.location.reload();
            window.location.href = 'Rooms.html';
            
        },
        error: function(error) {
            console.error('Fehler beim Aktualisieren von ' + field + ':', error.status, error.statusText);

           
            
        },
    });
}

$('.popup-close').click(function() {
    $('.popup').css('display', 'none');
});
