import serial
import time
import datetime
import redis
from flask import Flask

r = redis.Redis(host='localhost', port=6379, db=0)

app = Flask(__name__)

@app.route("/get_pos/<string:tag>")
def index(tag):
    pos_data = r.get(tag)
    return pos_data
