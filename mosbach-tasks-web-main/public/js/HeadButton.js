
document.addEventListener("DOMContentLoaded", function() {
    let BooleanButton = true;
    const initialContent = document.getElementById("initialContent").innerHTML;
    const headButton = document.getElementById("HeadButton");

    headButton.addEventListener("click", function() {
        if (BooleanButton) {
            //Label und Inhalt laden
            headButton.textContent = "Go Back";
            fetch("tasks/AddDeviceMask.html")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("HTTP error"+response.status);
                    }
                    return response.text();
                })
                .then(data => {
                    document.getElementById("content").innerHTML = data;
                    BooleanButton = false; 
                })
                .catch(error => {
                    console.error("Error: ", error);
                });
        } else {
            //Inhalt und Button zurücksetzen
            document.getElementById("content").innerHTML = initialContent;
            headButton.textContent = "Add Device";
            BooleanButton = true;
        }
    });
});
