from app import app
from flask import render_template, flash, redirect,url_for,request,jsonify
from flask_cors import CORS, cross_origin
import json

@app.route("/info_time",methods=['GET'])
def info_time():
       
    return json.dumps({ "first_response": "Initial setup" })
