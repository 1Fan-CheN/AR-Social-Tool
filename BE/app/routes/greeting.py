# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:39:22
LastEditTime : 2022-01-27 03:23:43
Description  : 
'''

from flask import Blueprint, request
from app.models.user import User
from app import logger
from app import db
from common.basersp import BaseRsp
from common.const import ReturnCode

greetings = Blueprint('greetings', __name__)

@greetings.route('/user/create', endpoint='create_user')
def create_user():
    logger.info('Get create user request: %s' % str(request.json))
    if request.method == 'POST':
        nick_name = request.json['nick_name']
        gender = request.json['gender']
        avator = request.json['avator']
        try:
            new_user = User(nick_name=nick_name, gender=gender, avator=avator)
            db.session.add(new_user)
            db.session.commit()
            return BaseRsp.success_rsp(request.json)
            
        except Exception as e:
            logger.error('Create user wrong! error: %s' % str(e))
        except:
            logger.error('Create user wrong with unexcepted wrong!')
            e = 'unexcepted error'
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, e)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@greetings.route('/user/update_nickname', endpoint='update_nickname')
def update_nickname():
    pass

@greetings.route('/user/update_gender', endpoint='update_gender')
def update_gender():
    pass

@greetings.route('/user/update_avator', endpoint='update_avator')
def update_avator():
    pass