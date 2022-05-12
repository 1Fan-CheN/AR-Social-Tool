# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:39:22
LastEditTime : 2022-03-28 23:05:22
Description  : 
'''
import datetime

from app import db, logger
from app.models.greetings import Greeting
from app.models.user import User
from common.basersp import BaseRsp
from common.const import ReturnCode
from flask import Blueprint, request

greetings = Blueprint('greetings', __name__)

@greetings.route('/greeting/get_list', methods=["get", "post"], endpoint='get_list')
def get_greeting_list():
    logger.info('Get quest for getting greeting list: %s' % str(request.json))
    if request.method == 'POST':
        try:
            postcode = request.json.get('postcode', '')
            page = int(request.json.get('page', -1))
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if postcode != '' and page != -1:
            
            try:
                if page != 0:
                    greeting_list = db.session.query(Greeting).filter(Greeting.postcode==postcode).order_by(Greeting.create_time.desc()).limit(100).slice((page-1) * 10, page * 10).all()
                else: 
                    greeting_list = db.session.query(Greeting).filter(Greeting.postcode==postcode).order_by(Greeting.create_time.desc()).all()
                result = []
                for greeting in greeting_list:
                    greeting_json = {}
                    greeting_json['messageID'] = greeting.messageID
                    greeting_json['animationID'] = greeting.animationID
                    greeting_json['create_time'] = greeting.create_time
                    uid = greeting.uid
                    user_info = db.session.query(User).filter(User.uid==uid).first()
                    if user_info is None:
                        continue
                    greeting_json['username'] = user_info.username
                    greeting_json['avatar'] = user_info.avatar
                    greeting_json['gender'] = user_info.gender
                    result.append(greeting_json)
                return BaseRsp.success_rsp(result)
            except Exception as e:
                logger.error('Get greeting list wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Get greeting list with unexcepted wrong!')
                err_msg = 'unexcepted error'
            code = ReturnCode.GETMethodError.value
            return BaseRsp.err_rsp(code, err_msg)
        else:
            err_msg = 'Posscode or page is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyPostcode.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@greetings.route('/greeting/send', methods=["get", "post"], endpoint='update_nickname')
def send():
    logger.info('Get create user request: %s' % str(request.json))
    if request.method == 'POST':
        try:
            postcode = request.json.get('postcode', '')
            messageID = request.json.get('messageID', -1)
            uid = request.json.get('uid', -1)
            animationID = request.json.get('animationID', -1)
            create_time = datetime.datetime.utcnow()
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if postcode != '' and messageID != -1 and uid != -1 and animationID != -1:
            try:
                new_greeting = Greeting(
                    postcode=postcode, 
                    messageID=messageID, 
                    uid=uid, 
                    animationID=animationID, 
                    create_time=create_time
                    )
                db.session.add(new_greeting)
                db.session.flush()
                new_id = new_greeting.id
                logger.info("Create new greeting succeed, request data: %s, id: %s", str(request.json), str(uid))
                return BaseRsp.success_rsp({"new_id": new_id})
                
            except Exception as e:
                logger.error('Create user wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Create user wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            code = ReturnCode.SendGreetingError.value
            return BaseRsp.err_rsp(code, err_msg)
        else:
            err_msg = 'Posscode is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyPostcode.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)
