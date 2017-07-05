/**
 * Created by Acer on 2016/9/29.
 */
define(function (require, exports, module) {

    var $ = require("jquery");

    var major = 0;
    var majorTimeout;

    var type = 0;
    var typeTimeout;

    var paperType = 0;

    var majorSelect = $("#select_major");
    var majorChoose = $("#choose_major");
    var majorShow = $("#major_show");

    var typeSelect = $("#select_type");
    var typeChoose = $("#choose_type");
    var typeShow = $("#type_show");

    var paperSelect = $("#paper_type");

    exports.init = function () {
        initMajor();
        initType();
        initPaperType();
    }

    function initMajor() {
        majorSelect.bind("click", function () {
            majorChoose.fadeIn(200);
        });

        var majorChildren = majorChoose.children();
        for (var i = 0; i < majorChildren.length; i++) {
            if (i == 0) {
                major = $(majorChildren[i]).attr("circle");
                majorShow.html($(majorChildren[i]).html());
            }
            $(majorChildren[i]).bind("click", function () {
                major = $(this).attr("circle");
                majorShow.html($(this).html());
                majorChoose.fadeOut(200);
            });
        }

        majorSelect.bind("mouseover", function () {
            majorHover();
        });

        majorSelect.bind("mouseout", function () {
            setMajorTimeout();
        });

        majorChoose.bind("mouseover", function () {
            majorHover();
        });

        majorChoose.bind("mouseout", function () {
            setMajorTimeout();
        });

    }

    function setMajorTimeout() {
        majorTimeout = setTimeout(function () {
            majorChoose.fadeOut(200);
        }, 1000);
    }

    function majorHover() {
        clearTimeout(majorTimeout);
    }

    function initType() {
        typeSelect.bind("click", function () {
            typeChoose.fadeIn(200);
        });

        var typeChildren = typeChoose.children();
        for (var i = 0; i < typeChildren.length; i++) {
            if (i == 0) {
                type = $(typeChildren[i]).attr("circle");
                typeShow.html($(typeChildren[i]).html());
            }
            $(typeChildren[i]).bind("click", function () {
                type = $(this).attr("circle");
                typeShow.html($(this).html());
                typeChoose.fadeOut(200);
            });
        }

        typeSelect.bind("mouseover", function () {
            typeHover();
        });

        typeSelect.bind("mouseout", function () {
            setTypeTimeout();
        });

        typeChoose.bind("mouseover", function () {
            typeHover();
        });

        typeChoose.bind("mouseout", function () {
            setTypeTimeout();
        });

    }

    function setTypeTimeout() {
        typeTimeout = setTimeout(function () {
            typeChoose.fadeOut(200);
        }, 1000);
    }

    function typeHover() {
        clearTimeout(typeTimeout);
    }

    function initPaperType() {
        var paperChildren = paperSelect.children();
        for (var i = 0; i < paperChildren.length; i++) {
            $(paperChildren[i]).bind("click", function () {
                onPaperChoose($(this));
            });
        }
    }

    function onPaperChoose(div) {
        var paperChildren = paperSelect.children();
        for (var i = 0; i < paperChildren.length; i++) {
            $(paperChildren[i]).removeClass("select");
        }
        div.addClass("select");
        paperType = div.attr("circle");
    }

});
