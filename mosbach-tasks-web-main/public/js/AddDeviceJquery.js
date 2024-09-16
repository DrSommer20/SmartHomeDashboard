	

$("#AddDeviceSubmit").click(function() {

    var AddNewDevice = {

            device_id: $("#deviceID").val(),

            name: $("#deviceName").val(),

            type:$("#deviceType").val(),

            location:$("#deviceLocation").val()

        };

    console.log(AddNewDevice);

    $.ajax({

        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'post',

        dataType: 'json',

        headers: {
                    'Authorization': localStorage.getItem('authToken')
                },

        contentType: 'application/json',

        success: function (data) {
            location.href='../homepage.html';
        },

        data: JSON.stringify(AddNewDevice),

        processData: false,

        contentType: "application/json; charset=UTF-8",

        error: function (xhr, ajaxOptions, thrownError) {

            alert('Error: ' + xhr.status + '   ' + thrownError);

          }

    });

});