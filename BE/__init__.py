# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-23 22:33:34
LastEditTime : 2022-01-26 19:21:43
Description  : 
'''

from flask import Flask

def create_app():
    from . import models, routes
    app = Flask(__name__)
    models.init_app(app)
    routes.init_app(app)
    # services.init_app(app)
    return app