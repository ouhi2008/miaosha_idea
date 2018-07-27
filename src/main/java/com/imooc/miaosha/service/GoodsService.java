package com.imooc.miaosha.service;

import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    public List<GoodsVo> listGoodsVo() {
        return new ArrayList<GoodsVo>();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
    return new GoodsVo();
    }
}
