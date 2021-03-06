from flask import Flask
from flask_cors import CORS
from app.config import Config
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_httpauth import HTTPBasicAuth,HTTPTokenAuth

app=Flask(__name__)
CORS(app)
app.config.from_object(Config)
db = SQLAlchemy(app)
migrate = Migrate(app, db)
auth=HTTPBasicAuth()
auth_token=HTTPTokenAuth(scheme="TOKEN")


from app import routes,models
