/**
 * Created by Acer on 2016/9/18.
 */
seajs.use(['header_module', 'login_dialog_module', 'space_module'], function (headerModule, loginDialogModule, spaceModule) {
    spaceModule.pageEnter();

    spaceModule.initData();

    headerModule.initHead();

    headerModule.initScroll();
});