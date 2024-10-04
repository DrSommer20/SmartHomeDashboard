$.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/auth/validate-token',
        type: 'POST',
        headers: {
                    'Authorization': localStorage.getItem('authToken')
                },
        success: function (data) {
            console.log('Token validated')
        },
        contentType: "application/json; charset=UTF-8",
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('Error: ' + xhr.status + '   ' + thrownError);
            window.location.href = 'https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/public/login-page/login-mask.html';
        }
    });