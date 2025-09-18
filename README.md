<p align="center">
  <img src="https://img.shields.io/badge/Simple%20Text%20Editor-JavaFX%20App-blueviolet?style=for-the-badge&logo=java&logoColor=white" alt="Simple Text Editor Banner"/>
</p>

<h1 align="center" style="font-family: 'Fira Code', 'JetBrains Mono', 'Source Code Pro', monospace; font-size: 3rem; color: #3CC2F7; letter-spacing: 2px;">📝 Simple Text Editor</h1>
<p align="center" style="font-family: 'Segoe UI', 'Roboto', 'Arial', sans-serif; color: #8383e2; font-size: 1.2rem;">
  <b>A clean, modern JavaFX text editor with dark mode, keyboard shortcuts, and safety features.</b><br>
  <i>by Mandip Amgain</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-11%2B-green?style=flat-square&logo=java">
  <img src="https://img.shields.io/badge/JavaFX-19-blue?style=flat-square&logo=openjdk">
  <img src="https://img.shields.io/badge/Maven-3.8%2B-yellow?style=flat-square&logo=apachemaven">
</p>

---

## 🎬 Preview

<!-- Replace the link below with your app demo video/GIF -->
<p align="center">
  <img src="YOUR_VIDEO_OR_GIF_LINK_HERE" alt="App Demo" width="600" style="border-radius: 16px; box-shadow: 0 8px 24px #0003; animation: fadeIn 1.2s;"/>
</p>

---

## ✨ Features

<div align="center">

| 🌟 | Feature |
|---|---------|
| 💾 | Open, Save, Save As… for `.txt` files (UTF-8) |
| 🟢 | Unsaved changes tracking with `*` in title |
| ��️ | Exit protection (save prompts) |
| 🌑 | Dark/Light Mode toggle |
| 🔄 | Keyboard shortcuts (cross-platform) |
| 📝 | Newline-preserving file I/O |
| ⌨️ | Customizable shortcuts in FXML |

</div>

---

## 🖼️ UI Snapshots

<details>
  <summary>Show UI</summary>

  <!-- Insert screenshots here if available -->
  <p align="center">
    <img src="https://raw.githubusercontent.com/Mandip77/Simple-TextEditor-Java/main/screenshot-light.png" width="45%" alt="Light Mode" style="border-radius: 8px; box-shadow: 0 4px 18px #0002; margin: 0 12px; animation: slideIn 1.2s;"/>
    <img src="https://raw.githubusercontent.com/Mandip77/Simple-TextEditor-Java/main/screenshot-dark.png" width="45%" alt="Dark Mode" style="border-radius: 8px; box-shadow: 0 4px 18px #0002; margin: 0 12px; animation: slideIn 1.2s;"/>
  </p>
</details>

---

## 🧑‍💻 Tech Stack

```yaml
Java           : 11+ (tested with 21)
JavaFX         : 19 (controls, FXML)
Maven          : javafx-maven-plugin
Build Tooling  : Maven 3.8+
```

---

## 🚀 Quick Start

```bash
git clone https://github.com/Mandip77/Simple-TextEditor-Java.git
cd Simple-TextEditor-Java
mvn clean javafx:run
```

Requires: Java 11+ & Maven 3.8+

---

## 🗂️ Project Structure

<details>
  <summary>Expand for details</summary>

```
src/
  main/
    java/
      edu/bhcc/mandip/
        App.java
        Controller.java
    resources/
      edu/bhcc/mandip/
        startpage.fxml
        light.css
        dark.css
pom.xml
```
</details>

---

## 🎹 Keyboard Shortcuts

| Action   | Windows/Linux    | macOS           |
| -------- | ---------------- | --------------- |
| Open     | Ctrl + O         | Cmd + O         |
| Save     | Ctrl + S         | Cmd + S         |
| Save As… | Ctrl + Shift + S | Cmd + Shift + S |
| Exit     | Ctrl + Q         | Cmd + Q         |

---

## 🛠️ Troubleshooting

<details>
  <summary>Expand for common issues</summary>

- FXML/CSS not found? Ensure `startpage.fxml`, `light.css`, and `dark.css` are in `src/main/resources/edu/bhcc/mandip/`.
- Dark mode toggle not working? Confirm CSS files exist as above.
- macOS "Timeout" warnings: benign; ignore.
</details>

---

## 🗺️ Roadmap

- Status bar (line/column, chars/words)
- Find / Replace
- Recent Files menu
- Zoom in/out
- Autosave & restore

---

## 🤝 Contributing

PRs and suggestions welcome!  
Fork, branch, and open a PR with description and screenshots/GIFs if UI changes.

---

## 📄 License

MIT (proposed). Please add a LICENSE file.

---

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&pause=1000&color=36BCF7&center=true&vCenter=true&width=400&lines=Happy+Editing!+%F0%9F%91%8B" alt="Animated typing effect"/>
</p>

<!--
Advanced effects/modern fonts are referenced with inline style tags, but GitHub-flavored Markdown only partially supports these. For the boldest look, consider using custom shields, SVGs, and animated GIFs as shown, and keep screenshots and video up-to-date for maximum visual punch!
-->