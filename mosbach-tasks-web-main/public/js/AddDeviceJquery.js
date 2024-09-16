	

$("#AddDeviceSubmit").click(function() {

    var AddNewDevice = {

        device:{
            name: $("#deviceName").val(),

            type:$("#deviceType").val(),

            location:$("#deviceLocation").val()

        },

           token: localStorage.getItem('authToken')

        };

    console.log(AddNewDevice);

    $.ajax({

        url: 'https://smarthomebackend-grumpy-squirrel-dr.apps.01.cf.eu01.stackit.cloud/api/device',
        type: 'post',

        dataType: 'json',

        contentType: 'application/json',

        success: function (data) {

            alert(data.messsage);

        },

        data: JSON.stringify(AddNewDevice),

        processData: false,

        contentType: "application/json; charset=UTF-8",

        error: function (xhr, ajaxOptions, thrownError) {

            alert('Error: ' + xhr.status + '   ' + thrownError);

          }

    });

});