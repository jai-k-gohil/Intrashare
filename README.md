# Intrashare
Intrashare is java application which enables users to transfer documents and media files between client and server within same network.

### Setting up IDE to use JavaFX Scene Builder
---
1. Once you open the IDE, on the bottom left of the pane click on `Configure > Preferences`.
2. On the left side of the pane, search for `Languages & Frameworks > JavaFX`, this will open JavaFX setting on the right pane.
3. In the blank space type in `/Applications/JavaFX Scene Builder 2.0.app` or use `...` to browse to the application.
4. Once done click on Ok.

### Fonts Used
---

Courtesy of
* [Museo_Slab.otf
* Museo_Slab_italic.otf

### Structure
---
<pre>
.
├── LICENSE
├── README.md
+── src
│   +── main
│   │   ├── Client.java
│   │   ├── ClientDemo.java
│   │   ├── ClientServices.java
│   │   ├── Commands.java
│   │   ├── CustomSettings.java
│   │   ├── DatabaseManager.java
│   │   ├── FileManager.java
│   │   ├── Server.java
│   │   ├── ServerConnectionObject.java
│   │   ├── ServerDemo.java
│   │   ├── ServerUploadFile.java
│   │   └── ThreadPool.java
│   +── view
│       ├── ClientView.java
│       ├── ClientViewController.java
│       ├── Dashboard.java
│       ├── FXUtilities.java
│       ├── IntroView.java
│       ├── LoginForm.java
│       ├── LoginFormController.java
│       ├── LoginView.java
│       ├── Scenes.java
│       ├── ServerView.java
│       ├── SignupForm.java
│       ├── SignupFormController.java
│       ├── SlideOut.java
│       ├── UserInterface.java
│       ├── UserListView.java
│       +── controls
│       │   ├── CustomCloseButton.java
│       │   ├── CustomDialog.java
│       │   ├── CustomHBoxCell.java
│       │   ├── CustomListView.java
│       │   ├── FileListView.java
│       │   └── FileListViewController.java
│       +── resources
│           ├── Application.css
│           ├── Museo_Slab_italic.otf
│           +── images
│           │   ├── cloudserver.png
│           │   ├── database-monitor.png
│           │   ├── downloadedfiles.png
│           │   ├── error.png
│           │   ├── folder-icon.png
│           │   ├── monitor-documents.png
│           │   ├── monitor-processing.png
│           │   ├── userlogin.png
│           │   └── usersignup.png
│           +── museo_slab.otf
├── test
</pre>

