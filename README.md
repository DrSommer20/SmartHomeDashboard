# SmartHomeDashboard
Projekt für Web Programmierung DHBW Mosbach
[UNIVERSITY]

## SmartHomeDashboard

SmartHomeDashboard ist ein Projekt, das im Rahmen des Web-Programmierungskurses an der DHBW Mosbach entwickelt wurde. Ziel des Projekts ist es, eine benutzerfreundliche Oberfläche zur Steuerung und Überwachung von Smart-Home-Geräten zu schaffen.

### Features

- **Gerätesteuerung**: Einfache Steuerung von Smart-Home-Geräten wie Lichtern, Thermostaten und Sicherheitssystemen.
- **Echtzeitüberwachung**: Anzeige von Statusinformationen und Sensorwerten in Echtzeit.
- **Benutzerverwaltung**: Unterstützung für mehrere Benutzer mit unterschiedlichen Berechtigungsstufen.
- **Responsive Design**: Optimiert für die Nutzung auf verschiedenen Geräten, einschließlich Smartphones und Tablets.

### Installation

1. Klone das Repository:
    ```bash
    git clone https://github.com/username/SmartHomeDashboard.git
    ```
2. Navigiere in das Projektverzeichnis:
    ```bash
    cd SmartHomeDashboard
    ```
3. Installiere die Abhängigkeiten:
    ```bash
    npm install
    ```
4. Starte die Anwendung:
    ```bash
    npm start
    ```

### Nutzung

Nach der Installation und dem Start der Anwendung kann die SmartHomeDashboard-Oberfläche über einen Webbrowser unter `http://localhost:3000` aufgerufen werden.

### API Beschreibung

Die SmartHomeDashboard API ermöglicht die Interaktion mit den Smart-Home-Geräten und bietet verschiedene Endpunkte zur Steuerung und Überwachung. 

#### Basis-URL

```
http://localhost:3000/api
```

#### Endpunkte

- **GET /devices**: Gibt eine Liste aller verbundenen Smart-Home-Geräte zurück.
- **POST /devices/:id/on**: Schaltet das Gerät mit der angegebenen ID ein.
- **POST /devices/:id/off**: Schaltet das Gerät mit der angegebenen ID aus.
- **GET /devices/:id/status**: Gibt den aktuellen Status des Geräts mit der angegebenen ID zurück.
- **POST /users**: Erstellt einen neuen Benutzer.
- **GET /users/:id**: Gibt Informationen über den Benutzer mit der angegebenen ID zurück.

### Mitwirkende

- **Tim Sommer** - Entwickler Backend
- **Paula Bauer** - Entwicklerin Frontend/Designerin
- **Chris David Kaufmann** - Entwickler Frontend/Designer

### Lizenz

Dieses Projekt steht unter der APACHE2.0-Lizenz. Weitere Informationen findest du in der [LICENSE](LICENSE) Datei.