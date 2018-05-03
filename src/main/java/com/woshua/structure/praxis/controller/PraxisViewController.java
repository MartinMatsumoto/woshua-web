package com.woshua.structure.praxis.controller;

import com.woshua.core.enums.CombinePaperType;
import com.woshua.core.enums.PaperType;
import com.woshua.core.web.*;
import com.woshua.core.web.annotation.ObjectConvertAnno;
import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.catalogue.service.CatalogueService;
import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.maptree.service.MapTreeService;
import com.woshua.structure.praxis.domain.Praxis;
import com.woshua.structure.praxis.service.EnablePageStatusService;
import com.woshua.structure.praxis.service.PraxisService;
import com.woshua.structure.praxis.transferobj.EnablePageStatusTo;
import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.service.UserService;
import com.woshua.structure.user.transferobj.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/3/24.
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/praxis")
public class PraxisViewController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MapTreeService mapTreeService;
    @Autowired
    private PraxisService praxisService;
    @Autowired
    private EnablePageStatusService enablePageStatusService;
    @Autowired
    private CatalogueService catalogueService;

    @GetMapping(value = "/tiku")
    public ModelAndView praxisTiku(HttpServletRequest request) {
        List<MapTree> grades = mapTreeService.listGrades();
        List<MapTree> deciplines = mapTreeService.listDeciplines();
        List<MapTree> praxisType = mapTreeService.listPraxisType();
        List<Catalogue> catalogues = catalogueService.listCatalogue(new MapTree((MapTree.CHUZHONG)), new MapTree((MapTree.MATH)), new Catalogue(0L));

        ModelAndView modelAndView = new ModelAndView("praxis/tiku");
        modelAndView.addObject("grades", grades);
        modelAndView.addObject("deciplines", deciplines);
        modelAndView.addObject("praxisType", praxisType);
        modelAndView.addObject("catalogues", catalogues);

        return modelAndView;
    }

    @GetMapping(value = "/space")
    public ModelAndView praxisSpace(HttpServletRequest request, @ObjectConvertAnno User user) {
        ModelAndView modelAndView = new ModelAndView("praxis/space", "user", new UserTo(user));
        return modelAndView;
    }

    @GetMapping(value = "/zujuan")
    public ModelAndView praxisCombinePageStatus(HttpServletRequest request, @ObjectConvertAnno User user) {
        List<EnablePageStatusTo> enablePageStatusTos = enablePageStatusService.findAll();
        List<MapTree> deciplines = mapTreeService.listDeciplines();
        List<MapTree> praxisType = mapTreeService.listPraxisType();
        List<MapTree> grades = mapTreeService.listGrades();

        ModelAndView modelAndView = new ModelAndView("praxis/zujuan", "user", new UserTo(user));
        modelAndView.addObject("status", enablePageStatusTos);
        modelAndView.addObject("types", CombinePaperType.getTypes());
        modelAndView.addObject("paperTypes", PaperType.getTypes());
        modelAndView.addObject("deciplines", deciplines);
        modelAndView.addObject("praxisType", praxisType);
        modelAndView.addObject("grades", grades);

        return modelAndView;
    }

    @PostMapping(path = "/list_praxis")
    public
    @ResponseBody
    ModelAndView login(Pageable pageable, @RequestParam("grade") MapTree grade, @RequestParam("decipline") MapTree decipline, @RequestParam("type") MapTree type, int difficult, HttpServletRequest request, @ObjectConvertAnno User user) {
        String catalogueId = request.getParameter("catalogueId");

        Map<String, Object> result = praxisService.listByParams(pageable, grade, decipline, type, difficult, user, catalogueId);
        return new ModelAndView(new WebJsonView(result));
    }

    @PostMapping(path = "/add_favorite")
    public
    @ResponseBody
    ModelAndView addFavorite(@RequestParam("praxisId") Praxis praxis, HttpServletRequest request) {
        if (praxis == null) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_UN_LOGIN)));
        }

        User user = userService.authCookieUser(request.getCookies(), getIpAddress(request));
        if (user == null) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_TARGET_NOT_EXIST)));
        }

        boolean success = praxisService.addFavorite(praxis, user);
        if (success) {
            return new ModelAndView(new WebJsonView());
        } else {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_TARGET_EXIST)));
        }
    }

    @PostMapping(path = "/cancel_favorite")
    public
    @ResponseBody
    ModelAndView cancelFavorite(@RequestParam("praxisId") Praxis praxis, HttpServletRequest request) {
        if (praxis == null) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_UN_LOGIN)));
        }

        User user = userService.authCookieUser(request.getCookies(), getIpAddress(request));
        if (user == null) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_TARGET_NOT_EXIST)));
        }

        boolean success = praxisService.cancelFavorite(praxis, user);
        if (success) {
            return new ModelAndView(new WebJsonView());
        } else {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_TARGET_NOT_EXIST)));
        }
    }

    @PostMapping(path = "/list_favorite")
    public
    @ResponseBody
    ModelAndView listFavorite(HttpServletRequest request) {
        User user = userService.authCookieUser(request.getCookies(), getIpAddress(request));

        Map<String, Object> result = praxisService.listFavorite(getPage(request), user);
        return new ModelAndView(new WebJsonView(result));
    }

}
