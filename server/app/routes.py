from app import app,db,auth,auth_token
from flask import render_template, flash, redirect,url_for,request,jsonify,g
from flask import g,abort
from flask_cors import CORS, cross_origin
import json
from app.models import User, Post
from sqlalchemy.exc import IntegrityError
import datetime
import Crypto.Hash.SHA224 as h
import time
CORS(app)

tokens={}

def get_key(val): 
    for key, value in tokens.items(): 
         if val == value: 
             return key 
  
    return None

@auth.verify_password
def verify_password(username_or_token, password):
    print(tokens)
    print(username_or_token in tokens)
    if username_or_token in tokens:
        g.current_user=tokens[username_or_token]
        
        return True
    if get_key(username_or_token):
        del(tokens[get_key(username_or_token)])

    username=username_or_token

    u_name=User.query.filter_by(name=username).first()
    if u_name is not None:
        print(u_name)
        g.current_user=u_name
        a=h.new()
        m=""+u_name.name+str( time.asctime( time.localtime(time.time()) ))
        a.update(m.encode('utf-8'))
        p=str(a.hexdigest())
        tokens.update({
            p:u_name.name
        })
        
        print("current TOkens are ")
        print(tokens)
        return u_name.verify_password(password)


    return False
@auth_token.verify_token
def verify_token(token):
        print("veryfying token "+str(token))
        if token in tokens:
            g.current_user=tokens[token]
            return True
        return False

@app.route("/sign_up")
@auth.login_required
def signUp():
        print(g.current_user)
        return json.dumps({
            "name":g.current_user.name,
            "response":True,
            "token_id":str(get_key(g.current_user.name))
        })

@app.route("/info_time",methods=['GET'])
@auth.login_required
def info_time():
    print("Hello, %s!" % g.current_user)
    return json.dumps({ "response":True,"description": str(datetime.datetime.utcnow()) })

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
    
    
    
    return json.dumps({'response':True})

@app.route("/create_newuser",methods=['POST'])
def create_newuser():
  try:
    name=request.json["name"]
    password=request.json["password"]   
    u=User(username=name.format("@gamil.com"),name=name)
    u.hash_password(password)
    db.session.add(u)
    db.session.commit()
    return json.dumps({'response':True})
  except IntegrityError:
    return json.dumps({'response':False, "description":"user already exists"})

    
    
  
@app.route("/get_blog_self",methods=['GET'])
@auth.login_required
def get_blog_self():
    user_n=request.args.get('u')
    print(request)
    print("you seeing this ",auth.username())
    if auth.username() in tokens:
        u_name=User.query.filter_by(name=tokens[auth.username()]).all()
    else:
        u_name=User.query.filter_by(name=auth.username()).all()
    if u_name == []:
        return json.dumps({"response":False,"description":"User not found"})
    l=["welcome to blog : ".format(u_name.name)]
    posts=u_name[0].posts.all()
    for p in posts:
        l.append(p.post_content)
    k={"response":True}
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
        return json.dumps({"response" : True})
    return json.dumps({"response" : False})






    