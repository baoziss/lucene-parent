package com.baizhi.service;


import com.baizhi.entity.Searcher;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2017/6/25.
 */
@Service
public interface SearchService {

    List<Searcher> searchParse(String expression,String path);


}
