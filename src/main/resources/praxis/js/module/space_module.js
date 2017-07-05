/**
 * Created by martinzeng on 16/9/19.
 */
/**
 * Created by Acer on 2016/9/19.
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

    var csrfParameterName = $(config.csrfParameterName).val();
    var csrfToken = $(config.csrfToken).val();

    var pageSize = 5;
    var currentPage = 1;
    var totalCount = 0;
    var showPage = 0;
    var showNumMax = 4;

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
                requestFavoritePraxis();
                //initPage();
            }
        });

        prevPage.bind("click", function () {
            currentPage--;
            if (currentPage < 1) {
                currentPage = 1;
            }
            requestFavoritePraxis();
            //initPage();
        });

        nextPage.bind("click", function () {
            currentPage++;
            if (currentPage > totalCount) {
                currentPage = totalCount;
            }
            requestFavoritePraxis();
            //initPage();
        });

        pageFirst.bind("click", function () {
            currentPage = 1;
            requestFavoritePraxis();
            //initPage();
        });

        pageMore.bind("click", function () {
            currentPage = showPage;
            requestFavoritePraxis();
            //initPage();
        });
    }

    exports.initData = function () {
        requestFavoritePraxis();
    }

    var requestFavoritePraxis = function () {
        nomoreData.hide();
        clearData();
        loading.show();

        var request = {
            size: pageSize,
            page: currentPage - 1,
            sort : "id",
            direction : "desc"
        }
        request[csrfParameterName] = csrfToken;

        $.post(config.listFavoritePraxis, request, function (data) {
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
                    requestFavoritePraxis();
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
        if (data && data.content && data.content.praxisList) {
            totalCount = data.content.tatolPage;
            initPage();
            var praxisArr = data.content.praxisList;
            $("html,body").animate({scrollTop: 0}, 500);

            var cloneTemplate = $("#item_container");

            if (praxisArr && praxisArr.length > 0) {
                for (var i = 0; i < praxisArr.length; i++) {
                    cloneTemplate.before(processParams($("#item_container").clone(true), praxisArr[i]));
                }
            } else {
                nomoreData.show();
            }

            //evil.doEvilImg(globalContent);
            evil.doEvilWidth(globalContent);
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
                favoriteModule.addFavorite($(this).offset(), data.id, content);
            }
        });

        content.find("#cancel_favorite").click(function () {
            if (!loginModule.isLogin()) {
                headerModule.showLoginDialog();
            } else {
                favoriteModule.cancelFavorite(data.id, content);
            }
        });

        if (data.favorite) {
            content.find("#add_favorite").css("display", "none");
            content.find("#cancel_favorite").css("display", "block");
        } else {
            content.find("#add_favorite").css("display", "block");
            content.find("#cancel_favorite").css("display", "none");
        }
        return content;
    }

});