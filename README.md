# Aplication Ui Converter Py
The application consists of converting the .ui file (generated by python Pyqt) into a .py file. The need arose when you had a slowdown at the time of making the front-end connection with the back end, using the GUI library, Pyqt in phyton, since you needed to do the conversion from the windows command prompt

#######################################################

Comments:

  - You need to be aware of the installation location of python, for the application to find the file python.exe, which is where it contains the functions to perform the .ui conversion
  - In the java code, the request searches the Windows AppData folder with the System.getenv ("APPDATA") method
  - An executable version is inside the project's dist folder
  
EX: C:\Users\felipe\AppData\Local\Programs\Python\Python36-32\python.exe
