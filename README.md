# Capstone Project: Enhancing Parking Spot Identification Accuracy Using Ultra-Wide Band Technology
Made by Vaishnav Santhosh & Gokul Dinesh

For details, read Report.pdf

# How to run
1. Run `python3 -m pip install -r requirements.txt` to install all dependencies (Use a virtual environment so that you don't mess up your normal python)
2. Run `flask run --host=0.0.0.0` and that should start up the flask server in your private network.
3. Run `python3 read_pos.py` so that the raspberry pi can read the tag's location.
4. Compile and run CapstoneApp using Android Studio in order to access the location (You have to input the ip address and flask port in the app along with the tag identifier.
