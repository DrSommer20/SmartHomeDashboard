document.addEventListener("DOMContentLoaded", function() {
    // Event Listener für den Button hinzufügen
    document.getElementById("AddDeviceButton").addEventListener("click", function() {
        // Specify the HTML file to load
        const htmlFile = "tasks/AddDeviceMask.html";
        
        fetch(htmlFile)
            .then(response => {
             if (!response.ok) {
                throw new Error("Netzwerkantwort war nicht ok");
             }
             return response.text();
            })
            .then(data => {
                document.getElementById("content").innerHTML = data;
            })
    });
});
