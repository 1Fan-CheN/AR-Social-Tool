# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:50:02
LastEditTime : 2022-03-20 22:42:57
Description  : 
'''

import yaml
import logging.config
import os

basedir = os.path.abspath(os.path.dirname(__file__))


def create_log(env):
    with open(os.path.join(basedir, './LogConfig.yaml'), 'r') as f:
        log_config = yaml.load(f.read(), Loader=yaml.FullLoader)
    log_config['handlers']['info_file_handler']['filename'] = os.path.join(basedir, 'info.log')
    log_config['handlers']['error_file_handler']['filename'] = os.path.join(basedir, 'error.log')
    logging.config.dictConfig(log_config)
    if env == 'production':
        return logging.getLogger(__name__)
    else:
        return logging.getLogger('DEBUG')

class DevelopmentConfig(object):
    DEBUG = True

class TestConfig(object):
    DEBUG = True

class ProductionConfig(object):
    DEBUG = False

config = {
    'development': DevelopmentConfig,
    'test': TestConfig,
    'production': ProductionConfig,
}