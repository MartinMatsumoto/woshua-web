/**
 * Created by martinzeng on 16/9/20.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var config = require("config");
    var evil = require("evil");

    var dialog = $("#praxis_dialog");
    var closeBtn = $("#praxis_dialog_close");
    var closeBtnBottom = $("#praxis_dialog_close_btn");

    var contentContainer = $("#dialog_content_container");
    var promptContainer = $("#dialog_prompt_container");
    var resolveContainer = $("#dialog_resolve_container");
    var answerContainer = $("#dialog_answer_container");

    var dialogPraxisId = $("#dialog_praxis_id");

    exports.showDialog = function (data) {
        var part = this;
        dialog.show();
        closeBtn.click(function () {
            part.closeDialog();
        });
        closeBtnBottom.click(function () {
            part.closeDialog();
        });
        //getOnePraxis(id);
        processData(data);
    }

    exports.closeDialog = function () {
        dialog.hide();
        closeBtn.unbind("click");
        closeBtnBottom.unbind("click");
    }

    var getOnePraxis = function (id) {
        $.post(config.getOnePraxis, {
            id: id
        }, function (data) {
            processData(data);
        }, "json");
    }

    var processData = function (data) {
        contentContainer.html(evil.doEvilBackground(data.content));

        var prompt = data.prompt;
        if(!prompt || prompt == ""){
            prompt = "略";
        }
        promptContainer.html(evil.doEvilBackground(prompt));

        var resolve = data.resolve;
        if(!resolve || resolve == ""){
            resolve = "略";
        }
        resolveContainer.html(evil.doEvilBackground(resolve));

        var answer = data.answer;
        if(!answer || answer == ""){
            answer = "略";
        }
        answerContainer.html(answer);
        dialogPraxisId.val(data.id);

        //evil.doEvilImg(dialog);
        evil.doEvilWidth(dialog);
    }

});