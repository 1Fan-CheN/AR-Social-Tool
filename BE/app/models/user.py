# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 18:08:36
LastEditTime : 2022-03-20 22:16:37
Description  : 
'''

from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import Column, Integer, String, SmallInteger
from app import db


class User(db.Model):
    uid = db.Column(db.Integer(), primary_key=True, unique=True, nullable=False)
    username = db.Column(db.String(64), unique=True) 
    passwd = db.Column(db.String(128))
    gender = db.Column(db.SmallInteger())
    avatar = db.Column(db.String(128), nullable=True) 

    __table_name__ = 'user'