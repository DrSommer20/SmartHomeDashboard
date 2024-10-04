$("#tasksubmit").click(function() {

    var taks = {

            name: $("#taskname").val(),

            description:$("#taskdescription").val(),

            priority:parseInt($("#taskpriority").val())

        };

    console.log(task);

    $.ajax({

        url: 'https://agile-wave-43715.herokuapp.com/api/v1.0/task',

        type: 'post',

        dataType: 'json',

        contentType: 'application/json',

        success: function (data) {

            alert(data);

        },

        data: JSON.stringify(task),

        processData: false,

        contentType: "application/json; charset=UTF-8",

        error: function (xhr, ajaxOptions, thrownError) {

            alert('Error: ' + xhr.status + '   ' + thrownError);

          }

    });

});
