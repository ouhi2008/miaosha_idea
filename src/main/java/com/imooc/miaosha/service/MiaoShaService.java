package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoShaUserDao;
import com.imooc.miaosha.domain.MiaoShaUser;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaService {
    @Autowired
    MiaoShaUserDao miaoShaUserDao;

    public MiaoShaUser getById(long id){
        return miaoShaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if(loginVo == null){
          return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        MiaoShaUser user = getById(Long.parseLong(mobile));
        if(user == null ){
            return CodeMsg.MOBILE_NOT_EXIST;
        }

        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass,saltDB);
        if(!calcPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }

        return CodeMsg.SUCCESS;
    }
}
