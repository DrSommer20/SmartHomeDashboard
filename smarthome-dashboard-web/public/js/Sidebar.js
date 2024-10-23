const sidebar = document.getElementById('sidebar');
const content = document.getElementById('main-content');

sidebar.addEventListener('mouseenter', () => {
    content.style.marginLeft = '265px';
});
sidebar.addEventListener('mouseleave', () => {
    content.style.marginLeft = '90px';
});

$("#logout-btn").click(function() {
    localStorage.clear()
    console.log('Logout erfolgreich');
    window.location.href = 'https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/public/login-page/login-mask.html';
});

$("#user-profile-btn").click(function() {
    location.href = 'https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/public/sub-pages/user-info.html';
});



