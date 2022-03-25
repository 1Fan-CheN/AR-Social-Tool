# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:47:48
LastEditTime : 2022-03-21 00:25:30
Description  : 
'''

from flask import Flask
from config import *
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()
logger = create_log('test')

def create_app(env):
    from app import models, routes
    app = Flask(__name__)
    app.config.from_object(config[env])
    models.init_app(app, env)
    routes.init_app(app)
    db.init_app(app)
    from .models.greetings import Greeting
    from .models.user import User
    db.create_all()
    db.session.commit()
    return app
