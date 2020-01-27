from app import db,auth
from datetime import datetime
from passlib.apps import custom_app_context as pwd_context
class User(db.Model):
    __tablename__='user'
    id=db.Column(db.Integer,primary_key=True)
    username=db.Column(db.String(10),index=True,unique=True)
    name=db.Column(db.String(10),index=True,unique=True)
    posts = db.relationship('Post', backref='author', lazy='dynamic')
    password_hash=db.Column(db.String(128),nullable=False)
    
    def __repr__(self):
        return '<User {}>'.format(self.username)
   
    def hash_password(self,password):
        self.password_hash=pwd_context.encrypt(password)

    def verify_password(self,password):
        return pwd_context.verify(password,self.password_hash)



class Post(db.Model):
    postIdconda = db.Column(db.Integer,primary_key=True)

    writer_name=db.Column(db.String(10),db.ForeignKey('user.name'))
    timestamp = db.Column(db.DateTime, index=True, default=datetime.utcnow)
    post_content=db.Column(db.String(100),index=True,unique=False)
    def __repr__(self):
        return '<User {} written by {}>'.format(self.post_content,self.writer_name)
   

