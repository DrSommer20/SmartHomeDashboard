const sidebar = document.getElementById('sidebar');
const content = document.getElementById('main-pane');

sidebar.addEventListener('mouseenter', () => {
    content.style.marginLeft = '265px';
});
sidebar.addEventListener('mouseleave', () => {
    content.style.marginLeft = '90px';
});



