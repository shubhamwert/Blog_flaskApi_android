from app import db
from datetime import datetime

class User(db.Model):
    id=db.Column(db.Integer,primary_key=True)
    username=db.Column(db.String(10),index=True,unique=True)
    name=db.Column(db.String(10),index=True,unique=True)
    posts = db.relationship('Post', backref='author', lazy='dynamic')
    def __repr__(self):
        return '<User {}>'.format(self.username)

    



class Post(db.Model):
    postIdconda = db.Column(db.Integer,primary_key=True)

    writer_name=db.Column(db.String(10),db.ForeignKey('user.name'))
    timestamp = db.Column(db.DateTime, index=True, default=datetime.utcnow)
    post_content=db.Column(db.String(100),index=True,unique=False)
    def __repr__(self):
        return '<User {} written by {}>'.format(self.post_content,self.writer_name)
   

