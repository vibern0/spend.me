# Spend.Me
An Android application to crontrol my spends. I'm an university student. I dont have much money :s

###How this works
The application have 4 activities.
- Login window (choose one cloud service)
- Category or Spends window
- Manage categories window (popup to add / edit / remove)
- Manage spend window (popup to add / edit / remove)

The application save the data in one cloud service (Dropbox, OneDrive or GoogleDrive), choosen by the client.
When the client manage a category or a spend, the file containing the data, will be updated.

###Use the app
This app is not available on google play store. If you want to use, download the project and build it.

###Where the data goes?
Nowadays, it's good if we can have everything in the cloud. So, thinking about it, this app uses datastores from several cloud services to save the data. If at the time I want to add a new spend there is not connection to internet, the data is stored localy and then uploaded.
