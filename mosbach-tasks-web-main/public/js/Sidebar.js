const sidebar = document.getElementById('sidebar');
const content = document.getElementById('main-content');

sidebar.addEventListener('mouseenter', () => {
    content.style.marginLeft = '265px';
});
sidebar.addEventListener('mouseleave', () => {
    content.style.marginLeft = '90px';
});

$("#logout-btn").click(function() {
    $.ajax({
        url: 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/auth',
        type: 'DELETE',
        headers: {
            'Authorization': localStorage.getItem('authToken')
        },
        success: function(response) {
            localStorage.removeItem('authToken');
            alert('Logout successful');
            window.location.href = 'https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/public/login-page/login-mask.html';
        },
        error: function(error) {
            console.error('Error at Logout:', error);
        }
    });
});

$("#user-profile-btn").click(function() {
    location.href = 'https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/public/sub-pages/user-info.html';
});



