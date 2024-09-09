
document.addEventListener("DOMContentLoaded", function() {
    let BooleanButton = true;
    const initialContent = document.getElementById("initialContent").innerHTML;
    const headButton = document.getElementById("HeadButton");

    headButton.addEventListener("click", function() {
        if (BooleanButton) {
            // Beim ersten Klick: Label ändern und Inhalt laden
            headButton.textContent = "Go Back";
            fetch("tasks/AddDeviceMask.html")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Netzwerkantwort war nicht ok");
                    }
                    return response.text();
                })
                .then(data => {
                    document.getElementById("content").innerHTML = data;
                    BooleanButton = false; 
                })
                .catch(error => {
                    console.error("Fehler beim Laden des Inhalts:", error);
                });
        } else {
            // Beim zweiten Klick: Inhalt zurücksetzen und Button zurücksetzen
            document.getElementById("content").innerHTML = initialContent;
            headButton.textContent = "Add Device";
            BooleanButton = true; // Zustand zurücksetzen
        }
    });
});
