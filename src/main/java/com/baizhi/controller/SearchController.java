package com.baizhi.controller;


import com.baizhi.entity.Searcher;
import com.baizhi.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @RequestMapping("/find")
    public String search(String str, HttpServletRequest request){

        String realPath = request.getSession().getServletContext().getRealPath("/");
        String parent = new File(realPath).getParent();

        List<Searcher> searchers = searchService.searchParse(str,parent);
        request.setAttribute("products",searchers);
        return "/back/showpro";
    }

}
