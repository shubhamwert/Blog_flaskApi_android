from app import app,db,auth
from flask import render_template, flash, redirect,url_for,request,jsonify,g
from flask import g,abort
from flask_cors import CORS, cross_origin
import json
from app.models import User, Post
from sqlalchemy.exc import IntegrityError
import datetime


CORS(app)

tokens={}

@auth.verify_password
def verify_password(username, password):
    u_name=User.query.filter_by(name=username).first()
    if u_name is not None:
        print(u_name)
        g.current_user=u_name
        return u_name.verify_password(password)


    return False

@app.route("/sign_up")
@auth.login_required
def signUp():
        print(auth.username(),auth)
        return json.dumps({
            "name":auth.username(),
            "response":True
        })

@app.route("/info_time",methods=['GET'])
@auth.login_required
def info_time():
    print("Hello, %s!" % g.current_user)
    return json.dumps({ "first_response": "Initial setup" })

@app.route("/create_blog",methods=['POST'])
def create_blog():
    blog=request.json["blog"]
    blog_writter=request.json["blog_writter"]
    print(" written by ",blog_writter," \n",blog)
    #insert a thread here
    u=User.query.filter_by(name=blog_writter).first()
    p=Post(author=u,post_content=blog)

    # db.session.add(u)
    db.session.add(p)
    db.session.commit()
    
    
    
    return json.dumps({'response':'blog created'})

@app.route("/create_newuser",methods=['POST'])
def create_newuser():
  try:
    name=request.json["name"]
    password=request.json["password"]   
    u=User(username=name.format("@gamil.com"),name=name)
    u.hash_password(password)
    db.session.add(u)
    db.session.commit()
    return json.dumps({'response':'new user created'})
  except IntegrityError:
    return json.dumps({'response':'user already exists'})

    
    
  

@app.route("/get_blog_self",methods=['GET'])
@auth.login_required
def get_blog_self():
    user_n=request.args.get('u')
    print("you seeing this ",auth.username())
    u_name=User.query.filter_by(name=auth.username()).all()
    if u_name == []:
        return json.dumps({"response":"error"})
    l=["fall back"]
    posts=u_name[0].posts.all()
    for p in posts:
        l.append(p.post_content)
    k={"response":"success"}
    k.update({"name":u_name[0].name})
    k.update({"content":l})
    print(json.dumps(k))
    return json.dumps(k)

@app.route("/deleteAll",methods=['GET'])
def deleteAll():
    auth_code=request.args.get('a')
    if auth_code=="alpha":
        users=User.query.all()
        print(users)

        for u in users:
         db.session.delete(u)
    
        posts=Post.query.all()
        for p in posts:
            db.session.delete(p)
    
        db.session.commit()
        return json.dumps({"response" : "success"})
    return json.dumps({"response" : "failure"})






    