# Simple Text Editor (JavaFX)

A lightweight text editor built with **Java**, **JavaFX (FXML + CSS)**, and **Maven**.  
It supports opening/saving `.txt` files, a dark/light theme toggle, and safe prompts to prevent losing unsaved work.

> Built by **Mandip Amgain** (CSC-285-01 Project #1)

---

## ✨ Features

- **Open / Save / Save As…** for `.txt` files (UTF-8)
- **Unsaved changes (“dirty”) tracking** with a leading `*` in the window title
- **Exit protection**: prompts to save changes before exiting or opening another file
- **Dark Mode toggle** (View → Dark Mode) using JavaFX CSS stylesheets
- **Newline-preserving** file I/O (no collapsed lines)
- **Keyboard shortcuts** (cross-platform)
  - `Ctrl/Cmd + O` → Open
  - `Ctrl/Cmd + S` → Save
  - `Ctrl/Cmd + Shift + S` → Save As…
  - `Ctrl/Cmd + Q` → Exit

> Shortcuts are defined in FXML; adjust to taste in `startpage.fxml`.

---

## 🖼️ UI at a glance

- **Menu Bar**  
  - **File**: Open, Save, Save As…, Exit  
  - **View**: Dark Mode (checkable)
- **Editor**: A resizable `TextArea` (wrap enabled)

---

## 🧱 Tech Stack

- **JDK**: 11+ (tested with JDK 21)
- **JavaFX**: 19 (`javafx-controls`, `javafx-fxml`)
- **Maven**: `javafx-maven-plugin` for local runs

---

## 🚀 Getting Started

### Prerequisites
- JDK **11+** (recommend 17 or 21)
- Maven **3.8+**

## Verify:
```
java -version
mvn -v
```

## Clone
```
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```
Run (dev)
```
mvn clean javafx:run

```
## Build

This project is intended to run via the Maven JavaFX plugin during development.
If you want a standalone distribution, you can:

use jlink to create a runtime image with JavaFX modules, or

package and run with proper --module-path pointing to JavaFX libraries.

(Ask if you want me to add a jlink profile—happy to wire it.)

## 📁 Project Structure

```
src/
  main/
    java/
      edu/bhcc/mandip/
        App.java            # JavaFX application entry point
        Controller.java     # UI logic: open/save, theme, dirty state, exit prompts
    resources/
      edu/bhcc/mandip/
        startpage.fxml      # UI layout (MenuBar + TextArea)
        light.css           # Light theme
        dark.css            # Dark theme
pom.xml
```

## 🔑 Key Files
App.java

Loads startpage.fxml from the classpath

Applies the light theme by default

Passes Stage + Scene to the controller via Controller#init(...)

Example snippet:
```
URL fxml = App.class.getResource("/edu/bhcc/mandip/startpage.fxml");
FXMLLoader loader = new FXMLLoader(fxml);
Parent root = loader.load();
Scene scene = new Scene(root);
scene.getStylesheets().add(App.class.getResource("/edu/bhcc/mandip/light.css").toExternalForm());
Controller ctrl = loader.getController();
ctrl.init(primaryStage, scene);

``` 
**Controller.java**

Implements:

- Open/Save/Save As… using FileChooser

- Dirty tracking by listening to TextArea text changes

- Exit prompts via Alert (Yes / No / Cancel)

- Dark Mode toggle swapping the stylesheet between light.css and dark.css
(resilient path lookup; won’t crash if CSS is missing)

- Updates the window title to show * when there are unsaved edits.

**startpage.fxml**

- Declares the layout

- Binds menu items to controller handlers (e.g., onAction="#openfile")

**light.css / dark.css**

- Proper JavaFX selectors for TextArea, menus, and scene background

Example dark theme:
```
.root { -fx-background-color: #1e1e1e; -fx-text-fill: #dcdcdc; }
.menu-bar { -fx-background-color: #2d2d2d; }
.text-area { -fx-control-inner-background: #2d2d2d; -fx-text-inner-color: #dcdcdc; }
.text-area .content { -fx-background-color: #2d2d2d; }
```
## 🎹 Shortcuts (default)

| Action   | Windows/Linux    | macOS           |
| -------- | ---------------- | --------------- |
| Open     | Ctrl + O         | Cmd + O         |
| Save     | Ctrl + S         | Cmd + S         |
| Save As… | Ctrl + Shift + S | Cmd + Shift + S |
| Exit     | Ctrl + Q         | Cmd + Q         |

Configured in FXML (accelerator="Shortcut+O", etc.). “Shortcut” maps to Ctrl on Windows/Linux and Cmd on macOS.

## 🧰 Troubleshooting
**“Location is not set” / FXML not found**

- Ensure startpage.fxml lives under:
```
src/main/resources/edu/bhcc/mandip/startpage.fxml


```
- After building, it should exist at:

```
target/classes/edu/bhcc/mandip/startpage.fxml

```
- Dark Mode toggle does nothing or crashes

- Confirm light.css and dark.css are in resources (same package path as FXML), e.g.:
```
src/main/resources/edu/bhcc/mandip/dark.css
src/main/resources/edu/bhcc/mandip/light.css

```
- The controller tries both /edu/bhcc/mandip/<css> and /<css> and logs what it tried.

**macOS: “Timeout while waiting for app reactivation”88**

- This Glass warning is benign and unrelated to FXML/CSS issues.

## 🗺️ Roadmap (nice next features)

- Status bar (line/column, chars/words)

- Find / Replace (Ctrl/Cmd + F / H)

- Recent Files menu (persisted via Preferences)

- Zoom (Ctrl/Cmd + + / -)

- Autosave & restore

## 🤝 Contributing

- PRs and suggestions welcome:

- Fork the repo

- Create a feature branch

Open a PR with a clear description and screenshots/GIFs if UI changes

## 📄 License

Choose a license (e.g., MIT) and add a LICENSE file at the repo root.
