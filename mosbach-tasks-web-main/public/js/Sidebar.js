const sidebar = document.getElementById('sidebar');
const content = document.getElementById('main-content');

sidebar.addEventListener('mouseenter', () => {
    content.style.marginLeft = '265px';
});
sidebar.addEventListener('mouseleave', () => {
    content.style.marginLeft = '90px';
});



