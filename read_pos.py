import serial
import time
import json
import redis

r = redis.Redis(host='localhost', port=6379, db=0)
DWM = serial.Serial(port="/dev/ttyACM1", baudrate=115200)
print("Connected to " + DWM.name)
DWM.write("\r\r".encode())
print("Encode")
time.sleep(1)
DWM.write("lec\r".encode())
print("Encode")
time.sleep(1)
try:
    while True:
        data = DWM.readline()
        if(data):
            data=data.decode()
            print(data)
            if("nan" not in data and "POS" in data):
                data = data.replace("\r\n","")
                data = data.split(",")
                pos = {"x": data[3], "y": data[4]}
                print(pos)
                pos = json.dumps(pos)
                r.set(data[2],pos)
    DWM.write("\r".encode())
    DWM.close()

except KeyboardInterrupt:
    print("Stop")
