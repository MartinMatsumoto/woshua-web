/**
 * Created by martinzeng on 16/9/19.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var praxisDialog = require("praxis_dialog_module");
    var config = require("config");
    var evil = require("evil");
    var utils = require("utils");
    var loginModule = require("login_module");
    var headerModule = require("header_module");
    var favoriteModule = require("favorite_module");

    var globalContent = $("#global_content");
    var pageInput = $("#page_input");
    var prevPage = $("#prev_page");
    var nextPage = $("#next_page");
    var pageNum = $("#page_num");
    var pageMore = $("#page_more");
    var pageFirst = $("#page_first");
    var totalPage = $("#total_page");
    var pageNumContainer = $("#page_num_container");
    var nomoreData = $("#no_more_data");
    var loading = $("#loading");

    var pageSize = 10;
    var currentPage = 1;
    var grade = 14;
    var decipline = 5;
    var totalCount = 0;
    var showPage = 0;
    var showNumMax = 4;

    //单选题
    var singleChooseType = 17;
    //小学隐藏
    var hideGrade = 13;
    //语文
    var chineseDecipline = 4;
    //数学
    var mathDecipline = 5;
    //不限难度
    var difficultNoLimit = -1;
    var difficult = difficultNoLimit;

    var type = singleChooseType;

    var csrfParameterName = $(config.csrfParameterName).val();
    var csrfToken = $(config.csrfToken).val();

    exports.pageEnter = function () {
        pageInput.bind("keydown", function (event) {
            if (event.keyCode == 13) {
                var pageInputNum = pageInput.val();
                if (isNaN(pageInputNum)) {
                    currentPage = 1;
                } else {
                    currentPage = pageInputNum;
                    if (currentPage < 1) {
                        currentPage = 1;
                    }
                    if (currentPage > showPage) {
                        currentPage = showPage;
                    }
                }
                requestPraxis();
                //initPage();
            }
        });

        prevPage.bind("click", function () {
            currentPage--;
            if (currentPage < 1) {
                currentPage = 1;
            }
            requestPraxis();
            //initPage();
        });

        nextPage.bind("click", function () {
            currentPage++;
            if (currentPage > totalCount) {
                currentPage = totalCount;
            }
            requestPraxis();
            //initPage();
        });

        pageFirst.bind("click", function () {
            currentPage = 1;
            requestPraxis();
            //initPage();
        });

        pageMore.bind("click", function () {
            currentPage = showPage;
            requestPraxis();
            //initPage();
        });
    }

    exports.initTips = function () {
        //学段
        var gradeArr = $("li[id*='course_grade']");
        gradeArr.each(function (i) {
            $(gradeArr[i]).click(function () {
                grade = $(this).attr("circle");
                currentPage = 1;
                requestPraxis();
            });
        });

        var deciplineArr = $("li[id*='course_descipline']");
        deciplineArr.each(function (i) {
            $(deciplineArr[i]).click(function () {
                decipline = $(this).attr("circle");
                currentPage = 1;
                requestPraxis();
            });
        });

        var typeArr = $("li[id*='course_type']");
        typeArr.each(function (i) {
            $(typeArr[i]).click(function () {
                type = $(this).attr("circle");
                currentPage = 1;
                requestPraxis();
            });
        });

        var difficultArr = $("li[id*='course_difficult']");
        difficultArr.each(function (i) {
            $(difficultArr[i]).click(function () {
                difficult = $(this).attr("circle");
                currentPage = 1;
                requestPraxis();
            });
        });

        requestPraxis();
    }

    var refreshTipType = function () {
        var gradeArr = $("li[id*='course_grade']");
        gradeArr.each(function (i) {
            var obj = $(gradeArr[i]);
            obj.find("a").removeClass("now");
            if (obj.attr("circle") == grade) {
                obj.find("a").addClass("now");
            }
        });

        //选到小学后发现科目不是语文或数学，自动跳到语文
        if (hideGrade == grade && (decipline != chineseDecipline && decipline != mathDecipline)) {
            decipline = chineseDecipline;
        }

        var deciplineArr = $("li[id*='course_descipline']");
        deciplineArr.each(function (i) {
            var obj = $(deciplineArr[i]);
            obj.find("a").removeClass("now");
            if (obj.attr("circle") == decipline) {
                obj.find("a").addClass("now");
            }

            //选到小学后  只能显示语文或者数学
            if (hideGrade == grade && i > 1) {
                obj.hide();
            } else {
                obj.show();
            }
        });

        //选到语文后发现类型不是其他以前的，自动跳到单选题
        if (decipline != chineseDecipline && type > 24) {
            type = singleChooseType;
        }

        var typeArr = $("li[id*='course_type']");
        typeArr.each(function (i) {
            var obj = $(typeArr[i]);
            obj.find("a").removeClass("now");
            if (obj.attr("circle") == type) {
                obj.find("a").addClass("now");
            }

            if (decipline != chineseDecipline && i > 8) {
                obj.hide();
            } else {
                obj.show();
            }
        });

        var difficultArr = $("li[id*='course_difficult']");
        difficultArr.each(function (i) {
            var obj = $(difficultArr[i]);
            obj.find("a").removeClass("now");
            if (obj.attr("circle") == difficult) {
                obj.find("a").addClass("now");
            }
        });

    }

    exports.initData = function () {
        var gradeGet = utils.getUrlParam('grade');
        var deciplineGet = utils.getUrlParam('decipline');

        if (gradeGet) {
            grade = gradeGet;
        }

        if (deciplineGet) {
            decipline = deciplineGet;
        }
    }

    var requestPraxis = function () {
        refreshTipType();
        nomoreData.hide();
        clearData();
        loading.show();

        var request = {
            size: pageSize,
            page: currentPage - 1,
            grade: grade,
            decipline: decipline,
            type: type,
            difficult : difficult
        }
        request[csrfParameterName] = csrfToken;

        $.post(config.listPraxis, request, function (data) {
            loading.hide();
            processData(data);
        }, "json");
    }

    /**
     * 初始化页码
     * @param count
     */
    var initPage = function () {
        var pageNumArr = pageNumContainer.find("a[id*='page_num_clone']");
        if (pageNumArr && pageNumArr.length > 0) {
            pageNumArr.each(function (i) {
                $(pageNumArr[i]).remove();
            });
        }

        if (currentPage == 1) {
            prevPage.hide();
        } else {
            prevPage.show();
        }
        //showPage = Math.ceil(totalCount / pageSize);
        showPage = totalCount;
        totalPage.html(showPage);
        if (currentPage == showPage || showPage == 0) {
            nextPage.hide();
        } else {
            nextPage.show();
        }

        if (showPage > pageSize) {
            pageMore.show();
            pageMore.find("span").html(showPage);
        } else {
            pageMore.hide();
        }

        if (currentPage >= (pageSize - showNumMax)) {
            pageFirst.show();
        } else {
            pageFirst.hide();
        }

        var begin = currentPage - showNumMax;
        if (begin > showPage - pageSize) {
            begin = showPage - pageSize;
        }

        if (begin < 1) {
            begin = 1;
        }

        var end = begin + pageSize;
        if (end > showPage) {
            end = showPage;
        }
        for (var i = begin; i <= end; i++) {
            var pageNumTemplate = pageNum.clone(true);
            pageNumTemplate.attr("id", pageNum.attr("id") + "_clone");
            pageNumTemplate.show();
            pageNumTemplate.html(i);
            if (i == currentPage) {
                pageNumTemplate.addClass("now");
            } else {
                pageNumTemplate.bind("click", function () {
                    currentPage = $(this).html();
                    requestPraxis();
                    //initPage(totalCount);
                });
            }
            pageNum.before(pageNumTemplate);
        }

    }

    var clearData = function () {
        var contentArr = globalContent.find("div[id*='item_container_clone']");
        if (contentArr && contentArr.length > 0) {
            contentArr.each(function (i) {
                $(contentArr[i]).remove();
            });
        }
    }

    var processData = function (data) {
        if (data && data.content) {
            totalCount = data.content.tatolPage;
            initPage();
            var praxisArr = data.content.praxisList;
            $("html,body").animate({scrollTop: 0}, 500);

            var cloneTemplate = $("#item_container");

            if (praxisArr && praxisArr.length > 0) {
                for (var i = 0; i < praxisArr.length; i++) {
                    cloneTemplate.after(processParams($("#item_container").clone(true), praxisArr[i]));
                }
            } else {
                nomoreData.show();
            }

            evil.doEvilWidth(globalContent);
        }else if(data && data.header){
            if (data.header.flag == -1) {
                headerModule.showLoginDialog();
                $().toastmessage('showErrorToast', data.header.errorDesc);
            }
        }
    }

    var processParams = function (content, data) {
        content.attr("style", "display:block");
        content.attr("id", content.attr("id") + "_clone");
        content.find("#content_container").html(evil.doEvilBackground(data.content));
        content.find("#praxis_id").val(data.id);
        content.find("#check_detail").click(function () {
            praxisDialog.showDialog(data);
        });
        content.find("#add_favorite").click(function () {
            if (!loginModule.isLogin()) {
                headerModule.showLoginDialog();
            } else {
                favoriteModule.addFavorite($(this).offset(), data.id,content);
            }
        });

        content.find("#cancel_favorite").click(function () {
            if (!loginModule.isLogin()) {
                headerModule.showLoginDialog();
            } else {
                favoriteModule.cancelFavorite(data.id,content);
            }
        });

        if(data.favorite){
            content.find("#add_favorite").css("display","none");
            content.find("#cancel_favorite").css("display","block");
        }else{
            content.find("#add_favorite").css("display","block");
            content.find("#cancel_favorite").css("display","none");
        }

        return content;
    }

});