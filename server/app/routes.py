from app import app,db
from flask import render_template, flash, redirect,url_for,request,jsonify
from flask_cors import CORS, cross_origin
import json
from app.models import User, Post
from sqlalchemy.exc import IntegrityError
CORS(app)
@app.route("/info_time",methods=['GET'])
def info_time():
       
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
    
    u=User(username=name.format("@gamil.com"),name=name)

    db.session.add(u)
    db.session.commit()
  except IntegrityError:
    return json.dumps({'response':'user already exists'})
    
    
  return json.dumps({'response':'new user created'})

@app.route("/get_blog_self",methods=['GET'])
def get_blog_self():
    user_n=request.args.get('u')
    print(User.query.all())
    u_name=User.query.filter_by(name=user_n).all()
    if u_name == []:
        return json.dumps({"error":"code"})
    print(u_name[0])
    l=[]
    posts=u_name[0].posts.all()
    for p in posts:
        l.append(p.post_content)
    k={}
    k.update({"name":u_name[0].name})
    k.update({"content":l})
    return json.dumps(k)

@app.route("/deleteAll",methods=['GET'])
def deleteAll():
    auth_code=request.args.get('a')
    print(type(auth_code))
    print(auth_code=="alpha")
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
    