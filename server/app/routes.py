from app import app,db
from flask import render_template, flash, redirect,url_for,request,jsonify
from flask_cors import CORS, cross_origin
import json
from app.models import User, Post

CORS(app)
@app.route("/info_time",methods=['GET'])
def info_time():
       
    return json.dumps({ "first_response": "Initial setup" })

@app.route("/create_blog",methods=['POST'])
def create_blog():
    blog=request.json["blog"]
    blog_writter=request.json["blog_writer"]
    print(" written by ",blog_writter," \n",blog)
    #insert a thread here
    u=User(username="{}@gamil.com".format(blog_writter),name=blog_writter)
    p=Post(author=u,post_content=blog)

    db.session.add(u)
    db.session.add(p)
    db.session.commit()
    
    
    
    return json.dumps({'response':'blog created'})

@app.route("/get_blog",methods=['GET'])
def get_blog():
    user=request.args.get('u')
    u_name=User.query.get(2)
    posts=u_name.posts.all()
    l={}
    for p in posts:
        l.update({u_name.name:p.post_content})
    print(l)
    return json.dumps(l)
