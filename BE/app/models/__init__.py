# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-23 22:36:53
LastEditTime : 2022-03-25 11:52:16
Description  : 
'''

from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)


class DBConfig(object):
    # SQLALCHEMY_DATABASE_URL = 'mysql+pymysql://root:Shenqi0014!@localhost:3306/DurhamDB'
    HOST = '127.0.0.1'
    PORT = '3306'
    DATABASE = 'argreeting'
    USERNAME = 'durham'
    PASSWORD = 'tmp1123_'

    DB_URI = "mysql+pymysql://{username}:{password}@{host}:{port}/{db}?charset=utf8".format(username=USERNAME,password=PASSWORD, host=HOST,port=PORT, db=DATABASE)

    SQLALCHEMY_DATABASE_URI = DB_URI
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SQLALCHEMY_ECHO = True
    SQLALCHEMY_COMMIT_ON_TEARDOWN = True


class TestDBConfig(object):
    HOST = '127.0.0.1'
    PORT = '3306'
    DATABASE = 'argreeting'
    USERNAME = 'durham'
    PASSWORD = 'tmp1123_'

    DB_URI = "mysql+pymysql://{username}:{password}@{host}:{port}/{db}?charset=utf8".format(username=USERNAME,password=PASSWORD, host=HOST,port=PORT, db=DATABASE)

    SQLALCHEMY_DATABASE_URI = DB_URI
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SQLALCHEMY_ECHO = True
    SQLALCHEMY_COMMIT_ON_TEARDOWN = True

def init_app(app, env):
    if env == 'test':
        app.config.from_object(TestDBConfig)
    else:
        app.config.from_object(DBConfig)
