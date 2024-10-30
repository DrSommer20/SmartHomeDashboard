
// Add Room
$("#AddRoomSubmit").click(function() {
    var AddNewRoom = {
        name: $("#roomName").val(),
    };
    console.log(AddNewRoom);
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room',
        type: 'POST',
        dataType: 'json',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        contentType: 'application/json',
        success: function(data) {
            location.href = 'Rooms.html';

        },
        data: JSON.stringify(AddNewRoom),
        processData: false,
        error: function(xhr, ajaxOptions, thrownError) {
            alert('Error: ' + xhr.status + '   ' + thrownError);
        }
    });
});

