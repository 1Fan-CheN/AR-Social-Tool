# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-27 03:25:12
LastEditTime : 2022-03-20 22:16:31
Description  : 
'''

from flask_sqlalchemy import SQLAlchemy
from app import db


class Greeting(db.Model):
    id = db.Column(db.Integer(), primary_key=True, unique=True, nullable=False)
    messageID = db.Column(db.Integer())
    uid = db.Column(db.Integer())
    username = db.Column(db.String(64), unique=False) 
    animationID = db.Column(db.Integer())
    postcode = db.Column(db.String(64))

    __table_name__ = 'greeting'