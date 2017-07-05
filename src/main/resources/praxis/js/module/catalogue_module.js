/**
 * Created by Acer on 2017/7/4.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var closeBtn = $("#catalogue_close");

    exports.init = function () {
        initClose();
    }

    function initClose(){
        closeBtn.bind("click", function () {
            alert(123);
        });
    }
});