/**
 * Created by Acer on 2016/9/18.
 */
seajs.use(['header_module','login_dialog_module'], function (headerModule,loginDialogModule) {
    headerModule.initHead();

    headerModule.initScroll();

    loginDialogModule.initClick();
});